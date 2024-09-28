package DAO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Event;
import model.Payment;
import model.Table;
import model.InventoryAudit;

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
            return st.executeUpdate() > 0;
        }
    }

    // Update a table status
    public boolean updateTableStatus(int tableId, String status) throws SQLException {
        String sql = "UPDATE table_management SET status = ? WHERE id = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, status);
            st.setInt(2, tableId);
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
                        rs.getInt("capacity"));
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
                            rs.getInt("capacity"));
                }
            }
        }
        return null;
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

    // Payment Management Methods

    // Create Payment
    public boolean createPayment(Payment payment) throws Exception {
        String sql = "INSERT INTO payments (method, cardNum, expMonth, expYear, cvn, paymentAmount, isCancelled) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, payment.getMethod());
            st.setString(2, payment.getCardNum()); // Encrypt before saving
            st.setString(3, payment.getExpMonth());
            st.setString(4, payment.getExpYear());
            st.setString(5, payment.getCVN()); // Encrypt before saving
            st.setBigDecimal(6, new BigDecimal(payment.getPaymentAmount())); // Handle decimals correctly
            st.setBoolean(7, payment.isCancelled());
            return st.executeUpdate() > 0;
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
                "FROM InventoryAudit " +
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
}