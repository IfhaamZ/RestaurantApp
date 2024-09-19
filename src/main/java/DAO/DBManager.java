package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBManager {

    // Error Submission Method

    // Create a new error report
    public boolean createErrorReport(String description, String steps, String category, String severity)
            throws SQLException {
        String sql = "INSERT INTO errors (description, steps, category, severity) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, description);
            statement.setString(2, steps);
            statement.setString(3, category);
            statement.setString(4, severity);
            return statement.executeUpdate() > 0;
        }
    }

    // Feedback Submission Method
    // Create a new feedback entry
    public boolean createFeedback(String customerName, String customerEmail, String feedbackText, int rating)
            throws SQLException {
        String sql = "INSERT INTO feedback (customer_name, customer_email, feedback_text, rating) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customerName);
            statement.setString(2, customerEmail);
            statement.setString(3, feedbackText);
            statement.setInt(4, rating);
            return statement.executeUpdate() > 0;
        }
    }

}
