<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.error" %>
<%@ page import="DAO.DBManager" %>

<html>
<head>
    <title>Edit Error Report</title>
    <style>
        /* Basic Styling */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f9;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            margin: 0;
        }

        .container {
            width: 60%;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #007bff;
            font-size: 28px;
            text-align: center;
            margin-bottom: 20px;
        }

        h2 {
            color: #dc3545;
            font-size: 22px;
            text-align: center;
            margin-bottom: 20px;
        }

        label {
            font-size: 16px;
            font-weight: bold;
            color: #333;
            display: block;
            margin-bottom: 10px;
        }

        textarea, input[type="text"] {
            width: 100%;
            padding: 12px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 20px;
            box-sizing: border-box;
            background-color: #f9f9f9;
        }

        textarea {
            height: 100px;
            resize: vertical;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: white;
            padding: 12px 20px;
            font-size: 18px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        a {
            text-decoration: none;
            color: #007bff;
            display: block;
            margin-top: 20px;
            text-align: center;
        }

        a:hover {
            text-decoration: underline;
        }

        .error-message {
            color: #dc3545;
            font-size: 18px;
            text-align: center;
            margin-bottom: 20px;
        }

        /* Responsive Styling */
        @media (max-width: 768px) {
            .container {
                width: 90%;
            }

            h1 {
                font-size: 24px;
            }
        }
    </style>
</head>
<body>

<div class="container">
    <%
        int id = Integer.parseInt(request.getParameter("id"));
        DBManager dbManager = new DBManager();
        error err = dbManager.getErrorById(id);

        if (err == null) {  // Handle case where error is not found
    %>
        <h2>Error not found with ID: <%= id %></h2>
        <a href="staffDashboard.jsp">Back to Dashboard</a>
    <%
        } else {
    %>

    <h1>Edit Error Report</h1>

    <form action="updateError" method="post" onsubmit="return validateForm()">
        <input type="hidden" name="id" value="<%= err.getId() %>">

        <label for="description">Description:</label>
        <textarea id="description" name="description"><%= err.getDescription() %></textarea>

        <label for="steps">Steps:</label>
        <textarea id="steps" name="steps"><%= err.getSteps() %></textarea>

        <label for="category">Category:</label>
        <input id="category" type="text" name="category" value="<%= err.getCategory() %>">

        <label for="severity">Severity:</label>
        <input id="severity" type="text" name="severity" value="<%= err.getSeverity() %>">

        <label for="staffComments">Staff Comments:</label>
        <textarea id="staffComments" name="staffComments"><%= err.getStaffComments() != null ? err.getStaffComments() : "" %></textarea>

        <input type="submit" value="Update Error Report">
    </form>

    <a href="staffDashboard.jsp">Back to Dashboard</a>

    <script type="text/javascript">
        function validateForm() {
            var description = document.getElementById('description').value.trim();
            var steps = document.getElementById('steps').value.trim();
            var category = document.getElementById('category').value.trim();
            var severity = document.getElementById('severity').value.trim();

            if (description === "" || steps === "" || category === "" || severity === "") {
                alert("All fields must be filled out.");
                return false;  // Prevent form submission
            }

            return true;  // Allow form submission
        }
    </script>

    <% } %>  <!-- End of the if-else block -->
</div>

</body>
</html>
