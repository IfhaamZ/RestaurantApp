<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>
<html>
  <head>
    <title>View Stock Levels</title>
    <link rel="stylesheet" href="css/ViewStockLevel.css" />
  </head>
  <body>
    <div class="container">
      <h2>Stock Levels</h2>

      <!-- Get the logged-in user from the session -->
      <c:set var="loggedInUser" value="${sessionScope.user}" />
      <c:set var="role" value="${loggedInUser.role}" />

      <!-- Confirmation message -->
      <c:if test="${not empty message}">
        <p style="color: green">${message}</p>
      </c:if>

      <table>
        <c:forEach var="entry" items="${stockLevels}">
          <tr>
            <td>${entry.key}</td>
            <td>${entry.value}</td>
          </tr>
        </c:forEach>
      </table>

      <!-- Only Staff can update stock levels -->
      <c:if test="${role == 'staff'}">
        <form action="inventoryupdate" method="post" class="update-stock-form">
          <input type="hidden" name="action" value="update" />
          <label>Product ID: 
            <input type="text" name="productID" pattern="^[Pp]\d+$" required title="Product ID must start with 'P' or 'p' followed by digits" />
          </label><br />
          
          <label>New Stock: 
            <input type="number" name="newStock" min="1" required title="Please enter a positive stock quantity." />
          </label><br />
          
          <input type="submit" value="Update Stock" class="form-button" />
        </form>

        <!-- Button to Check Inventory Usage Trend (S126) -->
        <h3>View Inventory Usage Trend</h3>
        <form action="inventoryviewAuditTrail" method="get">
          <input type="hidden" name="action" value="viewAuditTrail" />
          <label>Product ID: 
            <input type="text" name="productID" pattern="^[Pp]\d+$" required title="Product ID must start with 'P' or 'p' followed by digits" />
          </label><br />
          <input type="submit" value="View Usage Trend" class="form-button" />
        </form>
      </c:if>

      <!-- Home Button -->
      <a href="inventorylowStockNotification" class="form-button low-stock-button">Check Low Stock Notification</a>
      <a href="index.jsp" class="form-button home-button">Main Page</a>
    </div>
  </body>
</html>
