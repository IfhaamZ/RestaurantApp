<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.Table" %>
<html>
<head>
    <title>Manage Table</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/TableForm.css">
    <script>
        // JavaScript function to validate the form before submission
        function validateForm() {
            // Get the reservation time input
            const reservationTime = document.getElementById("reservationTime").value;
            
            // Check if reservation time is provided and in the future
            if (reservationTime) {
                const currentDateTime = new Date();
                const selectedDateTime = new Date(reservationTime);
                
                if (selectedDateTime < currentDateTime) {
                    alert("Reservation time must be in the future.");
                    return false; // Prevent form submission
                }
            }
            return true; // Allow form submission if everything is valid
        }
    </script>
</head>
<body>
    <header>
        <h1>${table == null ? "Add Table" : "Edit Table"}</h1>
        <nav>
            <ul>
                <li><a href="staffIndex.jsp">Home</a></li>
                <li><a href="tablelist">Manage Tables</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        <h2>${table == null ? "Add a New Table" : "Update Table"}</h2>
        <form action="${table == null ? 'tableinsert' : 'tableupdate'}" method="post" onsubmit="return validateForm()">
            <c:choose>
                <c:when test="${table != null}">
                    <input type="hidden" name="tableId" value="${table.id}">
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="tableId" value="">
                </c:otherwise>
            </c:choose>

            <!-- Validate table seats to be a positive number -->
            <label for="capacity">Table Seats:</label>
            <input type="number" id="capacity" name="capacity" min="1" required value="${table != null ? table.capacity : ''}" 
                   title="Please enter a valid number of seats.">

            <!-- Status dropdown -->
            <label for="status">Status:</label>
            <select id="status" name="status" required>
                <option value="Available" ${table != null && table.status == 'Available' ? 'selected' : ''}>Available</option>
                <option value="Occupied" ${table != null && table.status == 'Occupied' ? 'selected' : ''}>Occupied</option>
                <option value="Reserved" ${table != null && table.status == 'Reserved' ? 'selected' : ''}>Reserved</option>
            </select>

            <!-- Date and Time Picker for Reservation Time -->
            <label for="reservationTime">Reservation Time:</label>
            <input type="datetime-local" id="reservationTime" name="reservationTime" 
                   value="${table != null && table.reservationTime != null ? table.reservationTime : ''}" 
                   title="Reservation time must be in the future if provided.">

            <button type="submit" class="btn-submit">${table == null ? 'Add Table' : 'Update Table'}</button>
        </form>
    </div>
</body>
</html>
