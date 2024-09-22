package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DBManager;
import model.error;

@WebServlet("/updateError")
public class UpdateErrorServlet extends HttpServlet {

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

        // Server-side validation to ensure all fields are filled
        if (idStr == null || description == null || steps == null || category == null || severity == null ||
                description.trim().isEmpty() || steps.trim().isEmpty() || category.trim().isEmpty()
                || severity.trim().isEmpty()) {

            // Set error message and forward the request back to edit form
            request.setAttribute("errorMessage", "All fields are required.");
            request.setAttribute("id", idStr);
            request.setAttribute("description", description);
            request.setAttribute("steps", steps);
            request.setAttribute("category", category);
            request.setAttribute("severity", severity);
            request.setAttribute("staffComments", staffComments); // Pass staff comments back to form
            request.getRequestDispatcher("/editError.jsp").forward(request, response);
            return; // Stop further processing if validation fails
        }

        // Proceed to update error report
        int id = Integer.parseInt(idStr);
        error error = new error(id, description, steps, category, severity, staffComments); // Pass staff comments

        DBManager dbManager = new DBManager();
        try {
            boolean success = dbManager.updateErrorReport(error); // Include staff comments in the update
            if (success) {
                response.sendRedirect("staffDashboard.jsp"); // Redirect back to dashboard
            } else {
                request.setAttribute("errorMessage", "Failed to update the error report.");
                request.getRequestDispatcher("/editError.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error occurred.");
            request.getRequestDispatcher("/editError.jsp").forward(request, response);
        }
    }
}
