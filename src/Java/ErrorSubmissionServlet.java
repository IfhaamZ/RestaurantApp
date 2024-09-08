package com.example.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/submitError")
public class ErrorSubmissionServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ErrorSubmissionServlet.class.getName());

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

        try {
            // Simulate database logic (replace with actual DB operations)
            // Example: Save the form data to a database (via JDBC or ORM)

            // If successful, set the success message and forward to confirmation.jsp
            request.setAttribute("successMessage", "Your error report has been submitted successfully!");
            logger.info("Error report submitted successfully");

            // Forward to confirmation page
            request.getRequestDispatcher("/confirmation.jsp").forward(request, response);

        } catch (Exception e) {
            logger.severe("An error occurred while processing the error report: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("errorMessage", "An unexpected error occurred while submitting your report.");
            request.getRequestDispatcher("/errorSubmission.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("errorSubmission.jsp");
    }
}
