<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Place Order</title>
</head>
<body>
    <h2>Place an Order</h2>
    <form action="insertorder" method="post">
        <label for="customerName">Customer Name:</label>
        <input type="text" name="customerName" id="customerName" required><br><br>

        <label for="orderDetails">Order Details:</label>
        <textarea name="orderDetails" id="orderDetails" required></textarea><br><br>

        <button type="submit">Place Order</button>
    </form>
</body>
</html>
