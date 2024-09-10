<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Cancel Reservation</title>
            <!-- Link to CSS -->
            <link rel="stylesheet" href="css/CancelReservation.css">
</head>
<body>
    <h2>Cancel Your Reservation</h2>
    <form action="cancelReservationProcess.jsp" method="POST">
        <label for="reservationId">Reservation ID:</label>
        <input type="text" id="reservationId" name="reservationId" required><br>
        <button type="submit">Cancel Reservation</button>
    </form>
</body>
</html>
