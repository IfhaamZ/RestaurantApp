<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Add New Order</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <header>
        <h1>Add New Order</h1>
    </header>

    <section>
        <form action="${pageContext.request.contextPath}/ordersave" method="post">  <!-- 경로 수정 -->
            <label for="customerName">Customer Name:</label>
            <input type="text" id="customerName" name="customerName" required><br>

            <label for="dishName">Dish Name:</label>
            <input type="text" id="dishName" name="dishName" required><br>

            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" required><br>

            <input type="submit" value="Save">
        </form>
    </section>

    <footer>
        <p>&copy; 2024 Restaurant Management System. All Rights Reserved.</p>
    </footer>
</body>
</html>