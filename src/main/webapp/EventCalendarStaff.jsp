<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Event" %>
<html>
<head>
    <title>Staff Event Calendar</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/EventCalendarStaff.css">
</head>
<body>
    <header>
        <h1>Staff Event Management</h1>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="EventCalendarStaff.jsp">Manage Events</a></li>
                <li><a href="logout.jsp">Logout</a></li>
            </ul>
        </nav>
    </header>
    
    <div class="container">
        <h1 style="text-align:center; padding: 5px;">Event Management</h1>

        <!-- Navigation links for event management actions -->
        <div class="nav-links">
            <a class="custom-add-btn" href="eventnew" class="custom-button">Add New Event</a>            
            <a class="custom-list-btn" href="staffeventsearch"><span class="search-icon">🔍</span> Search Event</a>
        </div>

        <!-- Display Existing Events in a card-like format -->
        <h2>Upcoming Events</h2>
        <div class="event-list">
            <c:forEach var="event" items="${events}">
                <div class="event-item">
                    <h4>${event.name}</h4>
                    <p>${event.description}</p>
                    <p>Type: ${event.type}</p>
                    <p>Date: ${event.date}</p>
                    
                    <!-- Actions for Edit and Delete -->
                    <div class="event-actions">
                        <form action="eventedit" method="get" style="display:inline;">
                            <input type="hidden" name="eventId" value="${event.id}">
                            <button type="submit" class="btn-edit">Edit</button>
                        </form>
                        <form action="eventdelete" method="post" style="display:inline;">
                            <input type="hidden" name="eventId" value="${event.id}">
                            <button type="submit" class="btn-delete">Delete</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
