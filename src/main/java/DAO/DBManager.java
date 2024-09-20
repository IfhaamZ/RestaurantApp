package DAO;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Event;
import model.Table;

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

    // Clear reservation details when a table becomes available
    public boolean clearReservationDetails(int tableId) throws SQLException {
        String sql = "UPDATE table_management SET reservation_time = NULL, reserved_by_name = NULL, reserved_by_phone = NULL, reserved_by_email = NULL WHERE id = ?";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, tableId);
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
}