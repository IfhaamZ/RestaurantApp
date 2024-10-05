package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Event;
import model.Order;
import model.Table;
import model.User;

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

        // Validate user for login
        public User validateUser(String email, String password) throws SQLException {
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            try (Connection connection = DBConnector.getConnection();
                 PreparedStatement st = connection.prepareStatement(sql)) {
                st.setString(1, email);
                st.setString(2, password);
                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        return new User(rs.getString("name"), rs.getString("email"), rs.getString("password"));
                    }
                }
            }
            return null; // Return null if user is not found
        }
    
        // Register a new user
        public boolean registerUser(User user) throws SQLException {
            String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
            try (Connection connection = DBConnector.getConnection();
                 PreparedStatement st = connection.prepareStatement(sql)) {
                st.setString(1, user.getName());
                st.setString(2, user.getEmail());
                st.setString(3, user.getPassword());
                return st.executeUpdate() > 0; // returns true if registration is successful
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

public List<User> getAllUsers() throws SQLException {
    List<User> users = new ArrayList<>();
    String sql = "SELECT * FROM users";
    try (Connection connection = DBConnector.getConnection();
         PreparedStatement st = connection.prepareStatement(sql);
         ResultSet rs = st.executeQuery()) {
        while (rs.next()) {
            User user = new User(rs.getString("name"), rs.getString("email"), rs.getString("password"));
            users.add(user);
        }
    }
    return users;
}

// Get user by ID
public User getUserById(int id) throws SQLException {
    String sql = "SELECT * FROM users WHERE id = ?";
    try (Connection connection = DBConnector.getConnection();
         PreparedStatement st = connection.prepareStatement(sql)) {
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            return new User(rs.getString("name"), rs.getString("email"), rs.getString("password"));
        }
    }
    return null;
}

// Update an existing user
public boolean updateUser(User user) throws SQLException {
    String sql = "UPDATE users SET name = ?, email = ?, password = ? WHERE id = ?";
    try (Connection connection = DBConnector.getConnection();
         PreparedStatement st = connection.prepareStatement(sql)) {
        st.setString(1, user.getName());
        st.setString(2, user.getEmail());
        st.setString(3, user.getPassword());
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
}
