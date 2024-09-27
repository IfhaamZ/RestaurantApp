<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
  <head>
    <title>Current Stock Details</title>
    <link rel="stylesheet" href="css/ViewStockLevel.css" />
  </head>
  <body>
    <div class="container">
      <h2>Current Stock Details</h2>

      <!-- Debugging stockDetails and role -->
      <p>Debugging Information:</p>
      <p>
        Stock details attribute: <%= request.getAttribute("stockDetails") %>
      </p>
      <p>Role: <%= request.getAttribute("role") %></p>

      <!-- Check if stockDetails is null or empty -->
      <c:choose>
        <c:when test="${not empty stockDetails}">
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
        </c:when>
        <c:otherwise>
          <p>
            No stock details available. StockDetails might be null or empty.
          </p>
        </c:otherwise>
      </c:choose>

      <!-- Back button -->
      <a href="inventory?action=view&role=${role}" class="form-button"
        >Back to Stock Levels</a
      >
    </div>
  </body>
</html>
