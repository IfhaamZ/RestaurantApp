package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DBManager;

public class ErrorSubmissionServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ErrorSubmissionServlet.class.getName());
    private DBManager dbManager;

    @Override
    public void init() throws ServletException {
        try {
            dbManager = new DBManager(); // Initialize DBManager
            logger.info("DBManager initialized successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error initializing DBManager", e);
            throw new ServletException("Error initializing DBManager", e);
        }
    }

    // Handle POST requests by forwarding to doGet
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    // Handle GET requests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo(); // Get the path after /error

        try {
            switch (action) {
                case "/submit":
                    submitError(request, response); // Handle error submission
                    break;
                case "/form":
                    showForm(request, response); // Show error submission form
                    break;
                case "/confirmation":
                    showConfirmation(request, response); // Show confirmation page
                    break;
                case "/dashboard":
                    returnToDashboard(request, response); // Redirect to dashboard
                    break;
                default:
                    showForm(request, response); // Default action
                    break;
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "SQL error occurred", ex);
            throw new ServletException(ex);
        }
    }

    // Show the error submission form
    private void showForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Displaying error submission form.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/errorSubmission.jsp");
        dispatcher.forward(request, response);
    }

    // Submit the error report
    private void submitError(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        logger.info("Processing error submission.");

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

            logger.warning("Validation failed: One or more fields are empty.");
            request.setAttribute("errorMessage", "All fields are required.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/errorSubmission.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Delegate database operation to DBManager
        try {
            int errorId = dbManager.createErrorReportAndReturnID(description, steps, category, severity);
            logger.info("Error report inserted into the database successfully with ID: " + errorId);

            // Set success message and error ID for tracking
            request.setAttribute("successMessage", "Your error report was successfully submitted.");
            request.setAttribute("errorId", errorId);

            // Redirect to confirmation page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/confirmation.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error occurred while submitting error report", e);
            request.setAttribute("errorMessage", "Failed to submit your error report. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/errorSubmission.jsp");
            dispatcher.forward(request, response);
        }
    }

    // Show confirmation page
    private void showConfirmation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Displaying confirmation page.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/confirmation.jsp");
        dispatcher.forward(request, response);
    }

    // Redirect to the dashboard
    private void returnToDashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Redirecting to the dashboard.");
        response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
    }
}
