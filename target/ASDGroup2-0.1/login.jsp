<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
    <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'>
    <script src="https://kit.fontawesome.com/99df4661f3.js" crossorigin="anonymous"></script>
  </head>
  <body>
    <div class="container">
        <form method="post" action="userlogin" class="login-form"> <!-- Updated action -->
            <h2>Login</h2>
            <label for="email">Email</label>
            <input type="email" name="email" id="email" required placeholder="Enter your email" />

            <label for="password">Password</label>
            <input type="password" name="password" id="password" required placeholder="Enter your password" />

            <button class="login-form" type="submit">Login</button>
        </form>

        <% String error = request.getParameter("error"); %>
        <% if ("invalid".equals(error)) { %>
            <p class="message">Invalid email or password. Please try again.</p>
        <% } %>

        <p>If you haven't registered yet, please do so.</p>
        <button class="link-button">
            <a href="register.jsp">Register</a>
        </button>
    </div>
  </body>
</html>