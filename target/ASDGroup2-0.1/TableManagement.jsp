<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Table" %>
<html>
<head>
    <title>Table Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/TableManagement.css">
</head>
<body>
    <header>
        <h1>Table Management</h1>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        <h1>Manage Tables</h1>

        <!-- Add Table Button -->
        <div class="add-table-button-container">
            <a href="tablenew" class="btn-add-table">Add Table</a>
        </div>

        <div class="table-list">
            <c:forEach var="table" items="${tables}">
                <div class="table-item">
                    <h3>🍽️ Table: ${table.id}</h3>
                    <p>Status: ${table.status}</p>
                    <p>Seats: ${table.capacity}</p>

                    <!-- Display reservation details if the table is reserved -->
                    <c:if test="${table.status == 'Reserved'}">
                        <p><strong>Reserved By:</strong> ${table.reservedByName}</p>
                        <p><strong>Contact:</strong> ${table.reservedByPhone}</p>
                        <p><strong>Email:</strong> ${table.reservedByEmail}</p>
                    </c:if>

                    <!-- Edit Table -->
                    <form action="tableedit" method="get" style="display:inline;">
                        <input type="hidden" name="tableId" value="${table.id}">
                        <button type="submit" class="btn-edit">Edit</button>
                    </form>

                    <!-- Delete Table -->
                    <form action="tabledelete" method="post" style="display:inline;">
                        <input type="hidden" name="tableId" value="${table.id}">
                        <button type="submit" class="btn-delete" onclick="return confirm('Are you sure you want to delete this table?')">Delete</button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
