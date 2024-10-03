<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <%
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
        }
    %>
    <h2>Welcome, <%= user.getName() %>!</h2>
    <a href="userlogout">Logout</a>
</body>
</html>
