<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Restaurant Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <style>
        /* Styling for login and register buttons */
        .auth-buttons {
            position: absolute;
            top: 30px;
            right: 30px;
        }

        .auth-buttons a {
            margin-left: 10px;
            padding: 10px 20px;
            background-color: #ff6b6b; /* Matches header background */
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
            border: 2px solid #ff6b6b; /* Matches button to header */
        }

        .auth-buttons a:hover {
            background-color: white;
            color: #ff6b6b;
            border: 2px solid #ff6b6b; /* Matches button hover effect */
        }

        .auth-buttons a:first-child {
            margin-left: 0;
        }
    </style>
</head>
<body>
    <header>
        <h1>Welcome to the Restaurant Management System</h1>
        <!-- Login and Register Buttons -->
        <div class="auth-buttons">
            <a href="${pageContext.request.contextPath}/login.jsp" class="btn-auth">Login</a>
            <a href="${pageContext.request.contextPath}/register.jsp" class="btn-auth">Register</a>
        </div>
    </header>

    <nav>
        <ul>
            <li><a href="menu.jsp">Menu</a></li>
            <li><a href="reservation.jsp">Reservations</a></li>
            <li><a href="eventlist">Events</a></li>
            <li><a href="TableManagement.jsp">Table Management</a></li>
            <li><a href="contact.jsp">Contact Us</a></li>
            <li><a href="orderOption.jsp">Order</a></li>
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