<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Event" %>
<html>
<head>
    <title>Event Calendar</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/EventCalendar.css">
</head>
<body>
    <header>
        <h1>Restaurant Events</h1>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="EventCalendar.jsp">Events</a></li>
                <li><a href="contact.jsp">Contact Us</a></li>
            </ul>
        </nav>
    </header>
    
    <div class="container">
        <h2>Upcoming Events</h2>
        <!-- Button to Search Events -->
        <div class="search-button-container">
            <a href="eventsearch" class="btn-search-event"><span class="search-icon">🔍</span> Search Events</a>
        </div>
        <div class="event-list">
            <c:forEach var="event" items="${events}">
                <div class="event-item">
                    <h3>${event.name}</h3>
                    <p>${event.description}</p>
                    <p>Type: ${event.type}</p>
                    <p>Date: ${event.date}</p>
                </div>
            </c:forEach>
        </div>

    </div>
</body>
</html>
