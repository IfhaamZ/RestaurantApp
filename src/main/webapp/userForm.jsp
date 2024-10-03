<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User" %> <!-- Import the User class -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Form</title>
    <link rel="stylesheet" href="css/form.css">
</head>
<body>
    <h2><%=(request.getAttribute("user") != null) ? "Edit" : "Create" %> User</h2>
    <form action="<%=(request.getAttribute("user") != null) ? "adminupdateuser" : "admininsertuser" %>" method="post">
        
        <label for="name">Name:</label>
        <input type="text" name="name" value="<%= (request.getAttribute("user") != null) ? ((User)request.getAttribute("user")).getName() : "" %>" required>
        
        <label for="email">Email:</label>
        <input type="email" name="email" value="<%= (request.getAttribute("user") != null) ? ((User)request.getAttribute("user")).getEmail() : "" %>" required>
        
        <label for="password">Password:</label>
        <input type="password" name="password" required>
        
        <button type="submit">Submit</button>
    </form>
</body>
</html>
