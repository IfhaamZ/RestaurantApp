<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Event" %>
<html>
<head>
    <title><%= request.getAttribute("event") == null ? "Add New Event" : "Edit Event" %></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/EventForm.css">
</head>
<body>
    <header>
        <h1><%= request.getAttribute("event") == null ? "Add New Event" : "Edit Event" %></h1>
        <nav>
            <ul>
                <li><a href="staffeventlist">Back to Event List</a></li>
            </ul>
        </nav>
    </header>
    
    <div class="container">
        <h2><%= request.getAttribute("event") == null ? "Add Event" : "Edit Event" %></h2>
        
        <form action="<%= request.getAttribute("event") == null ? "eventinsert" : "eventupdate" %>" method="post">
            <input type="hidden" name="eventId" value="<%= request.getAttribute("event") != null ? ((Event) request.getAttribute("event")).getId() : "" %>">
            
            <label for="name">Event Name:</label>
            <input type="text" name="name" value="<%= request.getAttribute("event") != null ? ((Event) request.getAttribute("event")).getName() : "" %>" required>
            
            <label for="description">Description:</label>
            <textarea name="description" required><%= request.getAttribute("event") != null ? ((Event) request.getAttribute("event")).getDescription() : "" %></textarea>
            
            <label for="date">Date:</label>
            <input type="date" name="date" value="<%= request.getAttribute("event") != null ? ((Event) request.getAttribute("event")).getDate() : "" %>" required>
            
            <label for="type">Type:</label>
            <input type="text" name="type" value="<%= request.getAttribute("event") != null ? ((Event) request.getAttribute("event")).getType() : "" %>" required>
            
            <button type="submit" class="btn"><%= request.getAttribute("event") == null ? "Add Event" : "Update Event" %></button>
        </form>
    </div>
</body>
</html>
