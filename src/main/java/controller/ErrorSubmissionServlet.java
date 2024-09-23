package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DBManager;
import model.error;

// @WebServlet("/error/*") // Use a wildcard to handle multiple error-related routes
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

    // Handle POST requests and forward to doGet
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Forwarding POST requests to doGet to handle form submissions similarly
    }

    // Handle GET requests and route based on path
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath(); // Get the path after /error

        try {
            switch (action) {
                case "/submit":
                    submitError(request, response); // Handle error submission
                    break;
                case "/form":
                    showForm(request, response); // Show the error submission form
                    break;
                case "/confirmation":
                    showConfirmation(request, response); // Show confirmation after submission
                    break;
                case "/dashboard": // Handle redirect to the dashboard
                    returnToDashboard(request, response);
                    break;
                case "/viewError": // Handle viewing a specific error
                    viewError(request, response);
                    break;
                case "/update": // Handle updating an error
                    updateError(request, response); // Newly added logic for updating errors
                    break;
                default:
                    listErrors(request, response); // Default action, e.g., show list of errors (if needed)
                    break;
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "SQL error occurred", ex);
            throw new ServletException(ex);
        }
    }

    // Show error submission form
    private void showForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Displaying error submission form.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/errorSubmission.jsp");
        dispatcher.forward(request, response);
    }

    // Show error detail form
    private void viewError(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Displaying error detail form.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewError.jsp");
        dispatcher.forward(request, response);
    }

    // Submit error report
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
            request.setAttribute("errorId", errorId); // Attach the generated error ID to the request

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

    // Handle error report update
    private void updateError(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("Processing error update.");

        // Retrieve form data
        String idStr = request.getParameter("id");
        String description = request.getParameter("description");
        String steps = request.getParameter("steps");
        String category = request.getParameter("category");
        String severity = request.getParameter("severity");
        String staffComments = request.getParameter("staffComments"); // Retrieve staff comments

        // Server-side validation
        if (isInvalidInput(idStr, description, steps, category, severity)) {
            handleValidationError(request, response, idStr, description, steps, category, severity, staffComments);
            return;
        }

        // Proceed to update the error report
        int id = Integer.parseInt(idStr);
        error error = new error(id, description, steps, category, severity, staffComments); // Pass staff comments

        try {
            boolean success = dbManager.updateErrorReport(error); // Include staff comments in the update
            if (success) {
                logger.info("Error report updated successfully with ID: " + id);
                response.sendRedirect("staffDashboard.jsp"); // Redirect back to dashboard
            } else {
                logger.warning("Failed to update error report with ID: " + id);
                handleError(request, response, "Failed to update the error report.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error occurred while updating the error report", e);
            handleError(request, response, "Database error occurred.");
        }
    }

    // Helper method to check if the input is invalid
    private boolean isInvalidInput(String idStr, String description, String steps, String category, String severity) {
        return idStr == null || description == null || steps == null || category == null || severity == null ||
                description.trim().isEmpty() || steps.trim().isEmpty() || category.trim().isEmpty() ||
                severity.trim().isEmpty();
    }

    // Handle validation errors and forward back to the edit form
    private void handleValidationError(HttpServletRequest request, HttpServletResponse response,
            String idStr, String description, String steps,
            String category, String severity, String staffComments)
            throws ServletException, IOException {
        logger.warning("Validation failed: One or more fields are empty.");
        request.setAttribute("errorMessage", "All fields are required.");
        request.setAttribute("id", idStr);
        request.setAttribute("description", description);
        request.setAttribute("steps", steps);
        request.setAttribute("category", category);
        request.setAttribute("severity", severity);
        request.setAttribute("staffComments", staffComments);
        request.getRequestDispatcher("/editError.jsp").forward(request, response);
    }

    // Handle general errors and forward back to the edit form
    private void handleError(HttpServletRequest request, HttpServletResponse response, String errorMessage)
            throws ServletException, IOException {
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("/editError.jsp").forward(request, response);
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
        response.sendRedirect(request.getContextPath() + "/dashboard.jsp"); // Adjust path as needed
    }

    // List submitted errors (not part of original logic, can be used for error
    // listing)
    private void listErrors(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        // Placeholder for future expansion, like listing all submitted errors.
        // For now, it can just redirect to the form
        response.sendRedirect(request.getContextPath() + "/form");
    }
}
