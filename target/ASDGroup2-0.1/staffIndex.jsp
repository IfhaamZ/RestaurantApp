<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
// Check if a user is logged in by retrieving the session attribute "user"
User user = (User) session.getAttribute("user");

// If no user is logged in, redirect to the login page
if (user == null || !user.getRole().equals("staff")) {
    response.sendRedirect("login.jsp");
    return;
}
%>
<html>
<head>
    <title>Staff Dashboard - Restaurant Management System</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/staffIndex.css">
    <!-- Link to Font Awesome or any icon library for the icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <header>
        <h1>Staff Dashboard - Restaurant Management System</h1>
    </header>

    <section class="staff-content">
        <h2>Welcome, <%= user.getName() %>!</h2>
        <br>

        <!-- Card links for Event Management, Table Management, and Menu Management -->
        <div class="staff-links">
            <div class="card">
                <i class="fas fa-calendar-alt"></i> <!-- con for Event Management -->
                <a href="staffeventlist">Event Management</a>
            </div>
            <div class="card">
                <i class="fas fa-chair"></i> <!-- icon for Table Management -->
                <a href="tablelist">Table Management</a>
            </div>
            <div class="card">
                <i class="fas fa-utensils"></i> <!--icon for Menu Management -->
                <a href="staffMenuDisplay">Menu Management</a>
            </div>
            <div class="card">
                <i class="fas fa-comments"></i> <i class="fas fa-exclamation-circle"></i>                
                <a href="mainStaffDashboard">Error & Feedback Management</a>
            </div>
            <div class="card">
                <i class="fas fa-boxes"></i> <!-- Icon for Inventory Management -->
                <a href="inventoryview">Inventory Management</a>
            </div>
        </div>

        <!-- Logout button -->
        <form action="/userlogout" method="post">
            <button type="submit">Logout</button>
        </form>
    </section>

    <footer>
        <p>2024 Restaurant Management System - Staff Dashboard.</p>
    </footer>
</body>
</html>
