package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Handle POST requests the same way as GET requests
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath(); // Get the action from the request path
        try {
            // Route to the appropriate method based on the action
            switch (action) {
                case "/error":
                    handleErrorRequest(request, response); // Show the dashboard
                    break;
                default:
                    showDefaultPage(request, response); // Handle other or invalid routes
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    // Show a default or error page (optional)
    private void handleErrorRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ErrorSubmissionServlet");
        dispatcher.forward(request, response);
    }

    // Show a default or error page (optional)
    private void showDefaultPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found.");
    }
}