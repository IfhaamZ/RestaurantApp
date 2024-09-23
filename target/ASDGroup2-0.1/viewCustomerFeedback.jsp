<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.feedback" %>
<%@ page import="DAO.DBManager" %>

<html>
<head>
    <title>Your Feedback</title>
    <style>
        /* Basic Styles */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .container {
            width: 50%;
            margin: 50px auto;
            padding: 30px;
            background-color: white;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }

        h1 {
            color: #007bff;
            text-align: center;
            margin-bottom: 20px;
        }

        p {
            font-size: 18px;
            margin-top: 10px;
        }

        .staff-response {
            color: #28a745;
            font-weight: bold;
            margin-top: 20px;
        }

        .back-btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
            text-align: center;
        }

        .back-btn:hover {
            background-color: #0056b3;
        }

        .error-message {
            color: #dc3545;
            font-size: 18px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Your Feedback</h1>

    <!-- Check if errorMessage is set and display it -->
    <c:if test="${not empty errorMessage}">
        <p class="error-message">${errorMessage}</p>
    </c:if>

    <!-- Only display the feedback if it exists -->
    <c:if test="${not empty feedback}">
    <p><strong>Feedback ID:</strong> ${feedback.feedbackId}</p>
<p><strong>Customer Name:</strong> ${feedback.customerName}</p>
<p><strong>Feedback:</strong> ${feedback.feedbackText}</p>
<p><strong>Rating:</strong> ${feedback.rating}</p>

    <h2>Staff Response</h2>
    <p class="staff-response">${feedback.staffResponse != null ? feedback.staffResponse : "No response yet"}</p>
</c:if>

    <a href="/dashboard.jsp" class="back-btn">Return to Dashboard</a>
</div>

</body>
</html>
