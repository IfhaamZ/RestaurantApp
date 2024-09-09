package controller;

import model.Payment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        // Create or retrieve the Payment object from the session (if it exists)
        Payment payment = (Payment) request.getSession().getAttribute("payment");

        if (payment == null) {
            payment = new Payment();
        }

        // Set payment details
        payment.setMethod(method);
        payment.setCardNum(cardNum);
        payment.setExpMonth(expMonth);
        payment.setExpYear(expYear);
        payment.setCVN(cvn);
        payment.setPaymentAmount(paymentAmount);
        payment.setPaymentDate(paymentDate);

        // Handle action (either submit or cancel payment)
        if ("cancel".equals(action)) {
            payment.cancelPayment();
            request.getSession().setAttribute("payment", payment);
            request.setAttribute("message", "Payment has been cancelled.");
            request.getRequestDispatcher("paymentStatus.jsp").forward(request, response);
        } else {
            // Process the payment (in real applications, you'd save to a database)
            request.getSession().setAttribute("payment", payment);
            request.setAttribute("message", "Payment processed successfully.");
            request.getRequestDispatcher("paymentStatus.jsp").forward(request, response);
        }
    }
}
