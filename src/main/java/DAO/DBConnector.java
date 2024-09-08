package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector extends CreateDB {

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(db_url + db_name, db_user, db_pass);
        System.out.println("Database connection established"); // Debugging line
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
