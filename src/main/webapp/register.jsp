<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css" />
    <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'>
    <script src="https://kit.fontawesome.com/99df4661f3.js" crossorigin="anonymous"></script>
  </head>
  <body>
    <div class="container">
        <form method="post" action="userregister" class="register-form"> <!-- Updated action -->
            <h2>Register</h2>
            <label for="name">Name</label>
            <input type="text" name="name" id="name" required placeholder="Enter your name" />

            <label for="email">Email</label>
            <input type="email" name="email" id="email" required placeholder="Enter your email" />

            <label for="password">Password</label>
            <input type="password" name="password" id="password" required placeholder="Enter your password" />

            <button class="register-form" type="submit">Register</button>
        </form>

        <% String registrationError = request.getParameter("error"); %>
        <% if ("exists".equals(registrationError)) { %>
            <p class="message">This email is already registered. Please use a different email.</p>
        <% } %>

        <p>Already have an account? <a href="login.jsp">Login here</a></p>
    </div>
  </body>
</html>