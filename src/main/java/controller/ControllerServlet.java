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
                case "/payment":
                    handlePaymentRequests(request, response);
                    break;
                case "/inventory":
                    handleInventoryRequests(request, response);
                    break;
                case "/user":
                    handleUserRequests(request, response);
                    break;
                case "/reservation":
                    handleReservationRequests(request, response);
                    break;
                default:
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

    private void handleReservationRequests(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 전달할 action 값을 설정
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            action = "list"; // 기본값 설정 (필요에 따라 수정)
        }

        // `action` 파라미터를 `ReservationServlet`으로 전달
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ReservationServlet?action=" + action);
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

    private void handleUserRequests(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/UserServlet");
        dispatcher.forward(request, response);
    }

    // Handle requests related to menu management
    private void handleMenuRequests(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/MenuServlet"); // Forwarding to TableServlet
        dispatcher.forward(request, response);
    }

    // Handle requests related to inventory management
    private void handleInventoryRequests(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/InventoryServlet");
        dispatcher.forward(request, response);
    }

    private void handlePaymentRequests(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/PaymentServlet");
        dispatcher.forward(request, response);
    }

    // Show the default page (login.jsp)
    private void showDefaultPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("userlogin");
        dispatcher.forward(request, response);
    }
}
