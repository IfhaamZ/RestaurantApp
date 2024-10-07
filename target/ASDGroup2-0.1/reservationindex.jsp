<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@page
import="java.util.Random"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Reservation Management System</title>
    <!-- Link to CSS -->
    <link rel="stylesheet" href="css/reservationindex.css" />
  </head>
  <body>
    <header>
      <h1>Reservation Management System</h1>
    </header>
    <nav>
      <ul>
        <li><a href="MakeReservation.jsp">Make a Reservation</a></li>
        <li><a href="ReservationServlet?action=list">View Reservations</a></li>
        <li><a href="ModifyReservation.jsp">Modify a Reservation</a></li>
        <li><a href="CancelReservation.jsp">Cancel a Reservation</a></li>
      </ul>
    </nav>
  </body>
</html>
