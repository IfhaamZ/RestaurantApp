<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.feedback" %>
<%@ page import="DAO.DBManager" %>

<html>
<head>
    <title>Lookup Feedback</title>
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

        input[type="text"] {
            width: 100%;
            padding: 12px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 20px;
            box-sizing: border-box;
            background-color: #f9f9f9;
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

        .back-btn {
            text-decoration: none;
            color: #007bff;
            display: block;
            margin-top: 20px;
            text-align: center;
        }

        .back-btn:hover {
            text-decoration: underline;
        }

        /* Delete button styles */
        .delete-btn {
            padding: 12px 20px;
            background-color: #dc3545; /* Red for delete */
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            width: 100%; /* Make the button full width */
            margin-top: 20px;
        }

        .delete-btn:hover {
            background-color: #c82333; /* Darker red on hover */
        }

        /* Responsive Design */
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
        // Retrieve feedbackId from the request
        String feedbackIdParam = request.getParameter("id");

        if (feedbackIdParam == null || feedbackIdParam.isEmpty()) {
    %>
        <h2>Error: No feedback ID provided</h2>
        <div class="input-container">
            <form action="lookupFeedback.jsp" method="get">
                <label for="feedbackId">Enter Feedback ID:</label>
                <input type="text" id="feedbackId" name="id" required>
                <input type="submit" value="View Feedback">
            </form>
        </div>
        <a href="dashboard.jsp" class="back-btn">Back to Dashboard</a>
    <%
        } else {
            int feedbackId = Integer.parseInt(feedbackIdParam);
            DBManager dbManager = new DBManager();
            feedback fb = dbManager.getFeedbackById(feedbackId);

            if (fb == null) {
    %>
        <h2>Feedback not found.</h2>
        <a href="dashboard.jsp" class="back-btn">Back to Dashboard</a>
    <%
            } else {
    %>
        <h1>Feedback Details</h1>
        <p><strong>Feedback ID:</strong> <%= fb.getFeedbackId() %></p>
        <p><strong>Customer Name:</strong> <%= fb.getCustomerName() %></p>
        <p><strong>Feedback:</strong> <%= fb.getFeedbackText() %></p>
        <p><strong>Rating:</strong> <%= fb.getRating() %></p>
        <h2>Staff Response</h2>
        <p><%= fb.getStaffResponse() != null ? fb.getStaffResponse() : "No response yet" %></p>

        <!-- Delete form -->
        <form action="deleteFeedback" method="post">
            <input type="hidden" name="feedbackId" value="<%= fb.getFeedbackId() %>">
            <button type="submit" class="delete-btn">Delete Feedback</button>
        </form>
        
        <a href="dashboard.jsp" class="back-btn">Back to Dashboard</a>
    <%
            }
        }
    %>
</div>

</body>
</html>
