<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Make a Reservation</title>
    <link rel="stylesheet" href="css/MakeReservation.css">
</head>
<body>
    <h2>Make a New Reservation</h2>
    <form action="ReservationServlet" method="POST">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br>

        <label for="date">Date:</label>
        <input type="date" id="date" name="date" required><br>

        <label for="time">Time:</label>
        <input type="time" id="time" name="time" required><br>

        <label for="guests">Number of Guests:</label>
        <input type="number" id="guests" name="guests" min="1" required><br>

        <button type="submit">Submit</button>
    </form>
</body>
</html>
