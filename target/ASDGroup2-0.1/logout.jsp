<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Logout</title>
    <link rel="stylesheet" href="css/logout.css">
    <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'>
    <script src="https://kit.fontawesome.com/99df4661f3.js" crossorigin="anonymous"></script>
  </head>
  <body>
    <% User user = (User) session.getAttribute("user");
       String name = (user != null) ? user.getName() : "Guest";
    %>
    <div class="logout-container">
      <i class="fa-regular fa-circle-xmark" style="color: #f70820; font-size: 8rem; display: flex; justify-content: center; align-items:center;"></i>   
      <br>
      <h1>Goodbye for now, <%= name %>!</h1>
      <br>
      <p class="message">
        You've successfully logged out!<br>Click the button below to return to Home.
      </p>
      <br>
      <div class="button-container">
        <a href="index.jsp" class="home-button">Go to Home</a>
      </div>
    </div>
    <% session.invalidate(); %> <!-- Invalidate the session after the message is shown -->
  </body>
</html>