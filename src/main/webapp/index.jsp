<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Restaurant Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
    <header>
        <h1>Welcome to the Restaurant Management System</h1>
    </header>

    <nav>
        <ul>
            <li><a href="menu.jsp">Menu</a></li>
            <li><a href="reservation.jsp">Reservations</a></li>
            <li><a href="eventlist">Events</a></li>
            <li><a href="TableManagement.jsp">Table Management</a></li>
            <li><a href="contact.jsp">Contact Us</a></li>
        </ul>
    </nav>

    <div class="content">
        <h2>Explore Our Restaurant</h2>
        <p>Welcome to our restaurant management system. Here you can manage your tables, view upcoming events, make reservations, and much more!</p>
    </div>

    <footer>
        <p>&copy; 2024 Restaurant Management System. All Rights Reserved.</p>
    </footer>
</body>
</html>
