<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.error" %>
<%@ page import="DAO.DBManager" %>

<html>
<head>
    <title>View Error Report</title>
    <style>
        /* Basic reset and layout */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            color: #333;
        }

        .container {
            width: 60%;
            margin: 40px auto;
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        h1 {
            font-size: 30px;
            color: #007bff;
            text-align: center;
            margin-bottom: 20px;
        }

        h2 {
            font-size: 26px;
            color: #dc3545;
            text-align: center;
            margin-bottom: 20px;
        }

        h3 {
            color: #28a745;
            font-size: 22px;
            margin-top: 20px;
        }

        p {
            font-size: 18px;
            margin: 10px 0;
            line-height: 1.6;
        }

        strong {
            color: #007bff;
        }

        label {
            font-size: 18px;
            font-weight: bold;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin: 10px 0;
            box-sizing: border-box;
        }

        button {
            padding: 12px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0056b3;
        }

        a {
            text-decoration: none;
            color: #28a745;
            font-weight: bold;
            transition: color 0.3s ease;
        }

        a:hover {
            color: #218838;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .input-container {
            text-align: center;
        }

        .back-btn {
            display: block;
            margin-top: 20px;
            padding: 10px;
            background-color: #6c757d;
            color: white;
            text-align: center;
            border-radius: 5px;
            text-decoration: none;
        }

        .back-btn:hover {
            background-color: #5a6268;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            .container {
                width: 90%;
            }

            h1, h2 {
                font-size: 24px;
            }

            p {
                font-size: 16px;
            }
        }
    </style>
</head>
<body>

<div class="container">
    <%
        String errorIdParam = request.getParameter("id");

        if (errorIdParam == null || errorIdParam.isEmpty()) {
    %>
        <h2>Error: No error ID provided</h2>
        <div class="input-container">
            <form action="viewError.jsp" method="get">
                <label for="errorId">Enter Error ID:</label>
                <input type="text" id="errorId" name="id" required>
                <button type="submit">View Error Report</button>
            </form>
        </div>
        <a href="dashboard.jsp" class="back-btn">Back to Dashboard</a>
    <%
        } else {
            int id = Integer.parseInt(errorIdParam);
            DBManager dbManager = new DBManager();
            error err = dbManager.getErrorById(id);

            if (err == null) {
    %>
        <h2>Error report not found.</h2>
        <a href="dashboard.jsp" class="back-btn">Back to Dashboard</a>
    <%
            } else {
    %>
        <h1>Error Report Details</h1>
        <p><strong>Description:</strong> <%= err.getDescription() != null ? err.getDescription() : "No description available" %></p>
        <p><strong>Steps:</strong> <%= err.getSteps() != null ? err.getSteps() : "No steps available" %></p>
        <p><strong>Category:</strong> <%= err.getCategory() != null ? err.getCategory() : "Uncategorized" %></p>
        <p><strong>Severity:</strong> <%= err.getSeverity() != null ? err.getSeverity() : "Unknown" %></p>

        <!-- Display staff comments to the customer -->
        <h3>Update from Staff</h3>
        <p><%= err.getStaffComments() != null ? err.getStaffComments() : "No updates yet." %></p>

        <a href="dashboard.jsp" class="back-btn">Back to Dashboard</a>
    <%
            }
        }
    %>
</div>

</body>
</html>
