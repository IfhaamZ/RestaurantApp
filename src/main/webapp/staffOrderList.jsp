<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Management</title>
</head>
<body>
    <h2>Order Management</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Customer Name</th>
                <th>Order Details</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.customerName}</td>
                    <td>${order.orderDetails}</td>
                    <td>${order.status}</td>
                    <td>
                        <a href="staffeditorder?id=${order.id}">Edit</a> |
                        <a href="staffdeleteorder?id=${order.id}" onclick="return confirm('Are you sure?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>