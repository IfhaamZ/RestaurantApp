<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Stock Details</title>
    <link rel="stylesheet" href="css/ViewStockLevel.css" />
  </head>
  <body>
    <div class="container">
      <h2>Current Stock Details</h2>

      <!-- Table to display stock details -->
      <table class="stock-table">
        <tr>
          <th>Product Name</th>
          <th>Stock Quantity</th>
        </tr>
        <c:forEach var="entry" items="${stockDetails}">
          <tr>
            <td>${entry.key}</td>
            <td>${entry.value}</td>
          </tr>
        </c:forEach>
      </table>

      <!-- Button to go back to main page -->
      <a href="inventory?action=view" class="form-button"
        >Back to Stock Levels</a
      >
    </div>
  </body>
</html>
