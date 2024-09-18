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

    // Handle POST requests and forward to doGet
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Forward POST requests to doGet for consistency
    }

    // Handle GET requests and route based on path
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getPathInfo(); // Get the path after /error

        try {
            switch (action) {
                case "/error/submit":
                    submitError(request, response); // Handle error submission
                    break;
                case "/error/form":
                    showForm(request, response); // Show the error submission form
                    break;
                case "/error/confirmation":
                    showConfirmation(request, response); // Show confirmation after submission
                    break;
                default:
                    showForm(request, response); // Default action to show the form
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
        boolean success = dbManager.createErrorReport(description, steps, category, severity);
        if (success) {
            logger.info("Error report inserted into the database successfully.");
            response.sendRedirect(request.getContextPath() + "/error/confirmation"); // Redirect to confirmation page
        } else {
            logger.warning("Insert operation failed. No rows were inserted.");
            request.setAttribute("errorMessage", "Failed to submit your error report. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/errorSubmission.jsp");
            dispatcher.forward(request, response);
        }
    }

    // Show confirmation page
    private void showConfirmation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Displaying confirmation page.");
        RequestDispatcher dispatcher = request.getRequestDispatcher(request.getContextPath() + "/error/confirmation");
        dispatcher.forward(request, response);
    }
}
