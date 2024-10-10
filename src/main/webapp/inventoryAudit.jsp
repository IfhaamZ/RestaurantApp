<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    // Check if the user is logged in and has the staff role
    User user = (User) session.getAttribute("user");

    // If no user is logged in, or the user doesn't have the "staff" role, redirect to login page
    if (user == null || !user.getRole().equals("staff")) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
<head>
    <title>Inventory Audit Trail</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/inventoryAudit.css" />
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
        <a href="inventoryview" class="form-button">Back to Inventory</a>
    </div>
</body>
</html>
