<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.feedback" %>
<%@ page import="DAO.DBManager" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>Staff Feedback View and Update</title>
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

        textarea {
            width: 100%;
            height: 100px;
            padding: 10px;
            margin-bottom: 20px;
            resize: vertical;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .submit-btn {
            padding: 8px 16px;
            background-color: #28a745;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
            cursor: pointer;
        }

        .submit-btn:hover {
            background-color: #218838;
        }

        .edit-btn {
            background-color: #007bff;
            color: white;
            padding: 6px 12px;
            border-radius: 5px;
            text-decoration: none;
        }

        .edit-btn:hover {
            background-color: #0056b3;
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
    <h1>Staff Feedback Dashboard</h1>

    <h2>View and Update Customer Feedback</h2>

    <!-- Form to handle staff responses -->
    <form action="/submitStaffFeedbackResponse" method="post">
        <table>
            <thead>
                <tr>
                    <th>Feedback ID</th>
                    <th>Customer Name</th>
                    <th>Feedback</th>
                    <th>Rating</th>
                    <th>Staff Response</th>
                    <th>Date Submitted</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
            <%
                DBManager dbManager = new DBManager();
                List<feedback> feedbackList = dbManager.getAllFeedback();

                for (feedback fb : feedbackList) {
            %>
                <tr>
                    <td><%= fb.getFeedbackId() %></td>
                    <td><%= fb.getCustomerName() %></td>
                    <td><%= fb.getFeedbackText() %></td>
                    <td><%= fb.getRating() %></td>
                    <td><%= fb.getStaffResponse() != null ? fb.getStaffResponse() : "No response yet" %></td>
                    <td><%= fb.getCreatedAt() %></td>
                    <td>
                        <a href="editFeedback.jsp?id=<%= fb.getFeedbackId() %>" class="edit-btn">Edit</a>
                    </td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>

        <!-- Submit button to send all responses -->
        <div>
            <button type="submit" class="submit-btn">Submit All Responses</button>
        </div>
    </form>
</div>

</body>
</html>
