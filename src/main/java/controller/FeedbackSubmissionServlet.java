package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DBManager;
import model.feedback;

public class FeedbackSubmissionServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(FeedbackSubmissionServlet.class.getName());
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

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Forward POST requests to doGet for uniform handling
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        try {
            switch (action) {
                case "/feedbackSubmit":
                    submitFeedback(request, response);
                    break;
                case "/formSubmit":
                    showForm(request, response); // Show the feedback submission form
                    break;
                case "/feedbackConfirmation":
                    showConfirmation(request, response);
                    break;
                case "/backDashboard": // Handle redirect to the dashboard
                    backToDashboard(request, response);
                    break;
                case "/viewFeedback": // Handle feedback view for staff
                    viewFeedback(request, response);
                    break;
                case "/submitStaffFeedbackResponse": // Handle feedback update from staff
                    submitStaffFeedbackResponse(request, response);
                    break;
                case "/viewCustomerFeedback": // Handle feedback view for customer
                    viewCustomerFeedback(request, response);
                    break;
                case "/lookupFeedback": // Handle customer lookup of feedback by ID
                    lookupFeedback(request, response);
                    break;
                case "/updateFeedback":
                    updateFeedback(request, response);
                    break;
                case "/feedbackDashboard":
                    showFeedback(request, response);
                    break;
                case "/StaffDashboard":
                    returnFeedbackDashboard(request, response);
                    break;
                default:
                    showForm(request, response);
                    break;
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "SQL error occurred", ex);
            throw new ServletException(ex);
        }
    }

    // Show feedback submission form
    private void showForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Displaying feedback submission form.");
        forwardRequest(request, response, "/feedback.jsp");
    }

    // Submit feedback report
    private void submitFeedback(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        logger.info("Processing feedback submission.");

        // Retrieve form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String rating = request.getParameter("rating");
        String comments = request.getParameter("comments");

        // Validate required fields
        if (isInvalidInput(name, email, rating, comments)) {
            logger.warning("Validation failed: One or more fields are empty or invalid.");
            request.setAttribute("errorMessage", "All fields are required and email must be valid.");
            forwardRequest(request, response, "/feedback.jsp");
            return;
        }

        // Delegate database operation to DBManager
        try {
            int feedbackId = dbManager.createFeedbackAndReturnID(name, email, comments, Integer.parseInt(rating));
            logger.info("Feedback submitted successfully with ID: " + feedbackId);

            // Set success message and feedback ID for tracking
            request.setAttribute("successMessage", "Thank you for your feedback!");
            request.setAttribute("feedbackId", feedbackId);

            // Redirect to confirmation page
            forwardRequest(request, response, "/feedbackConfirmation.jsp");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error occurred while submitting feedback", e);
            request.setAttribute("errorMessage", "Failed to submit your feedback. Please try again.");
            forwardRequest(request, response, "/feedback.jsp");
        }
    }

    // Validate input
    private boolean isInvalidInput(String name, String email, String rating, String comments) {
        return name == null || name.trim().isEmpty() ||
                email == null || !isValidEmail(email) ||
                rating == null || rating.trim().isEmpty() ||
                comments == null || comments.trim().isEmpty();
    }

    // Email validation
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    // Show confirmation page
    private void showConfirmation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Displaying feedback confirmation page.");
        forwardRequest(request, response, "/feedbackConfirmation.jsp");
    }

    // Redirect back to dashboard
    private void backToDashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Redirecting to the dashboard.");
        response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
    }

    // Staff access: view all feedback
    private void viewFeedback(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        logger.info("Displaying all feedback for staff.");

        List<feedback> feedbackList = dbManager.getAllFeedback();
        request.setAttribute("feedbackList", feedbackList);

        forwardRequest(request, response, "/viewFeedback.jsp");
    }

    // Staff access: update feedback with staff responses for multiple feedbacks
    public void submitStaffFeedbackResponse(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        logger.info("Processing staff feedback responses.");

        // Retrieve the list of feedback entries from the database
        List<feedback> feedbackList = dbManager.getAllFeedback();

        // Loop through all feedback entries to retrieve corresponding staff responses
        for (feedback fb : feedbackList) {
            String staffResponse = request.getParameter("staffResponse_" + fb.getFeedbackId());

            if (staffResponse != null && !staffResponse.trim().isEmpty()) {
                // Update staff response in the database
                dbManager.updateFeedbackWithResponse(fb.getFeedbackId(), staffResponse);
            }
        }

        // After processing, redirect staff back to the feedback view page
        response.sendRedirect(request.getContextPath() + "/viewFeedback");
    }

    // Customer view: view specific feedback by ID
    private void viewCustomerFeedback(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        logger.info("Displaying customer feedback.");

        // Retrieve feedback ID from the request
        String feedbackIdStr = request.getParameter("id");
        if (feedbackIdStr == null || feedbackIdStr.isEmpty()) {
            logger.warning("No feedback ID provided for customer view.");
            request.setAttribute("errorMessage", "No feedback ID provided.");
            forwardRequest(request, response, "/lookupFeedback.jsp");
            return;
        }

        int feedbackId;
        try {
            feedbackId = Integer.parseInt(feedbackIdStr);
        } catch (NumberFormatException e) {
            logger.warning("Invalid feedback ID format: " + feedbackIdStr);
            request.setAttribute("errorMessage", "Invalid feedback ID format.");
            forwardRequest(request, response, "/lookupFeedback.jsp");
            return;
        }

        // Fetch feedback from the database using the feedback ID
        feedback fb = dbManager.getFeedbackById(feedbackId);

        if (fb == null) {
            logger.warning("Feedback with ID " + feedbackId + " not found.");
            request.setAttribute("errorMessage", "Feedback with ID " + feedbackId + " not found.");
            forwardRequest(request, response, "/lookupFeedback.jsp");
            return;
        }

        // If feedback exists, set the feedback attribute and forward to the display
        // page
        request.setAttribute("feedback", fb);
        forwardRequest(request, response, "/viewCustomerFeedback.jsp");
    }

    // Helper method to forward request
    private void forwardRequest(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    // Redirect to the dashboard
    private void returnFeedbackDashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Displaying staff dashboard.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/mainStaffDashboard.jsp");
        dispatcher.forward(request, response);
    }

    // New method to handle customer lookup
    private void lookupFeedback(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Redirecting to feedback lookup page.");
        forwardRequest(request, response, "/lookupFeedback.jsp");
    }

    // New method to handle customer lookup
    private void showFeedback(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("Redirecting to Staff feedback page.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewFeedback.jsp");
        dispatcher.forward(request, response);
    }

    // Staff access: update feedback with new staff responses for a single feedback
    // entry
    private void updateFeedback(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        logger.info("Processing update feedback.");

        // Retrieve form data from the request
        int feedbackId = Integer.parseInt(request.getParameter("id"));
        String staffResponse = request.getParameter("staffResponse");

        // Validate required fields
        if (staffResponse == null || staffResponse.trim().isEmpty()) {
            logger.warning("Validation failed: Staff response is empty.");
            request.setAttribute("errorMessage", "Staff response is required.");
            forwardRequest(request, response, "/editFeedback.jsp");
            return;
        }

        // Update feedback with staff response
        try {
            dbManager.updateFeedbackWithResponse(feedbackId, staffResponse);
            logger.info("Feedback updated successfully with ID: " + feedbackId);

            // Redirect to the feedback dashboard after successful update
            response.sendRedirect(request.getContextPath() + "/viewFeedback");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error occurred while updating feedback", e);
            request.setAttribute("errorMessage", "Failed to update feedback. Please try again.");
            forwardRequest(request, response, "/editFeedback.jsp");
        }
    }
}
