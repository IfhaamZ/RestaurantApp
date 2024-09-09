<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Low Stock Notification</title>
  </head>
  <body>
    <h2>Low Stock Notification</h2>

    <!-- Display the low stock message if there are low stock items -->
    <c:if test="${not empty lowStockMessage}">
      <pre>${lowStockMessage}</pre>
    </c:if>

    <!-- Display message if all stock levels are sufficient -->
    <c:if test="${empty lowStockMessage}">
      <p>All stock levels are sufficient.</p>
    </c:if>

    <!-- Button to go back to view stock levels -->
    <a href="inventory?action=view" class="home-button"
      >Back to View Stock Levels</a
    >
  </body>
</html>
