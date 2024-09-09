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

    <!-- Hero Section -->
    <section class="hero">
        <div class="hero-content">
            <h2>Discover Exquisite Dining</h2>
            <p>Experience the finest flavors and impeccable service at our restaurant. From cozy reservations to lively events, we have everything you need for the perfect dining experience.</p>
            <a href="reservation.jsp" class="btn-hero">Make a Reservation</a>
        </div>
    </section>

    <footer>
        <p>&copy; 2024 Restaurant Management System. All Rights Reserved.</p>
    </footer>
</body>
</html>
