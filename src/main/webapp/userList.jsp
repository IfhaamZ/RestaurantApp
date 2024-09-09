<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <header>
        <h1>User Management</h1>
    </header>

    <nav>
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="usernew">Add New User</a></li>
        </ul>
    </nav>

    <section>
        <h2>User List</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>User ID</th>
                    <th>Username</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.userId}</td>
                        <td>${user.username}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </section>

    <footer>
        <p>&copy; 2024 Restaurant Management System. All Rights Reserved.</p>
    </footer>
</body>
</html>