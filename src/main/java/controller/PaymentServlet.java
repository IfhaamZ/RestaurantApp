package controller;

import DAO.DBManager;
import model.Payment;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class PaymentServlet extends HttpServlet {
    private DBManager dbManager;

    public void init() throws ServletException {
        try {
            dbManager = new DBManager();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Use getServletPath instead of getPathInfo to extract the action
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/paymentnew":
                    showPaymentForm(request, response);
                    break;
                case "/paymentinsert":
                    insertPayment(request, response);
                    break;
                case "/paymentstatus":
                    showPaymentStatus(request, response);
                    break;
                case "/paymentcancel":
                    cancelPayment(request, response);
                    break;
                default:
                    showPaymentForm(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    // Show form for creating a new payment
    private void showPaymentForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Payment.jsp");
        dispatcher.forward(request, response);
    }

    private void insertPayment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String method = request.getParameter("method");
        String cardNum = request.getParameter("cardNum");
        String expMonth = request.getParameter("expMonth");
        String expYear = request.getParameter("expYear");
        String cvn = request.getParameter("cvn");
        String paymentAmount = request.getParameter("paymentAmount");
        String staffRole = "customer"; // Assuming customer role here for simplicity

        try {
            Payment payment = new Payment(
                    null, method, cardNum, expMonth, expYear, cvn, paymentAmount, LocalDateTime.now().toString(),
                    staffRole);

            // Insert the payment and get the generated paymentID
            int paymentID = dbManager.createPayment(payment);

            // Fetch the payment from the database to ensure it is passed to the JSP
            Payment savedPayment = dbManager.getPaymentById(paymentID);

            // Set the Payment object and a success message in the request scope
            request.setAttribute("payment", savedPayment);
            request.setAttribute("message", "Payment was successful!");

            // Forward to the payment status page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/paymentStatus.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("paymenterror");
        }
    }

    // Show payment status page
    private void showPaymentStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/paymentStatus.jsp");
        dispatcher.forward(request, response);
    }

    // Cancel a payment
    private void cancelPayment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int paymentID = Integer.parseInt(request.getParameter("paymentID"));
        boolean result = dbManager.cancelPayment(paymentID);
        if (result) {
            response.sendRedirect("paymentstatus");
        } else {
            response.sendRedirect("paymenterror");
        }
    }
}
