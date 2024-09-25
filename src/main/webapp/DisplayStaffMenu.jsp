<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.MenuItem" %>
<html>
<head>
    <title>Manage Menu</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/DisplayStaffMenu.css">
</head>
<body>
    <header>
        <h1>Menu Management</h1>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="menuform">Add New Item</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        <!-- Search Form -->
        <form action="staffmenusearch" method="get" class="search-form">
            <label for="category">Search by Category:</label>
            <select name="category" id="category">
                <option value="">Select Category</option>
                <c:forEach var="category" items="${categories}">
                    <option value="${category}" ${param.category == category ? 'selected' : ''}>${category}</option>
                </c:forEach>
            </select>
            <button type="submit" class="btn-search">Search</button>
        </form>

        <c:forEach var="item" items="${menuItems}">
            <div class="menu-item">
                <h2>${item.name}</h2>
                <p>${item.description}</p>
                <p>Price: $${item.price}</p>
                <p>Category: ${item.category}</p>

                <!-- Edit Item -->
                <form action="menuedit" method="get" style="display:inline;">
                    <input type="hidden" name="menuId" value="${item.id}">
                    <button type="submit" class="btn-edit">Edit</button>
                </form>

                <!-- Delete Item -->
                <form action="menudelete" method="post" style="display:inline;">
                    <input type="hidden" name="menuId" value="${item.id}">
                    <button type="submit" class="btn-delete" onclick="return confirm('Are you sure you want to delete this item?')">Delete</button>
                </form>
            </div>
        </c:forEach>
    </div>
</body>
</html>
