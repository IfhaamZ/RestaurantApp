<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Cancel Reservation</title>
    <!-- Link to CSS -->
    <link rel="stylesheet" href="css/CancelReservation.css" />
  </head>
  <body>
    <h2>Cancel Your Reservation</h2>
    <form action="ReservationServlet?action=delete" method="POST">
      <label for="reservationId">Reservation ID:</label>
      <input
        type="text"
        id="reservationId"
        name="reservationId"
        required
      /><br />
      <label for="reservationName">Reservation Name:</label>
      <input
        type="text"
        id="reservationName"
        name="reservationName"
        required
      /><br />

      <div class="button-container">
        <button
          type="button"
          class="cancel-btn"
          onclick="window.location.href='index.jsp';"
        >
          Cancel
        </button>
        <button type="submit">Cancel Reservation</button>
      </div>
    </form>
  </body>
</html>
