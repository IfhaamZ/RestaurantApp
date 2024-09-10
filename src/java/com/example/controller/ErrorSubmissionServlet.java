package com.example.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

@WebServlet("/submitError")
public class ErrorSubmissionServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ErrorSubmissionServlet.class.getName());

    // Database connection details
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/restaurant_db";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Karthi";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("ErrorSubmissionServlet: doPost method invoked");

        // Retrieve form data
        String description = request.getParameter("description");
        String steps = request.getParameter("steps");
        String category = request.getParameter("category");
        String severity = request.getParameter("severity");

        // Validate required fields
        if (description == null || description.trim().isEmpty() ||
            steps == null || steps.trim().isEmpty() ||
            category == null || category.trim().isEmpty() ||
            severity == null || severity.trim().isEmpty()) {

            logger.warning("Validation failed: One or more fields are empty");
            request.setAttribute("errorMessage", "All fields are required.");
            request.getRequestDispatcher("/errorSubmission.jsp").forward(request, response);
            return;
        }

        // Log the form data
        logger.info("Error Description: " + description);
        logger.info("Steps Taken: " + steps);
        logger.info("Error Category: " + category);
        logger.info("Severity: " + severity);

        // Load MySQL JDBC driver and establish connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            logger.info("JDBC Driver loaded successfully.");

            // Establish database connection
            try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                logger.info("Database connection established.");

                // SQL query to insert the error report into the errors table
                String sql = "INSERT INTO errors (description, steps, category, severity) " +
                             "VALUES (?, ?, ?, ?)";
                             
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, description);
                statement.setString(2, steps);
                statement.setString(3, category);
                statement.setString(4, severity);

                // Execute the query
                int rowsInserted = statement.executeUpdate();

                if (rowsInserted > 0) {
                    logger.info("Error report inserted into the database successfully.");
                    // Store success message in session
                    request.getSession().setAttribute("successMessage", "Your error report has been submitted successfully!");
                    response.sendRedirect("confirmation.jsp"); // Redirect to confirmation page
                } else {
                    logger.warning("No rows were inserted.");
                    request.setAttribute("errorMessage", "An error occurred while submitting your report.");
                    request.getRequestDispatcher("/errorSubmission.jsp").forward(request, response);
                }

                statement.close(); // Explicitly closing the statement
                logger.info("Statement closed.");

            } catch (SQLException e) {
                // Log and handle SQL exceptions
                logger.severe("Database error occurred: " + e.getMessage());
                e.printStackTrace();
                request.setAttribute("errorMessage", "A database error occurred while submitting your report.");
                request.getRequestDispatcher("/errorSubmission.jsp").forward(request, response);
            }

        } catch (ClassNotFoundException e) {
            // Log and handle ClassNotFoundException for JDBC Driver
            logger.severe("JDBC Driver not found: " + e.getMessage());
            e.printStackTrace();
            throw new ServletException("JDBC Driver not found!", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("errorSubmission.jsp");
    }
}
