<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>View Stock Levels</title>
    <link rel="stylesheet" href="css/Payment.css" />
  </head>
  <body>
    <h2>Stock Levels</h2>

    <!-- Role Selection Form -->
    <form action="inventory" method="get">
      <label for="role">Select Role:</label>
      <select name="role" id="role">
        <option value="Manager">Manager</option>
        <option value="Staff">Staff</option>
      </select>
      <input type="submit" value="View Stock Levels" />
    </form>

    <!-- Confirmation message -->
    <c:if test="${not empty message}">
      <p style="color: green">${message}</p>
    </c:if>

    <!-- Display stock levels -->
    <table border="1">
      <tr>
        <th>Product Name</th>
        <th>Stock Quantity</th>
      </tr>
      <c:forEach var="entry" items="${stockLevels}">
        <tr>
          <td>${entry.key}</td>
          <td>${entry.value}</td>
        </tr>
      </c:forEach>
    </table>

    <!-- Only Managers can update stock levels -->
    <c:if test="${role == 'Manager'}">
      <h3>Update Stock Levels</h3>
      <form action="inventory" method="post">
        <input type="hidden" name="action" value="update" />
        <label>Product ID: <input type="text" name="productID" /></label><br />
        <label>New Stock: <input type="number" name="newStock" /></label><br />
        <input type="submit" value="Update Stock" />
      </form>
    </c:if>

    <!-- Home Button -->
    <a href="index.jsp" class="home-button">Main Page</a>

    <!-- Low Stock Notification Button -->
    <a
      href="inventory?action=lowStockNotification&role=${role}"
      class="low-stock-button"
      >Check Low Stock Notification</a
    >
  </body>
</html>
