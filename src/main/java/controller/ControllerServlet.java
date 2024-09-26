package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handle POST requests by forwarding to doGet
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    // Handle GET requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the action from the request path
        String action = request.getServletPath();

        try {
            // Route to appropriate method based on action
            switch (action) {
                case "/event": // Handle event-related requests
                    handleEventRequests(request, response);
                    break;
                case "/table": // Handle table management requests
                    handleTableRequests(request, response); // New case for handling table-related requests
                    break;
                case "/menu": // Handle menu-related requests
                    handleMenuRequests(request, response); // New case for handling table-related requests
                    break;
                case "/error": // Handle error submission requests
                    handleErrorRequest(request, response); // Show the dashboard
                    break;
                case "/feedback": // Handle feedback submission requests
                    handleFeedbackRequest(request, response); // Show the dashboard
                    break;
                default: // If no specific action is found, show the default page
                    showDefaultPage(request, response);
                    break;
            }
        } catch (Exception ex) {
            // Handle any exceptions that occur during the request processing
            throw new ServletException(ex);
        }
    }

    // Handle requests related to event management
    private void handleEventRequests(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/EventServlet");
        dispatcher.forward(request, response);
    }

    // Handle requests related to table management
    private void handleTableRequests(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/TableServlet"); // Forwarding to TableServlet
        dispatcher.forward(request, response);
    }

    // Show a default or error page (optional)
    private void handleErrorRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ErrorSubmissionServlet");
        dispatcher.forward(request, response);
    }

    // Show a default or error page (optional)
    private void handleFeedbackRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/FeedbackSubmissionServlet");
        dispatcher.forward(request, response);
    }

    // Handle requests related to menu management
    private void handleMenuRequests(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/MenuServlet"); // Forwarding to TableServlet
        dispatcher.forward(request, response);
    }

    // Show the default page (index.jsp)
    private void showDefaultPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
