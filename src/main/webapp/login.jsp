<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Login</title>
    <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'>
    <style>
        /* General styling for restaurant login page */
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
            font-size: 28px;
        }

        label {
            display: block;
            margin: 15px 0 5px;
            color: #666;
            font-size: 14px;
        }

        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 12px;
            border-radius: 5px;
            border: 1px solid #ddd;
            margin-bottom: 20px;
            font-size: 16px;
        }

        button.login-form {
            width: 100%;
            background-color: #ff6b6b;
            color: white;
            padding: 12px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }

        button.login-form:hover {
            background-color: #e55d5d;
        }

        .message {
            color: red;
            text-align: center;
            margin-bottom: 20px;
            font-size: 14px;
        }

        p {
            text-align: center;
            margin: 10px 0;
            color: #666;
        }

        .link-button {
            width: 100%;
            background-color: transparent;
            border: none;
            text-align: center;
        }

        .link-button a {
            color: #ff6b6b;
            text-decoration: none;
            font-size: 16px;
            font-weight: bold;
        }

        .link-button a:hover {
            text-decoration: underline;
        }
    </style>
  </head>
  <body>
    <div class="container">
        <form method="post" action="userlogin" class="login-form">
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