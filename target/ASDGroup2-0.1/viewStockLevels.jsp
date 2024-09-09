<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>View Stock Levels</title>
    <link rel="stylesheet" href="css/Payment.css" />
  </head>
  <body>
    <h2>Stock Levels</h2>
    <!-- Confirmation message -->
    <c:if test="${not empty message}">
      <p style="color: green">${message}</p>
    </c:if>

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
    <form action="inventory" method="get">
      <input type="hidden" name="action" value="update" />
      <label>Product ID: <input type="text" name="productID" /></label><br />
      <label>New Stock: <input type="number" name="newStock" /></label><br />
      <input type="submit" value="Update Stock" />
    </form>

    <!-- Home Button -->
    <a href="index.jsp" class="home-button">Main Page</a>

    <!-- Low Stock Notification Button -->
    <a href="lowStockNotification.jsp" class="low-stock-button"
      >Check Low Stock Notification</a
    >
  </body>
</html>
