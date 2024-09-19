<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Table" %>
<html>
<head>
    <title>Table Reservation</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/TableReservationForm.css">
</head>
<body>
    <header>
        <h1>Reserve a Table</h1>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="tablelist">Manage Tables</a></li>
                <li><a href="eventlist">Events</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        <h2>Reserve an Available Table</h2>
        <form action="tablereserve" method="post">
            <label for="tableId">Select Table:</label>
            <select name="tableId" id="tableId" required>
                <option value="">Select a table</option>
                <c:forEach var="table" items="${availableTables}">
                    <option value="${table.id}">Table ${table.id} - Capacity: ${table.capacity}</option>
                </c:forEach>
            </select>

            <label for="reservationTime">Reservation Time:</label>
            <input type="datetime-local" id="reservationTime" name="reservationTime" required>

            <button type="submit" class="btn-reserve">Reserve Table</button>
        </form>
    </div>
</body>
</html>
