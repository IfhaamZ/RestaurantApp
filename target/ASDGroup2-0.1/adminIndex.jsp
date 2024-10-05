<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
// Check if a user is logged in and has admin privileges
User user = (User) session.getAttribute("user");

// If no user is logged in, or if the user does not have admin privileges, redirect to the login page
if (user == null || !user.getRole().equals("admin")) {
    response.sendRedirect("login.jsp");
    return;
}
%>
<html>
<head>
    <title>Admin Dashboard - Restaurant Management System</title>
    <!-- Use the same CSS as the staff dashboard -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/staffIndex.css">
    <!-- Link to Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <header>
        <h1>Admin Dashboard - Restaurant Management System</h1>
    </header>

    <section class="staff-content">
        <h2>Welcome, <%= user.getName() %>!</h2>
        <br>
        <!-- Card links for User Management, Settings, and Reports -->
        <div class="staff-links">
            <div class="card">
                <i class="fas fa-users-cog"></i> <!-- Icon for User Management -->
                <a href="adminlistuser">User Management</a>
            </div>
        </div>

        <!-- Logout button -->
        <form action="userlogout" method="post">
            <button type="submit">Logout</button>
        </form>
    </section>

    <footer>
        <p>2024 Restaurant Management System - Admin Dashboard.</p>
    </footer>
</body>
</html>
