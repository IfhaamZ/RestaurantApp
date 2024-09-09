<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Add New User</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <header>
        <h1>Add New User</h1>
    </header>

    <section>
        <form action="usersave" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required><br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br>

            <input type="submit" value="Save">
        </form>
    </section>

    <footer>
        <p>&copy; 2024 Restaurant Management System. All Rights Reserved.</p>
    </footer>
</body>
</html>
