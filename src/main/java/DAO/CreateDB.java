package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {

    protected static final String db_url = "jdbc:mysql://localhost:3306/";
    protected static final String db_user = "root";
    protected static final String db_pass = "password123";
    protected static final String db_name = "restaurantdb";
    // Group DB password
    // protected static final String db_pass = "Lama1234!";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(db_url, db_user, db_pass);
                Statement stmt = conn.createStatement();) {
            // Create database
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + db_name);
            conn.close();

            // Create tables
            Connection con = DriverManager.getConnection(db_url + db_name, db_user, db_pass);
            createTables(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTables(Connection conn) throws SQLException {
        // Create event table
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS event (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(100)," +
                    "description VARCHAR(255)," +
                    "date DATE," +
                    "type VARCHAR(50))");
            System.out.println("Event table creation successful.");
        }

        // Create table management table
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `table_management` (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "capacity INT," +
                    "status VARCHAR(20)," +
                    "reservation_time TIMESTAMP)");
            System.out.println("Table Management table creation successful.");
        }

        // Create payment table
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Payment (" +
                    "paymentID INT AUTO_INCREMENT PRIMARY KEY," +
                    "method VARCHAR(50)," +
                    "cardNum VARBINARY(255)," + // Use VARBINARY for encrypted data
                    "expMonth VARCHAR(2)," +
                    "expYear VARCHAR(4)," +
                    "cvn VARBINARY(255)," + // Use VARBINARY for encrypted data
                    "paymentAmount DECIMAL(10, 2)," +
                    "paymentDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    "isCancelled BOOLEAN DEFAULT FALSE)");
            System.out.println("Payment table creation successful.");
        }

    }
}
