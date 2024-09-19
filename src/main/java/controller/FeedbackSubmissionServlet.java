package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        // Database connection setup (update with your database credentials)
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant_db", "root", "Karthi");

            // Insert feedback data into the feedback table
            String sql = "INSERT INTO feedback (customer_name, customer_email, feedback_text, rating) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, comments);
            statement.setInt(4, Integer.parseInt(rating));

            // Execute the insert statement
            statement.executeUpdate();

            // Set a success message and forward to the confirmation page
            request.setAttribute("successMessage", "Thank you for your feedback!");
            request.getRequestDispatcher("/feedbackConfirmation.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "There was an error saving your feedback. Please try again later.");
            request.getRequestDispatcher("/feedback.jsp").forward(request, response);
        } finally {
            // Close resources
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Helper method to validate email format
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
