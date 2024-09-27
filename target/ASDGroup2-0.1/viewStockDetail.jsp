<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Current Stock Details</title>
    <link rel="stylesheet" href="css/ViewStockLevel.css" />
  </head>
  <body>
    <div class="container">
      <h2>Current Stock Details</h2>

      <!-- Stock table -->
      <table class="stock-table">
        <tr>
          <th>Product Name</th>
          <th>Stock Quantity</th>
        </tr>

        <!-- Iterate over the stock details and display them -->
        <c:forEach var="entry" items="${stockDetails}">
          <tr>
            <td>${entry.key}</td>
            <td>${entry.value}</td>
          </tr>
        </c:forEach>
      </table>

      <!-- Back button -->
      <a href="inventory?action=view&role=${role}" class="form-button"
        >Back to Stock Levels</a
      >
    </div>
  </body>
</html>
