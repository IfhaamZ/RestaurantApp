package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.error;

public class DBManager {

    // Method to create a new error report and return its generated ID
    public int createErrorReportAndReturnID(String description, String steps, String category, String severity)
            throws SQLException {
        String sql = "INSERT INTO errors (description, steps, category, severity) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Set parameters
            statement.setString(1, description);
            statement.setString(2, steps);
            statement.setString(3, category);
            statement.setString(4, severity);

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
                        resultSet.getString("severity"));
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
            statement.setInt(6, errorReport.getId());

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
                            resultSet.getString("severity"));
                    errorReport.setStaffComments(resultSet.getString("staff_comments")); // Retrieve staff comments
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
}
