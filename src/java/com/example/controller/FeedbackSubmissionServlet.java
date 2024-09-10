package com.example.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.Logger;

// Map this servlet to handle feedback form submissions
@WebServlet("/submitFeedback")
public class FeedbackSubmissionServlet extends HttpServlet {

    // Logger for debugging
    private static final Logger logger = Logger.getLogger(FeedbackSubmissionServlet.class.getName());

    // Email validation pattern
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // Retrieve form data from the request
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String rating = request.getParameter("rating");
        String comments = request.getParameter("comments");

        // Validate email
        if (email == null || !isValidEmail(email)) {
            // Set error message for invalid email and forward back to the form
            request.setAttribute("errorMessage", "Please enter a valid email address.");
            request.getRequestDispatcher("/feedback.jsp").forward(request, response);
            return;
        }

        // Validation: Check that required fields are not empty
        if (rating == null || rating.trim().isEmpty() || comments == null || comments.trim().isEmpty()) {
            // Set error message and forward back to the feedback form
            request.setAttribute("errorMessage", "Please provide both a rating and feedback.");
            request.getRequestDispatcher("/feedback.jsp").forward(request, response);
            return; // Stop further execution
        }

        // Log the form data for debugging
        logger.info("Name: " + name);
        logger.info("Email: " + email);
        logger.info("Rating: " + rating);
        logger.info("Comments: " + comments);

        // TODO: Save the feedback to the database or send it to a relevant service

        // Simulate database logic (replace with actual database operations)

        // Set a success message and forward to the confirmation page
        request.setAttribute("successMessage", "Thank you for your feedback!");
        request.getRequestDispatcher("/feedbackConfirmation.jsp").forward(request, response);
    }

    private boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Redirect GET requests to the feedback form
        response.sendRedirect("feedback.jsp");
    }
}
