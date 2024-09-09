<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Payment Status</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        margin: 20px;
      }
      .message {
        font-size: 20px;
        color: green;
      }
    </style>
  </head>
  <body>
    <h1>Payment Status</h1>

    <p class="message"><%= request.getAttribute("message") %></p>

    <a href="index.jsp">Return to Home</a>
  </body>
</html>
