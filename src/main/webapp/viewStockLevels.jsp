<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>View Stock Levels</title>
    <link rel="stylesheet" href="css/ViewStockLevel.css" />
  </head>
  <body>
    <div class="container">
      <h2>Stock Levels</h2>

      <!-- Role Selection Form -->
      <form action="inventory" method="get" class="role-form">
        <label for="role">Select Role:</label>
        <select name="role" id="role">
          <option value="Manager">Manager</option>
          <option value="Staff">Staff</option>
        </select>
      </form>

      <!-- Confirmation message -->
      <c:if test="${not empty message}">
        <p style="color: green">${message}</p>
      </c:if>

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
        <form action="inventory" method="post" class="update-stock-form">
          <input type="hidden" name="action" value="update" />
          <label>Product ID: <input type="text" name="productID" /></label
          ><br />
          <label>New Stock: <input type="number" name="newStock" /></label
          ><br />
          <a href="inventory?action=viewStockDetail" class="form-button">View Stock Detail</a>

          <input type="submit" value="Update Stock" class="form-button" />
        </form>
      </c:if>

      <!-- Home Button -->
      <a
        href="inventory?action=lowStockNotification&role=${role}"
        class="form-button low-stock-button"
        >Check Low Stock Notification</a
      >
      <a href="index.jsp" class="form-button home-button">Main Page</a>
    </div>
  </body>
</html>
