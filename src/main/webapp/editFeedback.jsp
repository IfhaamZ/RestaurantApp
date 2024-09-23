<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.feedback" %>
<%@ page import="DAO.DBManager" %>

<html>
<head>
    <title>Edit Feedback</title>
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
        feedback fb = dbManager.getFeedbackById(id);

        if (fb == null) {  // Handle case where feedback is not found
    %>
        <h2>Feedback not found with ID: <%= id %></h2>
        <a href="viewFeedback.jsp">Back to Feedback Dashboard</a>
    <%
        } else {
    %>

    <h1>Edit Feedback</h1>

    <form action="/updateFeedback" method="post">
        <input type="hidden" name="id" value="<%= fb.getFeedbackId() %>">

        <label for="customerName">Customer Name:</label>
        <input id="customerName" type="text" name="customerName" value="<%= fb.getCustomerName() %>" readonly>

        <label for="feedbackText">Feedback:</label>
        <textarea id="feedbackText" name="feedbackText" readonly><%= fb.getFeedbackText() %></textarea>

        <label for="rating">Rating:</label>
        <input id="rating" type="text" name="rating" value="<%= fb.getRating() %>" readonly>

        <label for="staffResponse">Staff Response:</label>
        <textarea id="staffResponse" name="staffResponse"><%= fb.getStaffResponse() != null ? fb.getStaffResponse() : "" %></textarea>

        <input type="submit" value="Update Feedback">
    </form>

    <a href="viewFeedback.jsp">Back to Feedback Dashboard</a>

    <% } %>  <!-- End of the if-else block -->
</div>

</body>
</html>
