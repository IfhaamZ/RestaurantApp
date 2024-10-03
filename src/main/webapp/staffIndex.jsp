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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <style>
        /* Additional styles specific to staff index */
        .staff-links ul {
            list-style: none;
            padding: 0;
        }

        .staff-links ul li {
            margin: 20px 0;
        }

        .staff-links a {
            text-decoration: none;
            background-color: #ff6b6b;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            font-weight: bold;
            display: inline-block;
        }

        .staff-links a:hover {
            background-color: #e55d5d;
        }

        .staff-content {
            text-align: center;
            margin: 50px 0;
        }
    </style>
</head>
<body>
    <header>
        <h1>Staff Dashboard - Restaurant Management System</h1>
    </header>

    <section class="staff-content">
        <h2>Welcome, <%= user.getName() %>!</h2>
        <p>Manage events and tables efficiently to provide the best service to our guests.</p>

        <!-- Links to Event Management and Table Management -->
        <div class="staff-links">
            <ul>
                <li><a href="staffeventlist">Event Management</a></li>
                <li><a href="tablelist">Table Management</a></li>
                <li><a href="staffMenuDisplay">Menu Management</a></li>
            </ul>
        </div>

        <!-- Logout button -->
        <form action="logout.jsp" method="post">
            <button type="submit">Logout</button>
        </form>
    </section>

    <footer>
        <p>2024 Restaurant Management System - Staff Dashboard.</p>
    </footer>
</body>
</html>
