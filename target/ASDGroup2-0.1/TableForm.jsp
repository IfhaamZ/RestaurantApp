<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.Table" %>
<html>
<head>
    <title>Manage Table</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/TableForm.css">
</head>
<body>
    <header>
        <h1>${table == null ? "Add Table" : "Edit Table"}</h1>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="tablelist">Manage Tables</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        <h2>${table == null ? "Add a New Table" : "Update Table"}</h2>
        <form action="${table == null ? 'tableinsert' : 'tableupdate'}" method="post">
            <c:choose>
                <c:when test="${table != null}">
                    <input type="hidden" name="tableId" value="${table.id}">
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="tableId" value="">
                </c:otherwise>
            </c:choose>

            <label for="capacity">Table Seats:</label>
            <input type="number" id="capacity" name="capacity" min="1" required value="${table != null ? table.capacity : ''}">

            <label for="status">Status:</label>
            <select id="status" name="status" required>
                <option value="Available" ${table != null && table.status == 'Available' ? 'selected' : ''}>Available</option>
                <option value="Occupied" ${table != null && table.status == 'Occupied' ? 'selected' : ''}>Occupied</option>
                <option value="Reserved" ${table != null && table.status == 'Reserved' ? 'selected' : ''}>Reserved</option>
            </select>

            <!-- Date and Time Picker for Reservation Time -->
            <label for="reservationTime">Reservation Time:</label>
            <input type="datetime-local" id="reservationTime" name="reservationTime" 
                   value="${table != null && table.reservationTime != null ? table.reservationTime : ''}">

            <button type="submit" class="btn-submit">${table == null ? 'Add Table' : 'Update Table'}</button>
        </form>
    </div>
</body>
</html>
