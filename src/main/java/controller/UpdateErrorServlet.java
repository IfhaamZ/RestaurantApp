package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DBManager;
import model.error;

@WebServlet("/updateError") // Define the servlet path for updating errors
public class UpdateErrorServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UpdateErrorServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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

        DBManager dbManager = new DBManager();
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

    // Helper method to handle general errors and forward back to the edit form
    private void handleError(HttpServletRequest request, HttpServletResponse response, String errorMessage)
            throws ServletException, IOException {
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("/editError.jsp").forward(request, response);
    }
}
