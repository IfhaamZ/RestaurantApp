package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DBManager;
import model.error;

public class ErrorSubmissionServlet extends HttpServlet {
    // Logger to log important events
    private static final Logger logger = Logger.getLogger(ErrorSubmissionServlet.class.getName());
    private DBManager dbManager;

    // Initialize the servlet and set up DBManager
    @Override
    public void init() throws ServletException {
        try {
            dbManager = new DBManager(); // Initialize DBManager
            logger.info("DBManager initialized successfully.");
        } catch (Exception e) {
            // Log initialization failure
            logger.log(Level.SEVERE, "Error initializing DBManager", e);
            throw new ServletException("Error initializing DBManager", e);
        }
    }

    // Handle POST requests and forward to doGet
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Forwarding POST requests to doGet to handle form submissions similarly
    }

    // Handle GET requests and route based on path
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
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
                case "/update": // Handles updating an error report
                    updateError(request, response);
                    break;
                case "/staffDashboard": // Displays the staff dashboard
                    showStaffDashboard(request, response);
                    break;
                case "/mainStaffDashboard": // Redirects to the main staff dashboard
                    returnToStaffDashboard(request, response);
                    break;
                case "/updateErrorStatus": // Handles updating the error status
                    updateErrorStatus(request, response); // New logic for status update
                    break;
                case "/deleteError": // Handle deletion of an error report
                    deleteError(request, response); // Include the deletion logic
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
        String status = request.getParameter("status");
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
        // Generate the current timestamp
        Timestamp createdAt = Timestamp.from(Instant.now());

        // Delegate database operation to DBManager
        try {
            int errorId = dbManager.createErrorReportAndReturnID(description, steps, category, severity, createdAt,
                    status);
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
        String status = request.getParameter("status"); // Retrieve status
        // Server-side validation
        if (isInvalidInput(idStr, description, steps, category, severity)) {
            handleValidationError(request, response, idStr, description, steps, category, severity, staffComments);
            return;
        }

        // Proceed to update the error report
        int id = Integer.parseInt(idStr);
        // Update the constructor call in updateError
        error error = new error(id, description, steps, category, severity, staffComments, null, status);
        // Pass staff comments

        try {
            boolean success = dbManager.updateErrorReport(error); // Include staff comments in the update
            if (success) {
                logger.info("Error report updated successfully with ID: " + id);
                response.sendRedirect("/staffDashboard.jsp"); // Redirect back to dashboard
            } else {
                logger.warning("Failed to update error report with ID: " + id);
                handleError(request, response, "Failed to update the error report.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error occurred while updating the error report", e);
            handleError(request, response, "Database error occurred.");
        }
    }

    // Handle error report deletion
    private void deleteError(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        logger.info("Processing error report deletion.");

        // Retrieve error ID from the request
        String errorIdStr = request.getParameter("errorId");

        if (errorIdStr == null || errorIdStr.isEmpty()) {
            logger.warning("No error ID provided for deletion.");
            request.setAttribute("errorMessage", "No error ID provided.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/viewError.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            int errorId = Integer.parseInt(errorIdStr);

            // Call DBManager to delete the error report
            boolean isDeleted = dbManager.deleteErrorById(errorId);

            if (isDeleted) {
                logger.info("Error report with ID " + errorId + " deleted successfully.");
                request.setAttribute("successMessage", "Error report deleted successfully.");
                // Forward to the new errorDeleted.jsp page
                RequestDispatcher dispatcher = request.getRequestDispatcher("/errorDeleted.jsp");
                dispatcher.forward(request, response);
            } else {
                logger.warning("Failed to delete error report with ID " + errorId);
                request.setAttribute("errorMessage", "Error report deletion failed.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/viewError.jsp");
                dispatcher.forward(request, response);
            }
        } catch (NumberFormatException e) {
            logger.warning("Invalid error ID format: " + errorIdStr);
            request.setAttribute("errorMessage", "Invalid error ID format.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/viewError.jsp");
            dispatcher.forward(request, response);
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

    // Show staff dashboard
    private void showStaffDashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Displaying staff dashboard.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/staffDashboard.jsp");
        dispatcher.forward(request, response);
    }

    // Redirect to the dashboard
    private void returnToStaffDashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Displaying staff dashboard.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/mainStaffDashboard.jsp");
        dispatcher.forward(request, response);
    }

    // Add method to handle the status update
    private void updateErrorStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Processing error status update.");

        // Retrieve the error ID and new status from the request
        String errorIdStr = request.getParameter("id");
        String status = request.getParameter("status");

        if (errorIdStr == null || errorIdStr.isEmpty() || status == null || status.isEmpty()) {
            logger.warning("Invalid status update: No error ID or status provided.");
            request.setAttribute("errorMessage", "Invalid error ID or status.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/staffDashboard.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            int errorId = Integer.parseInt(errorIdStr);

            // Update the status in the database
            boolean isUpdated = dbManager.updateErrorStatus(errorId, status);

            if (isUpdated) {
                logger.info("Error status updated successfully for ID: " + errorId);
                request.setAttribute("successMessage", "Error status updated successfully.");
                response.sendRedirect(request.getContextPath() + "/staffDashboard"); // Redirect back to dashboard
            } else {
                logger.warning("Failed to update error status for ID: " + errorId);
                request.setAttribute("errorMessage", "Error status update failed.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/staffDashboard.jsp");
                dispatcher.forward(request, response);
            }
        } catch (NumberFormatException e) {
            logger.warning("Invalid error ID format: " + errorIdStr);
            request.setAttribute("errorMessage", "Invalid error ID format.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/staffDashboard.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error occurred while updating error status", e);
            request.setAttribute("errorMessage", "Database error occurred.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/staffDashboard.jsp");
            dispatcher.forward(request, response);
        }
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
