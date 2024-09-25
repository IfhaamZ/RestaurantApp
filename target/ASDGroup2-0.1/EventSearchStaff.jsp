<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Event" %>
<html>
<head>
    <title>Staff Event Search</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/searchEvent.css">
</head>
<body>
    <div class="container">
        <h1>Search Events (Staff)</h1>
        <div class="nav-links">
            <a href="staffeventlist" style="background: lightgreen;">Go Back</a>
            <a href="eventnew">Add New Event</a>
        </div>
        <br>
        <div class="search-container">
            <h2>Search Events</h2>
            <form action="staffeventsearch" method="get">
                <label for="type">Type:</label>
                <input type="text" id="type" name="type">

                <label for="date">Date:</label>
                <input type="date" id="date" name="date">

                <input type="submit" value="Search">
            </form>
        </div>

        <div class="event-list">
            <%
                List<Event> listEvents = (List<Event>) request.getAttribute("ListOfEvents");
                if (listEvents != null && !listEvents.isEmpty()) {
                    for (Event event : listEvents) {
            %>
                <div class="event-item">
                    <h3><%= event.getName() %></h3>
                    <p><%= event.getDescription() %></p>
                    <p>Date: <%= event.getDate() %></p>
                    <p>Type: <%= event.getType() %></p>
                    
                    <!-- Edit and Delete Buttons for Staff -->
                    <form action="eventdelete" method="post" style="display:inline;">
                        <input type="hidden" name="eventId" value="<%= event.getId() %>">
                        <button type="submit" class="btn-delete">Delete</button>
                    </form>

                    <form action="eventedit" method="get" style="display:inline;">
                        <input type="hidden" name="eventId" value="<%= event.getId() %>">
                        <button type="submit" class="btn-edit">Edit</button>
                    </form>
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