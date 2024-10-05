<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Order" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Order</title>
</head>
<body>
    <h2>Edit Order</h2>
    <form action="staffupdateorder" method="post">
        <input type="hidden" name="id" value="<%= ((Order) request.getAttribute("order")).getId() %>">
        
        <label for="orderDetails">Order Details:</label>
        <textarea name="orderDetails" id="orderDetails" required><%= ((Order) request.getAttribute("order")).getOrderDetails() %></textarea><br><br>

        <label for="status">Status:</label>
        <select name="status" id="status">
            <option value="pending" <%= ((Order) request.getAttribute("order")).getStatus().equals("pending") ? "selected" : "" %>>Pending</option>
            <option value="confirmed" <%= ((Order) request.getAttribute("order")).getStatus().equals("confirmed") ? "selected" : "" %>>Confirmed</option>
            <option value="completed" <%= ((Order) request.getAttribute("order")).getStatus().equals("completed") ? "selected" : "" %>>Completed</option>
        </select><br><br>

        <button type="submit">Update Order</button>
    </form>
</body>
</html>