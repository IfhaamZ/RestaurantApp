<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Restaurant Management System</title>
    <!-- Link to CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>
    <header>
        <h1>Welcome to the Restaurant Management System</h1>
    </header>

    <!-- Navigation Bar -->
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
            <h1>Discover Delicious Food & Great Ambience</h1>
            <p>Experience fine dining at its best. Browse our menu, make reservations, and attend our exclusive events!</p>
            <!-- Call-to-action button -->
            <a href="menu.jsp" class="btn-cta">Explore Menu</a>
        </div>
        <!-- Hero background image -->
        <div class="hero-image">
            <!-- Example image. Make sure the path matches the actual file location in your project -->
            <img src="${pageContext.request.contextPath}/images/hero.jpeg" alt="Delicious food" style="display:none;">
        </div>
    </section>

    <!-- Main Content Section -->
    <div class="content">
        <h2>Explore Our Restaurant</h2>
        <p>Welcome to our restaurant management system. Here you can manage your tables, view upcoming events, make reservations, and much more!</p>
    </div>

    <!-- Footer -->
    <footer>
        <p>&copy; 2024 Restaurant Management System. All Rights Reserved.</p>
    </footer>
</body>
</html>
