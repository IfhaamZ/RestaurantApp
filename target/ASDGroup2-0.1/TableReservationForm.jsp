<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Reserve Table</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/TableReservationForm.css">
</head>
<body>
    <header>
        <h1>Reserve Table</h1>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="TableManagement.jsp">Back to Table Management</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        <h2>Reserve Table ID: ${table.id}</h2>
        <form action="tablereserve" method="post">
            <input type="hidden" name="tableId" value="${table.id}">

            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>

            <label for="phone">Phone Number:</label>
            <input type="tel" id="phone" name="phone" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="reservationTime">Reservation Time:</label>
            <input type="datetime-local" id="reservationTime" name="reservationTime" required>

            <button type="submit">Reserve Table</button>
            <a href="index.jsp" class="cancel-btn">Cancel</a>
        </form>
    </div>
</body>
</html>
