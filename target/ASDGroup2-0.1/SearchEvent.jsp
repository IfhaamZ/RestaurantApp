<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Event" %>
<html>
<head>
    <title>Search Events</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/SearchEvent.css">
</head>
<body>
    <div class="container">
        <h1>Search Events</h1>
        <div class="nav-links">
            <a href="eventlist" class="btn-list">List All Events</a>
        </div>
        
        <div class="search-container">
            <form action="eventsearch" method="get" class="search-form">
                <label for="type">Type:</label>
                <input type="text" id="type" name="type" placeholder="Enter event type">
                
                <label for="date">Date:</label>
                <input type="date" id="date" name="date">

                <input type="submit" value="Search" class="btn-search">
            </form>
        </div>

        <div class="event-list">
            <%
                List<Event> listEvents = (List<Event>) request.getAttribute("ListOfEvents");
                if (listEvents != null && !listEvents.isEmpty()) {
                    for (Event event : listEvents) {
            %>
                <div class="event-item">
                    <div class="event-header">
                        <h3><%= event.getName() %></h3>
                        <p class="event-date"><span class="date-display"><%= event.getDate() %></span></p>
                    </div>
                    <p class="event-description"><%= event.getDescription() %></p>
                    <div class="event-meta">
                        <p class="event-type">Type: <%= event.getType() %></p>
                    </div>
                </div>
            <%
                    }
                } else {
            %>
                <div class="no-results">
                    <p>No events found matching your search criteria.</p>
                </div>
            <%
                }
            %>
        </div>
    </div>
</body>
</html>
