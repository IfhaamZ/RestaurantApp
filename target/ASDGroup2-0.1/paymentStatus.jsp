<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ page
import="model.Payment" %>
<!-- Import the Payment class -->
<html>
  <head>
    <title>Payment Status</title>
    <!-- Link to external CSS file -->
    <link rel="stylesheet" type="text/css" href="./css/paymentStatus.css" />
  </head>
  <body>
    <div class="container">
      <h1>Payment Status</h1>

      <!-- Display success or error message -->
      <p class="message"><%= request.getAttribute("message") %></p>

      <!-- Check and display payment details if available -->
      <% Payment payment = (Payment) request.getAttribute("payment"); if
      (payment != null) { %>
      <div class="payment-details">
        <h2>Payment Details</h2>
        <p><strong>Method:</strong> <%= payment.getMethod() %></p>

        <!-- Only show card details for "card" payment method -->
        <% if ("card".equalsIgnoreCase(payment.getMethod())) { %>
        <p><strong>Card Number:</strong> <%= payment.getCardNum() %></p>
        <p>
          <strong>Expiry Date:</strong> <%= payment.getExpMonth() %> / <%=
          payment.getExpYear() %>
        </p>
        <% } %>

        <p><strong>Amount:</strong> <%= payment.getPaymentAmount() %></p>
        <p><strong>Date:</strong> <%= payment.getPaymentDate() %></p>
      </div>
      <% } else { %>
      <p class="error">No payment details available.</p>
      <% } %>

      <!-- Return to home button -->
      <a href="index.jsp" class="return-home">Return to Home</a>
    </div>
  </body>
</html>
