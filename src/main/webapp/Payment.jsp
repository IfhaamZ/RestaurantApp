<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Manage Payment</title>
    <link rel="stylesheet" href="styles/Payment.css" />
    <!-- Link to the CSS file -->
  </head>
  <body>
    <div class="container">
      <h2>Payment Form</h2>

      <form action="managePayment" method="post">
        <div class="form-group">
          <label for="method">Payment Method:</label>
          <select id="method" name="method" required>
            <option value="card">Credit Card</option>
            <option value="cash">Cash</option>
          </select>
        </div>

        <div id="card-details" class="form-group">
          <label for="cardNum">Card Number:</label>
          <input
            type="text"
            id="cardNum"
            name="cardNum"
            placeholder="Enter Card Number"
            pattern="\d{16}"
            title="Please enter a valid 16-digit card number."
          />
        </div>

        <div id="exp-details" class="form-row">
          <div class="form-group">
            <label for="expMonth">Expiry Month:</label>
            <input
              type="text"
              id="expMonth"
              name="expMonth"
              placeholder="MM"
              pattern="\d{2}"
              title="Please enter a valid 2-digit month."
            />
          </div>
          <div class="form-group">
            <label for="expYear">Expiry Year:</label>
            <input
              type="text"
              id="expYear"
              name="expYear"
              placeholder="YY"
              pattern="\d{2}"
              title="Please enter a valid 2-digit year."
            />
          </div>
          <div class="form-group">
            <label for="cvn">CVN:</label>
            <input
              type="text"
              id="cvn"
              name="cvn"
              placeholder="CVN"
              pattern="\d{3}"
              title="Please enter a valid 3-digit CVN."
            />
          </div>
        </div>

        <div class="form-group">
          <label for="paymentAmount">Payment Amount:</label>
          <input
            type="number"
            id="paymentAmount"
            name="paymentAmount"
            placeholder="Enter Amount"
            required
          />
        </div>

        <div class="form-group">
          <label for="paymentDate">Payment Date:</label>
          <input type="date" id="paymentDate" name="paymentDate" value="<%= new
          java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date())
          %>" readonly>
        </div>

        <div class="form-actions">
          <button type="submit" name="action" value="submit" class="submit-btn">
            Submit Payment
          </button>
          <button type="submit" name="action" value="cancel" class="cancel-btn">
            Cancel Payment
          </button>
        </div>
      </form>

      <% // Display a message if available (for example, after a successful or
      canceled payment) String message = (String)
      request.getAttribute("message"); if (message != null) { %>
      <p class="message"><%= message %></p>
      <% } %>
    </div>

    <script>
      // Toggle card details visibility based on the selected payment method
      document.getElementById("method").addEventListener("change", function () {
        const cardDetails = document.getElementById("card-details");
        const expDetails = document.getElementById("exp-details");
        if (this.value === "cash") {
          cardDetails.style.display = "none";
          expDetails.style.display = "none";
        } else {
          cardDetails.style.display = "block";
          expDetails.style.display = "flex";
        }
      });
    </script>
  </body>
</html>
