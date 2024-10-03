<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css" />
    <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'>
    <style>
        /* General styling for restaurant register page */
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

        input[type="text"],
        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 12px;
            border-radius: 5px;
            border: 1px solid #ddd;
            margin-bottom: 20px;
            font-size: 16px;
        }

        button.register-form {
            width: 100%;
            background-color: #ff6b6b;
            color: white;
            padding: 12px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }

        button.register-form:hover {
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

        p a {
            color: #ff6b6b;
            text-decoration: none;
            font-weight: bold;
        }

        p a:hover {
            text-decoration: underline;
        }
    </style>
  </head>
  <body>
    <div class="container">
        <form method="post" action="userregister" class="register-form">
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
