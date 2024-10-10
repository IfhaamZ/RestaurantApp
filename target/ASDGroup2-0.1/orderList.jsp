<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Order Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <header>
        <h1>Order Management</h1>
    </header>

    <nav>
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/ordernew">Add New Order</a></li>  <!-- 경로 수정 -->
        </ul>
    </nav>

    <section>
        <h2>Order List</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Customer Name</th>
                    <th>Dish Name</th>
                    <th>Quantity</th>
                    <th>Order Date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.orderId}</td>
                        <td>${order.customerName}</td>
                        <td>${order.dishName}</td>
                        <td>${order.quantity}</td>
                        <td>${order.orderDate}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </section>

    <footer>
        <p>&copy; 2024 Restaurant Management System. All Rights Reserved.</p>
    </footer>
</body>
</html>