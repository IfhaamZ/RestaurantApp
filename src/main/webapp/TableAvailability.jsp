<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Table Availability</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/TableAvailability.css">
</head>
<body>
    <header>
        <h1>Available Tables</h1>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="tableavailability">Available Tables</a></li>
                <li><a href="tablelist">Manage Tables</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        <h2>Select a Table to Reserve</h2>
        <div class="table-list">
            <c:forEach var="table" items="${tables}">
                <div class="table-item">
                    <p>Table ID: ${table.id}</p>
                    <p>Seats: ${table.capacity}</p>
                    <!-- Reserve Table -->
                    <form action="tablereserveform" method="get" style="display:inline;">
                        <input type="hidden" name="tableId" value="${table.id}">
                        <button type="submit" class="btn-reserve">Reserve</button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
