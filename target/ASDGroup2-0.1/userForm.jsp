<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User" %> <!-- Import the User class -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Page - User Form</title>
    <link rel="stylesheet" href="css/form.css">
    <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'>
    <style>
        /* General styling for restaurant management system */
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .admin-container {
            text-align: center;
            width: 100%;
            margin-top: -40px;
        }

        .form-container {
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
            margin: 0 auto;
            text-align: left;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
            font-size: 32px;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
            font-size: 24px;
        }

        label {
            display: block;
            margin-bottom: 10px;
            color: #666;
            font-size: 14px;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"],
        select {
            width: 100%;
            padding: 12px;
            border-radius: 5px;
            border: 1px solid #ddd;
            margin-bottom: 20px;
            font-size: 16px;
        }

        button[type="submit"] {
            width: 100%;
            background-color: #ff6b6b;
            color: white;
            padding: 12px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }

        button[type="submit"]:hover {
            background-color: #e55d5d;
        }
    </style>
</head>
<body>
    <div class="admin-container">
        <h1>Admin Page</h1>
        <div class="form-container">
            <h2><%=(request.getAttribute("user") != null) ? "Edit" : "Create" %> User</h2>
            <form action="<%=(request.getAttribute("user") != null) ? "adminupdateuser" : "admininsertuser" %>" method="post">
                <!-- Hidden field for user ID, only if editing a user -->
                <input type="hidden" name="id" value="<%= (request.getAttribute("user") != null) ? ((User)request.getAttribute("user")).getId() : "" %>">
                
                <label for="name">Name:</label>
                <input type="text" name="name" value="<%= (request.getAttribute("user") != null) ? ((User)request.getAttribute("user")).getName() : "" %>" required>
                
                <label for="email">Email:</label>
                <input type="email" name="email" value="<%= (request.getAttribute("user") != null) ? ((User)request.getAttribute("user")).getEmail() : "" %>" required>
                
                <%-- Password field: required only for creating a new user --%>
                <label for="password">Password:</label>
                <input type="password" name="password" <%= (request.getAttribute("user") == null) ? "required" : "" %> placeholder="<%= (request.getAttribute("user") != null) ? "Leave blank to keep current password" : "" %>">

                <!-- Role selection dropdown -->
                <label for="role">Role:</label>
                <select name="role" required>
                    <option value="customer" <%= (request.getAttribute("user") != null && ((User)request.getAttribute("user")).getRole().equals("customer")) ? "selected" : "" %>>Customer</option>
                    <option value="staff" <%= (request.getAttribute("user") != null && ((User)request.getAttribute("user")).getRole().equals("staff")) ? "selected" : "" %>>Staff</option>
                    <option value="admin" <%= (request.getAttribute("user") != null && ((User)request.getAttribute("user")).getRole().equals("admin")) ? "selected" : "" %>>Admin</option>
                </select>
                
                <button type="submit"><%=(request.getAttribute("user") != null) ? "Update" : "Create" %></button>
            </form>
        </div>
    </div>
</body>
</html>
