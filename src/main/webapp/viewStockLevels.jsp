<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>View Stock Levels</title>
  </head>
  <body>
    <h2>Stock Levels</h2>
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
  </body>
</html>
