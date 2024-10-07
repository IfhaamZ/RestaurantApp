<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Modify Reservation</title>
    <!-- Link to CSS -->
    <link rel="stylesheet" href="css/ModifyReservation.css" />
  </head>
  <body>
    <h2>Modify Your Reservation</h2>
    <form action="ReservationServlet?action=update" method="POST">
      <label for="reservationId">Reservation ID:</label>
      <input
        type="text"
        id="reservationId"
        name="reservationId"
        required
      /><br />
      <label for="name">New Name:</label>
      <input type="text" id="name" name="name" required /><br />
      <label for="date">New Date:</label>
      <input type="date" id="date" name="date" required /><br />
      <label for="time">New Time:</label>
      <input type="time" id="time" name="time" required /><br />
      <label for="guests">New Number of Guests:</label>
      <input type="number" id="guests" name="guests" min="1" required /><br />

      <div class="button-container">
        <button
          type="button"
          class="cancel-btn"
          onclick="window.location.href='index.jsp';"
        >
          Cancel
        </button>
        <button type="submit">Submit</button>
      </div>
    </form>
  </body>
</html>
