package controller;

import model.Payment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DBManager;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet("/managePayment")
public class PaymentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String method = request.getParameter("method");
        String cardNum = request.getParameter("cardNum");
        String expMonth = request.getParameter("expMonth");
        String expYear = request.getParameter("expYear");
        String cvn = request.getParameter("cvn");
        String paymentAmount = request.getParameter("paymentAmount");
        String paymentDate = request.getParameter("paymentDate");
        String action = request.getParameter("action"); // "cancel" or "submit"

        // DBManager instance
        DBManager dbManager = new DBManager();

        try {
            // Check if the payment already exists in the session
            Payment payment = (Payment) request.getSession().getAttribute("payment");

            if (payment == null) {
                // If no payment exists in the session, create a new Payment object
                payment = new Payment(null, method, cardNum, expMonth, expYear, cvn, paymentAmount, paymentDate, null);
            }

            // Set payment details
            payment.setMethod(method);
            payment.setCardNum(cardNum); // Encrypt the cardNum here
            payment.setExpMonth(expMonth);
            payment.setExpYear(expYear);
            payment.setCVN(cvn); // Encrypt the CVN here
            payment.setPaymentAmount(paymentAmount);
            payment.setPaymentDate(paymentDate);

            if ("cancel".equals(action)) {
                payment.cancelPayment();
                request.setAttribute("message", "Payment has been cancelled.");
            } else {
                dbManager.createPayment(payment);
                payment.processPayment();
                request.setAttribute("message", "Payment processed successfully.");
            }

            // Update session and redirect to status page
            request.getSession().setAttribute("payment", payment);
            request.getRequestDispatcher("paymentStatus.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "There was an issue processing the payment.");
            request.getRequestDispatcher("paymentError.jsp").forward(request, response);
        }
    }
}
