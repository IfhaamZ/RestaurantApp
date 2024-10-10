<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Event" %>
<html>
<head>
    <title><%= request.getAttribute("event") == null ? "Add New Event" : "Edit Event" %></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/EventForm.css">
    <script>
        // JavaScript function to validate the form before submission
        function validateForm() {
            const eventType = document.querySelector('[name="type"]').value;
            const date = document.querySelector('[name="date"]').value;
            const eventName = document.querySelector('[name="name"]').value;

            // Check if the event name contains only letters and spaces
            const namePattern = /^[A-Za-z\s]+$/;
            if (!namePattern.test(eventName)) {
                alert("Event Name can only contain letters and spaces.");
                return false;
            }

            // Ensure the date is not empty and valid
            if (!date) {
                alert("Please select a valid date.");
                return false;
            }

            // Check if the event type contains only letters and spaces
            if (!namePattern.test(eventType)) {
                alert("Event Type can only contain letters and spaces.");
                return false;
            }

            return true;
        }
    </script>
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
        
        <form action="<%= request.getAttribute("event") == null ? "eventinsert" : "eventupdate" %>" method="post" onsubmit="return validateForm()">
            <input type="hidden" name="eventId" value="<%= request.getAttribute("event") != null ? ((Event) request.getAttribute("event")).getId() : "" %>">
            
            <label for="name">Event Name:</label>
            <input type="text" name="name" value="<%= request.getAttribute("event") != null ? ((Event) request.getAttribute("event")).getName() : "" %>" required pattern="[A-Za-z\s]+" title="Event Name must contain only letters and spaces.">
            
            <label for="description">Description:</label>
            <textarea name="description" required><%= request.getAttribute("event") != null ? ((Event) request.getAttribute("event")).getDescription() : "" %></textarea>
            
            <label for="date">Date:</label>
            <input type="date" name="date" value="<%= request.getAttribute("event") != null ? ((Event) request.getAttribute("event")).getDate() : "" %>" required>
            
            <label for="type">Type:</label>
            <input type="text" name="type" value="<%= request.getAttribute("event") != null ? ((Event) request.getAttribute("event")).getType() : "" %>" required pattern="[A-Za-z\s]+" title="Event Type must contain only letters and spaces.">
            
            <button type="submit" class="btn"><%= request.getAttribute("event") == null ? "Add Event" : "Update Event" %></button>
        </form>
    </div>
</body>
</html>
