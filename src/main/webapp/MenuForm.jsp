<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.MenuItem" %>
<html>
<head>
    <title>Manage Menu Item</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/MenuForm.css">
</head>
<body>
    <header>
        <h1>${menuItem == null ? "Add New Item" : "Edit Item"}</h1>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="menulist">Back to Menu</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        <form action="${menuItem == null ? 'menuinsert' : 'menuupdate'}" method="post">
            <c:choose>
                <c:when test="${menuItem != null}">
                    <input type="hidden" name="menuId" value="${menuItem.id}">
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="menuId" value="">
                </c:otherwise>
            </c:choose>

            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required value="${menuItem != null ? menuItem.name : ''}">

            <label for="description">Description:</label>
            <textarea id="description" name="description" required>${menuItem != null ? menuItem.description : ''}</textarea>

            <label for="price">Price:</label>
            <input type="number" step="0.01" id="price" name="price" required value="${menuItem != null ? menuItem.price : ''}">

            <label for="category">Category:</label>
            <input type="text" id="category" name="category" required value="${menuItem != null ? menuItem.category : ''}">

            <button type="submit" class="btn-submit">${menuItem == null ? 'Add Item' : 'Update Item'}</button>
        </form>
    </div>
</body>
</html>
