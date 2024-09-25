<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.MenuItem" %>
<html>
<head>
    <title>Menu</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/DisplayMenu.css">
</head>
<body>
    <header>
        <h1>Our Menu</h1>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
            </ul>
        </nav>
    </header>

    <!-- Search Form with Dropdown -->
    <div class="search-container">
        <form action="menusearch" method="get">
            <label for="category">Search by Category:</label>
            <select name="category" id="category" required>
                <option value="">Select Category</option>
                <!-- Assuming categories are dynamically fetched -->
                <c:forEach var="cat" items="${categories}">
                    <option value="${cat}">${cat}</option>
                </c:forEach>
            </select>
            <button type="submit">Search</button>
        </form>
    </div>

    <div class="container">
    <c:choose>
        <c:when test="${empty menuItems}">
            <p>No items found for the selected category. Please try another category.</p>
        </c:when>
        <c:otherwise>
            <c:forEach var="item" items="${menuItems}">
                <div class="menu-item">
                    <h2>${item.name}</h2>
                    <p>${item.description}</p>
                    <p>Price: $${item.price}</p>
                    <p>Category: ${item.category}</p>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
