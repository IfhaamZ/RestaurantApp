<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        <h2>Manage Events</h2>
        
        <!-- Add Event Form -->
        <div class="add-event">
            <h3>Add a New Event</h3>
            <form action="addEvent" method="post">
                <label for="name">Event Name:</label>
                <input type="text" name="name" required>
                
                <label for="description">Description:</label>
                <textarea name="description" required></textarea>
                
                <label for="date">Date:</label>
                <input type="date" name="date" required>
                
                <label for="type">Type:</label>
                <input type="text" name="type" required>
                
                <button type="submit" class="btn">Add Event</button>
            </form>
        </div>

        <!-- Display Existing Events -->
        <h3>Upcoming Events</h3>
        <div class="event-list">
            <c:forEach var="event" items="${events}">
                <div class="event-item">
                    <h4>${event.name}</h4>
                    <p>${event.description}</p>
                    <p>Date: ${event.date}</p>
                    <p>Type: ${event.type}</p>
                    
                    <!-- Delete Event -->
                    <form action="deleteEvent" method="post" style="display:inline;">
                        <input type="hidden" name="eventId" value="${event.id}">
                        <button type="submit" class="btn-delete">Delete</button>
                    </form>

                    <!-- Edit Event -->
                    <form action="editEvent.jsp" method="get" style="display:inline;">
                        <input type="hidden" name="eventId" value="${event.id}">
                        <button type="submit" class="btn-edit">Edit</button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
