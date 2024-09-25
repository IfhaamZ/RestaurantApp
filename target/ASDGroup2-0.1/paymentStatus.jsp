<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ page
import="model.Payment" %>
<!-- Import the Payment class -->
<html>
  <head>
    <title>Payment Status</title>
    <link rel="stylesheet" type="text/css" href="./css/paymentStatus.css" />
    <style>
      body {
        font-family: Arial, sans-serif;
        margin: 20px;
      }
      .message {
        font-size: 20px;
        color: green;
      }
      .error {
        font-size: 20px;
        color: red;
      }
      .payment-details {
        font-size: 18px;
        margin-top: 20px;
      }
    </style>
  </head>
  <body>
    <h1>Payment Status</h1>

    <!-- Display message -->
    <p class="message"><%= request.getAttribute("message") %></p>

    <!-- Retrieve and display payment details if available -->
    <% Payment payment = (Payment) request.getAttribute("payment"); if (payment
    != null) { %>
    <div class="payment-details">
      <h2>Payment Details</h2>
      <p><strong>Method:</strong> <%= payment.getMethod() %></p>

      <!-- Only display card details if the payment method is "card" -->
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

    <a href="index.jsp">Return to Home</a>
  </body>
</html>
