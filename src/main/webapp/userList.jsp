<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
    <link rel="stylesheet" href="css/list.css">
    <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'>
    <style>
        /* General page styling */
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 20px;
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
            font-size: 28px;
        }

        a {
            text-decoration: none;
            background-color: #ff6b6b;
            color: white;
            padding: 10px 15px;
            border-radius: 5px;
            font-weight: bold;
            display: inline-block;
            margin-bottom: 20px;
        }

        a:hover {
            background-color: #e55d5d;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 0 auto;
            max-width: 900px;
            background-color: white;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 15px;
            text-align: left;
        }

        th {
            background-color: #ff6b6b;
            color: white;
            font-size: 16px;
        }

        td {
            border-bottom: 1px solid #ddd;
            font-size: 14px;
        }

        tr:last-child td {
            border-bottom: none;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        td a {
            margin-right: 10px;
            color: #ff6b6b;
            font-weight: bold;
        }

        td a:hover {
            color: #e55d5d;
        }

        td a:last-child {
            margin-right: 0;
        }
    </style>
</head>
<body>
    <h2>User Management</h2>
    <div style="text-align: center;">
        <a href="adminnewuser">Create New User</a>
    </div>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>
                        <a href="adminedituser?id=${user.id}">Edit</a>
                        <a href="admindeleteuser?id=${user.id}" onclick="return confirm('Are you sure you want to delete this user?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
