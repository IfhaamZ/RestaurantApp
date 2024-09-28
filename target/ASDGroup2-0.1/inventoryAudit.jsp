<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Inventory Audit Trail</title>
    <link rel="stylesheet" href="css/inventoryAudit.css" />
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  </head>
  <body>
    <div class="container">
      <h2>Inventory Audit Trail for Product ID: ${param.productID}</h2>

      <table class="audit-table">
        <tr>
          <th>Old Stock</th>
          <th>New Stock</th>
          <th>Timestamp</th>
          <th>Updated By</th>
        </tr>

        <c:forEach var="audit" items="${auditList}">
          <tr>
            <td>${audit.oldStock}</td>
            <td>${audit.newStock}</td>
            <td>${audit.timestamp}</td>
            <td>${audit.updatedBy}</td>
          </tr>
        </c:forEach>
      </table>

      <!-- Back to Product List or Inventory -->
      <a href="inventory">Back to Inventory</a>
    </div>
  </body>
</html>
