package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {

    protected static final String JDBC_URL = "jdbc:mysql://localhost:3306/restaurant_db";
    protected static final String JDBC_USER = "root";
    protected static final String JDBC_PASSWORD = "Karthi";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                Statement stmt = conn.createStatement();) {
            // Create database
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}