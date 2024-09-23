<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.error" %>
<%@ page import="DAO.DBManager" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>Staff Dashboard</title>
    <style>
        /* Basic reset and layout */
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .container {
            width: 90%;
            margin: 40px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #333;
            font-size: 36px;
            text-align: center;
            margin-bottom: 20px;
        }

        h2 {
            color: #555;
            font-size: 24px;
            margin-bottom: 20px;
            text-align: left;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        td {
            color: #333;
        }

        a {
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
            transition: color 0.2s ease-in-out;
        }

        a:hover {
            color: #0056b3;
        }

        /* Button styles for actions */
        .btn {
            padding: 8px 16px;
            background-color: #28a745;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .btn:hover {
            background-color: #218838;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            .container {
                width: 100%;
                padding: 10px;
            }

            th, td {
                padding: 8px;
            }
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Staff Dashboard</h1>

    <h2>View and Edit Error Reports</h2>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Description</th>
            <th>Steps</th>
            <th>Category</th>
            <th>Severity</th>
            <th>Staff Comments</th>
            <th>Date Submitted</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            DBManager dbManager = new DBManager();
            List<error> errorReports = dbManager.getAllErrorReports();  // Use the 'error' class

            for (error err : errorReports) {
        %>
        <tr>
            <td><%= err.getId() %></td>
            <td><%= err.getDescription() %></td>
            <td><%= err.getSteps() %></td>
            <td><%= err.getCategory() %></td>
            <td><%= err.getSeverity() %></td>
            <td><%= err.getStaffComments() != null ? err.getStaffComments() : "No comments yet" %></td>
            <td><%= err.getCreatedAt() != null ? err.getCreatedAt() : "N/A" %></td> <!-- Display Created At -->
            <td><a href="editError.jsp?id=<%= err.getId() %>" class="btn">Edit</a></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

</div>

</body>
</html>
