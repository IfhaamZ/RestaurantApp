<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Order" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Order</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }

        .container {
            max-width: 800px;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #333;
            font-weight: bold;
            margin-bottom: 20px;
        }

        form label {
            display: block;
            margin-top: 10px;
            color: #333;
        }

        form input[type="text"], form select, form textarea {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        form textarea {
            resize: vertical;
        }

        /* Button styling */
        button {
            background-color: #ff6b6b;
            color: white;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            margin-top: 20px;
            width: 100%;
            border-radius: 5px;
            border: 2px solid #ff6b6b;
            font-weight: bold;
        }

        button:hover {
            background-color: white;
            color: #ff6b6b;
            border: 2px solid #ff6b6b;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Edit Order</h2>
        <form action="staffupdateorder" method="post">
            <!-- Hidden field for the order ID -->
            <input type="hidden" name="id" value="<%= ((Order) request.getAttribute("order")).getId() %>">

            <!-- Order Details -->
            <label for="orderDetails">Order Details:</label>
            <textarea name="orderDetails" id="orderDetails" required rows="4"><%= ((Order) request.getAttribute("order")).getOrderDetails() %></textarea>

            <!-- Order Status -->
            <label for="status">Status:</label>
            <select name="status" id="status">
                <option value="pending" <%= ((Order) request.getAttribute("order")).getStatus().equals("pending") ? "selected" : "" %>>Pending</option>
                <option value="confirmed" <%= ((Order) request.getAttribute("order")).getStatus().equals("confirmed") ? "selected" : "" %>>Confirmed</option>
                <option value="completed" <%= ((Order) request.getAttribute("order")).getStatus().equals("completed") ? "selected" : "" %>>Completed</option>
            </select>

            <!-- Submit Button -->
            <button type="submit">Update Order</button>
        </form>
    </div>
</body>
</html>
