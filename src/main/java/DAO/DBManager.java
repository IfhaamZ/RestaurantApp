package DAO;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import model.Event;
import model.InventoryAudit;
import model.MenuItem;
import model.Order;
import model.Payment;
import model.Reservation;
import model.Table;
import model.User;
import model.error;
import model.feedback;

public class DBManager {

    // Event Management Methods

    // Create a new event
    public boolean createEvent(Event event) throws SQLException {
        String sql = "INSERT INTO event (name, description, date, type) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, event.getName());
            st.setString(2, event.getDescription());
            st.setDate(3, Date.valueOf(event.getDate()));
            st.setString(4, event.getType());
            return st.executeUpdate() > 0;
        }
    }

    // Update an event
    public boolean updateEvent(Event event) throws SQLException {
        String sql = "UPDATE event SET name = ?, description = ?, date = ?, type = ? WHERE id = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, event.getName());
            st.setString(2, event.getDescription());
            st.setDate(3, Date.valueOf(event.getDate()));
            st.setString(4, event.getType());
            st.setInt(5, event.getId());
            return st.executeUpdate() > 0;
        }
    }

    // Fetch all events
    public List<Event> fetchAllEvents() throws SQLException {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM Event";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Event event = new Event(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDate("date").toLocalDate(),
                        rs.getString("type"));
                events.add(event);
            }
        }
        System.out.println("Total events fetched: " + events.size()); // Debugging line
        return events;
    }

    // Fetch an event by ID
    public Event getEventById(int id) throws SQLException {
        String sql = "SELECT * FROM event WHERE id = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Event(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDate("date").toLocalDate(),
                            rs.getString("type"));
                }
            }
        }
        return null;
    }

    // Search events by type and date
    public List<Event> searchEvents(String type, String date) throws SQLException {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM Event WHERE 1=1";

        if (type != null && !type.isEmpty()) {
            sql += " AND type = ?";
        }
        if (date != null && !date.isEmpty()) {
            sql += " AND date = ?";
        }

        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {

            int paramIndex = 1;
            if (type != null && !type.isEmpty()) {
                st.setString(paramIndex++, type);
            }
            if (date != null && !date.isEmpty()) {
                st.setDate(paramIndex++, java.sql.Date.valueOf(date));
            }

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Event event = new Event(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDate("date").toLocalDate(),
                        rs.getString("type"));
                events.add(event);
            }
        }
        return events;
    }

    // Delete an event
    public boolean deleteEvent(int id) throws SQLException {
        String sql = "DELETE FROM event WHERE id = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            return st.executeUpdate() > 0;
        }
    }

    // Table Management Methods

    // Create a new table
    public boolean createTable(Table table) throws SQLException {
        String sql = "INSERT INTO table_management (capacity, status, reservation_time) VALUES (?, ?, ?)";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, table.getCapacity());
            st.setString(2, table.getStatus());
            if (table.getReservationTime() != null) {
                st.setTimestamp(3, Timestamp.valueOf(table.getReservationTime()));
            } else {
                st.setNull(3, Types.TIMESTAMP);
            }
            return st.executeUpdate() > 0;
        }
    }

    // Update a table
    public boolean updateTable(Table table) throws SQLException {
        String sql = "UPDATE table_management SET capacity = ?, status = ?, reservation_time = ? WHERE id = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, table.getCapacity());
            st.setString(2, table.getStatus());
            if (table.getReservationTime() != null) {
                st.setTimestamp(3, Timestamp.valueOf(table.getReservationTime()));
            } else {
                st.setNull(3, Types.TIMESTAMP);
            }
            st.setInt(4, table.getId());
            return st.executeUpdate() > 0;
        }
    }

    // Fetch all tables
    public List<Table> fetchAllTables() throws SQLException {
        List<Table> tables = new ArrayList<>();
        String sql = "SELECT * FROM table_management";

        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Table table = new Table(
                        rs.getInt("id"),
                        rs.getString("status"),
                        rs.getInt("capacity"),
                        rs.getTimestamp("reservation_time") != null
                                ? rs.getTimestamp("reservation_time").toLocalDateTime()
                                : null,
                        rs.getString("reserved_by_name"),
                        rs.getString("reserved_by_phone"),
                        rs.getString("reserved_by_email"));
                tables.add(table);
            }
        }
        return tables;
    }

    // Fetch a table by ID
    public Table getTableById(int id) throws SQLException {
        String sql = "SELECT * FROM table_management WHERE id = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Table(
                            rs.getInt("id"),
                            rs.getString("status"),
                            rs.getInt("capacity"),
                            rs.getTimestamp("reservation_time") != null
                                    ? rs.getTimestamp("reservation_time").toLocalDateTime()
                                    : null,
                            rs.getString("reserved_by_name"),
                            rs.getString("reserved_by_phone"),
                            rs.getString("reserved_by_email"));
                }
            }
        }
        return null;
    }

    // Fetch all available tables
    public List<Table> fetchAvailableTables() throws SQLException {
        List<Table> availableTables = new ArrayList<>();
        String sql = "SELECT * FROM table_management WHERE status = 'Available'";

        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Table table = new Table(
                        rs.getInt("id"),
                        rs.getString("status"),
                        rs.getInt("capacity"),
                        rs.getTimestamp("reservation_time") != null
                                ? rs.getTimestamp("reservation_time").toLocalDateTime()
                                : null,
                        rs.getString("reserved_by_name"),
                        rs.getString("reserved_by_phone"),
                        rs.getString("reserved_by_email"));
                availableTables.add(table);
            }
        }
        return availableTables;
    }

    // Reserve a table by updating its status and reservation time
    public boolean reserveTable(int tableId, LocalDateTime reservationTime, String reservedByName,
            String reservedByPhone, String reservedByEmail) throws SQLException {
        String sql = "UPDATE table_management SET status = ?, reservation_time = ?, reserved_by_name = ?, reserved_by_phone = ?, reserved_by_email = ? WHERE id = ?";

        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {

            st.setString(1, "Reserved");
            st.setTimestamp(2, Timestamp.valueOf(reservationTime));
            st.setString(3, reservedByName);
            st.setString(4, reservedByPhone);
            st.setString(5, reservedByEmail);
            st.setInt(6, tableId);

            return st.executeUpdate() > 0;
        }
    }

    public boolean clearReservationDetails(int tableId) throws SQLException {
        String sql = "UPDATE table_management SET status = ?, reservation_time = NULL, reserved_by_name = NULL, reserved_by_phone = NULL, reserved_by_email = NULL WHERE id = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, "Available"); // Set status to Available
            st.setInt(2, tableId);
            return st.executeUpdate() > 0;
        }
    }

    // Delete a table
    public boolean deleteTable(int id) throws SQLException {
        String sql = "DELETE FROM table_management WHERE id = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            return st.executeUpdate() > 0;
        }
    }

    // Menu Management Tests
    // Insert a new menu item
    public boolean insertMenuItem(MenuItem item) throws SQLException {
        String sql = "INSERT INTO menu (name, description, price, category) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, item.getName());
            st.setString(2, item.getDescription());
            st.setDouble(3, item.getPrice());
            st.setString(4, item.getCategory());
            return st.executeUpdate() > 0;
        }
    }

    // Fetch all menu items
    public List<MenuItem> fetchAllMenuItems() throws SQLException {
        List<MenuItem> menuItems = new ArrayList<>();
        String sql = "SELECT * FROM menu";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                MenuItem item = new MenuItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getString("category"));
                menuItems.add(item);
            }
        }
        return menuItems;
    }

    // Fetch a menu item by ID
    public MenuItem getMenuItemById(int id) throws SQLException {
        String sql = "SELECT * FROM menu WHERE id = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new MenuItem(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getString("category"));
                }
            }
        }
        return null;
    }

    // Update an existing menu item
    public boolean updateMenuItem(MenuItem item) throws SQLException {
        String sql = "UPDATE menu SET name = ?, description = ?, price = ?, category = ? WHERE id = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, item.getName());
            st.setString(2, item.getDescription());
            st.setDouble(3, item.getPrice());
            st.setString(4, item.getCategory());
            st.setInt(5, item.getId());
            return st.executeUpdate() > 0;
        }
    }

    // Fetch menu items based on category
    public List<MenuItem> fetchMenuItemsByCategory(String category) throws SQLException {
        List<MenuItem> menuItems = new ArrayList<>();
        String sql = "SELECT * FROM menu WHERE category LIKE ?";

        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, "%" + category + "%"); // Supports partial matches for more flexible searching

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    MenuItem menuItem = new MenuItem(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getString("category"));
                    menuItems.add(menuItem);
                }
            }
        }
        return menuItems;
    }

    // Method to fetch all unique categories from the menu table
    public List<String> fetchAllCategories() throws SQLException {
        List<String> categories = new ArrayList<>();
        String sql = "SELECT DISTINCT category FROM menu";

        try (Connection connection = DBConnector.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                categories.add(rs.getString("category"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching categories: " + e.getMessage());
            throw e;
        }

        return categories;
    }

    // Delete a menu item
    public boolean deleteMenuItem(int id) throws SQLException {
        String sql = "DELETE FROM menu WHERE id = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            return st.executeUpdate() > 0;
        }
    }

    // Error and Feedback
    // Method to create a new error report and return its generated ID
    public int createErrorReportAndReturnID(String description, String steps, String category, String severity,
            Timestamp createdAt)
            throws SQLException {
        String sql = "INSERT INTO errors (description, steps, category, severity, created_at) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Set parameters
            statement.setString(1, description);
            statement.setString(2, steps);
            statement.setString(3, category);
            statement.setString(4, severity);
            statement.setTimestamp(5, createdAt); // Set the created_at timestamp

            // Execute update
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Error report creation failed, no rows affected.");
            }

            // Retrieve the generated ID
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Return the generated ID
                } else {
                    throw new SQLException("Error report creation failed, no ID obtained.");
                }
            }
        }
    }

    // Method to retrieve all error reports
    public List<error> getAllErrorReports() throws SQLException {
        String sql = "SELECT * FROM errors";
        List<error> errorReports = new ArrayList<>();

        try (Connection connection = DBConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            // Loop through the result set and create error objects
            while (resultSet.next()) {
                error errorReport = new error(
                        resultSet.getInt("id"),
                        resultSet.getString("description"),
                        resultSet.getString("steps"),
                        resultSet.getString("category"),
                        resultSet.getString("severity"),
                        resultSet.getTimestamp("created_at")); // Retrieve createdAt
                errorReport.setStaffComments(resultSet.getString("staff_comments")); // Retrieve staff comments
                errorReports.add(errorReport);
            }
        }
        return errorReports;
    }

    // Method to update an existing error report
    public boolean updateErrorReport(error errorReport) throws SQLException {
        String sql = "UPDATE errors SET description=?, steps=?, category=?, severity=?, staff_comments=? WHERE id=?";

        try (Connection connection = DBConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            // Set parameters for the update statement
            statement.setString(1, errorReport.getDescription());
            statement.setString(2, errorReport.getSteps());
            statement.setString(3, errorReport.getCategory());
            statement.setString(4, errorReport.getSeverity());
            statement.setString(5, errorReport.getStaffComments()); // Update staff comments
            statement.setInt(6, errorReport.getId()); // Error ID for the WHERE clause

            // Execute update and return whether the operation was successful
            return statement.executeUpdate() > 0;
        }
    }

    // Method to retrieve a specific error report by ID
    public error getErrorById(int id) throws SQLException {
        String sql = "SELECT * FROM errors WHERE id = ?";
        error errorReport = null;

        try (Connection connection = DBConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    errorReport = new error(
                            resultSet.getInt("id"),
                            resultSet.getString("description"),
                            resultSet.getString("steps"),
                            resultSet.getString("category"),
                            resultSet.getString("severity"),
                            resultSet.getTimestamp("created_at"));
                    errorReport.setStaffComments(resultSet.getString("staff_comments"));
                }
            }
        }
        return errorReport; // Return the error object if found, or null otherwise
    }

    // Feedback Submission Method
    public boolean createFeedback(String customerName, String customerEmail, String feedbackText, int rating)
            throws SQLException {
        String sql = "INSERT INTO feedback (customer_name, customer_email, feedback_text, rating) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            // Set parameters for the feedback submission
            statement.setString(1, customerName);
            statement.setString(2, customerEmail);
            statement.setString(3, feedbackText);
            statement.setInt(4, rating);

            // Execute update and return whether the operation was successful
            return statement.executeUpdate() > 0;
        }
    }

    // Method to create feedback and return its generated ID
    public int createFeedbackAndReturnID(String customerName, String customerEmail, String feedbackText, int rating)
            throws SQLException {
        String sql = "INSERT INTO feedback (customer_name, customer_email, feedback_text, rating) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Set parameters for the feedback submission
            statement.setString(1, customerName);
            statement.setString(2, customerEmail);
            statement.setString(3, feedbackText);
            statement.setInt(4, rating);

            // Execute update
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Feedback submission failed, no rows affected.");
            }

            // Retrieve the generated ID
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Return the generated ID
                } else {
                    throw new SQLException("Feedback submission failed, no ID obtained.");
                }
            }
        }
    }

    // Method to get feedback by ID
    public feedback getFeedbackById(int feedbackId) throws SQLException {
        String sql = "SELECT * FROM feedback WHERE feedback_id = ?"; // Changed from 'id' to 'feedback_id'
        feedback fb = null;

        try (Connection connection = DBConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, feedbackId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    // Assuming the feedback table has columns: feedback_id, customer_name, email,
                    // feedback_text, rating, staff_response
                    fb = new feedback(
                            rs.getInt("feedback_id"), // Use feedback_id here
                            rs.getString("customer_name"),
                            rs.getString("customer_email"),
                            rs.getString("feedback_text"),
                            rs.getInt("rating"),
                            rs.getString("staff_response"),
                            rs.getTimestamp("created_at") // Assuming this is a timestamp field
                    );
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching feedback by ID", e);
        }
        return fb;
    }

    public List<feedback> getAllFeedback() throws SQLException {
        String sql = "SELECT * FROM feedback";
        List<feedback> feedbackList = new ArrayList<>();

        try (Connection connection = DBConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                feedback fb = new feedback(
                        resultSet.getInt("feedback_id"), // Changed from 'id' to 'feedback_id'
                        resultSet.getString("customer_name"),
                        resultSet.getString("customer_email"),
                        resultSet.getString("feedback_text"),
                        resultSet.getInt("rating"),
                        resultSet.getString("staff_response"), // Staff response added
                        resultSet.getTimestamp("created_at") // Created at timestamp added
                );
                feedbackList.add(fb);
            }
        }
        return feedbackList;
    }

    // Method to update feedback with staff response
    public boolean updateFeedbackWithResponse(int feedbackId, String staffResponse) throws SQLException {
        String sql = "UPDATE feedback SET staff_response=? WHERE feedback_id=?"; // Changed from 'id' to 'feedback_id'

        try (Connection connection = DBConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, staffResponse);
            statement.setInt(2, feedbackId);

            return statement.executeUpdate() > 0;
        }
    }

    // Payment Management Methods

    // Create Payment and return the generated paymentID
    public int createPayment(Payment payment) throws Exception {
        String sql = "INSERT INTO payments (method, cardNum, expMonth, expYear, cvn, paymentAmount, isCancelled) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            st.setString(1, payment.getMethod());
            st.setString(2, payment.getCardNum()); // Encrypt before saving
            st.setString(3, payment.getExpMonth());
            st.setString(4, payment.getExpYear());
            st.setString(5, payment.getCVN()); // Encrypt before saving
            st.setBigDecimal(6, new BigDecimal(payment.getPaymentAmount())); // Handle decimals correctly
            st.setBoolean(7, payment.isCancelled());
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating payment failed, no rows affected.");
            }

            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Return the generated paymentID
                } else {
                    throw new SQLException("Creating payment failed, no ID obtained.");
                }
            }
        }
    }

    // Fetch Payment by ID
    public Payment getPaymentById(int paymentID) throws Exception {
        String sql = "SELECT * FROM payments WHERE paymentID = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, paymentID);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Payment payment = new Payment(
                            rs.getString("paymentID"),
                            rs.getString("method"),
                            Payment.decrypt(rs.getString("cardNum")), // Decrypt
                            rs.getString("expMonth"),
                            rs.getString("expYear"),
                            Payment.decrypt(rs.getString("cvn")), // Decrypt
                            rs.getString("paymentAmount"),
                            rs.getTimestamp("paymentDate").toString(),
                            null // No staff role here
                    );
                    return payment;
                }
            }
        }
        return null;
    }

    // Update Payment Status
    public boolean cancelPayment(int paymentID) throws SQLException {
        String sql = "UPDATE payments SET isCancelled = TRUE WHERE paymentID = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, paymentID);
            return st.executeUpdate() > 0;
        }
    }

    // Fetch All Payments
    public List<Payment> fetchAllPayments() throws Exception {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Payment payment = new Payment(
                        rs.getString("paymentID"),
                        rs.getString("method"),
                        Payment.decrypt(rs.getString("cardNum")), // Decrypt
                        rs.getString("expMonth"),
                        rs.getString("expYear"),
                        Payment.decrypt(rs.getString("cvn")), // Decrypt
                        rs.getString("paymentAmount"),
                        rs.getTimestamp("paymentDate").toString(),
                        null // No staff role here
                );
                payments.add(payment);
            }
        }
        return payments;
    }

    public List<InventoryAudit> getInventoryAuditByProduct(String productID) throws SQLException {
        List<InventoryAudit> auditList = new ArrayList<>();

        String sql = "SELECT productID, oldStockLevel, newStockLevel, timestamp, updatedBy " +
                "FROM inventoryaudit " +
                "WHERE productID = ? " +
                "ORDER BY timestamp DESC " +
                "LIMIT 10";

        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {

            // Set the parameter for the prepared statement
            st.setString(1, productID);

            // Execute the query
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    // Create an InventoryAudit object and set its properties
                    InventoryAudit audit = new InventoryAudit();
                    audit.setProductID(rs.getString("productID"));
                    audit.setOldStock(rs.getInt("oldStockLevel"));
                    audit.setNewStock(rs.getInt("newStockLevel"));
                    audit.setTimestamp(rs.getTimestamp("timestamp"));
                    audit.setUpdatedBy(rs.getString("updatedBy"));

                    // Add to the list
                    auditList.add(audit);
                }
            }
        }

        return auditList;
    }

    public User validateUser(String email, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, email);
            st.setString(2, password);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
                            rs.getString("password"), rs.getString("role")); // Include role
                }
            }
        }
        return null; // Return null if user is not found
    }

    // Register a new user
    public boolean registerUser(User user) throws SQLException {
        String sql = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, user.getName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getPassword());
            st.setString(4, user.getRole()); // Set role
            return st.executeUpdate() > 0;
        }
    }

    // Check if user exists by email during registration
    public boolean checkIfUserExists(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, email);
            try (ResultSet rs = st.executeQuery()) {
                return rs.next(); // returns true if a user with the same email exists
            }
        }

    }

    // Get user by ID
    public User getUserById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"),
                        rs.getString("role"));
            }
        }
        return null;
    }

    // Get all users
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
                        rs.getString("password"), rs.getString("role"));
                users.add(user);
            }
        }
        return users;
    }

    // Update user
    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET name = ?, email = ?, password = ?, role = ? WHERE id = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, user.getName());
            st.setString(2, user.getEmail());
            st.setString(3, user.getPassword());
            st.setString(4, user.getRole()); // Set role
            st.setInt(5, user.getId());
            return st.executeUpdate() > 0;
        }
    }

    // Delete a user
    public boolean deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            return st.executeUpdate() > 0;
        }
    }

    // Insert a new order
public boolean insertOrder(Order order) throws SQLException {
    String sql = "INSERT INTO orders (customerName, orderDetails, status) VALUES (?, ?, ?)";
    try (Connection connection = DBConnector.getConnection();
         PreparedStatement st = connection.prepareStatement(sql)) {
        st.setString(1, order.getCustomerName());
        st.setString(2, order.getOrderDetails());
        st.setString(3, order.getStatus());
        return st.executeUpdate() > 0;
    }
}

// Fetch all orders for staff view
public List<Order> getAllOrders() throws SQLException {
    List<Order> orders = new ArrayList<>();
    String sql = "SELECT * FROM orders";
    try (Connection connection = DBConnector.getConnection();
         PreparedStatement st = connection.prepareStatement(sql);
         ResultSet rs = st.executeQuery()) {
        while (rs.next()) {
            Order order = new Order(
                    rs.getInt("id"),
                    rs.getString("customerName"),
                    rs.getString("orderDetails"),
                    rs.getString("status")
            );
            orders.add(order);
        }
    }
    return orders;
}

// Fetch order by ID
public Order getOrderById(int id) throws SQLException {
    String sql = "SELECT * FROM orders WHERE id = ?";
    try (Connection connection = DBConnector.getConnection();
         PreparedStatement st = connection.prepareStatement(sql)) {
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return new Order(
                    rs.getInt("id"),
                    rs.getString("customerName"),
                    rs.getString("orderDetails"),
                    rs.getString("status")
            );
        }
    }
    return null;
}

// Update an order
public boolean updateOrder(Order order) throws SQLException {
    String sql = "UPDATE orders SET orderDetails = ?, status = ? WHERE id = ?";
    try (Connection connection = DBConnector.getConnection();
         PreparedStatement st = connection.prepareStatement(sql)) {
        st.setString(1, order.getOrderDetails());
        st.setString(2, order.getStatus());
        st.setInt(3, order.getId());
        return st.executeUpdate() > 0;
    }
}

// Delete an order
public boolean deleteOrder(int id) throws SQLException {
    String sql = "DELETE FROM orders WHERE id = ?";
    try (Connection connection = DBConnector.getConnection();
            PreparedStatement st = connection.prepareStatement(sql)) {
        st.setInt(1, id);
        return st.executeUpdate() > 0;
    }
}

// 예약 추가 메서드
    public boolean addReservation(Reservation reservation) throws SQLException {
        String sql = "INSERT INTO reservations (name, reservation_date, reservation_time, guests) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {

            // LocalDate와 LocalTime을 MySQL의 Date와 Time으로 변환
            st.setString(1, reservation.getName());
            st.setDate(2, Date.valueOf(reservation.getDate()));  // LocalDate -> java.sql.Date
            st.setTime(3, Time.valueOf(reservation.getTime()));  // LocalTime -> java.sql.Time
            st.setInt(4, reservation.getGuests());

            return st.executeUpdate() > 0;
        }
    }

    // 예약 삭제 메서드
    public void deleteReservation(int reservationId, String reservationName) throws SQLException {
        String sql = "DELETE FROM reservations WHERE id = ? AND name = ?";
        try (Connection connection = DBConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, reservationId);
            preparedStatement.setString(2, reservationName);
            preparedStatement.executeUpdate();
        }
    }

    // 예약 업데이트 메서드
    public boolean updateReservation(Reservation reservation) throws SQLException {
        String sql = "UPDATE reservations SET name = ?, reservation_date = ?, reservation_time = ?, guests = ? WHERE id = ?";
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {

            st.setString(1, reservation.getName());
            st.setDate(2, Date.valueOf(reservation.getDate()));
            st.setTime(3, Time.valueOf(reservation.getTime()));
            st.setInt(4, reservation.getGuests());
            st.setInt(5, reservation.getId());

            

            return st.executeUpdate() > 0;
        }
    }

     // 모든 예약 데이터를 가져오는 메서드 추가
    public List<Reservation> getAllReservations() throws SQLException {
        String sql = "SELECT * FROM reservations";
        List<Reservation> reservations = new ArrayList<>();

        try (Connection connection = DBConnector.getConnection();
             PreparedStatement st = connection.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                LocalDate date = rs.getDate("reservation_date").toLocalDate();
                LocalTime time = rs.getTime("reservation_time").toLocalTime();
                int guests = rs.getInt("guests");

                // Reservation 객체 생성 및 리스트에 추가
                reservations.add(new Reservation(id, name, date, time, guests));
            }
        }

        return reservations;
    }
}