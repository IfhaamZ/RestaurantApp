<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Registration Successful</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/success.css" />
    <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'>
    <style>
        /* General styling for the registration success page */
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
            text-align: center;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
            font-size: 28px;
        }

        p {
            color: #666;
            font-size: 16px;
            margin-bottom: 20px;
        }

        a {
            text-decoration: none;
            background-color: #ff6b6b;
            color: white;
            padding: 10px 15px;
            border-radius: 5px;
            font-weight: bold;
            display: inline-block;
            margin-top: 20px;
        }

        a:hover {
            background-color: #e55d5d;
        }

        .message {
            color: green;
            font-size: 16px;
            margin-bottom: 20px;
        }
    </style>
  </head>
  <body>
    <div class="container">
        <h2>Registration Successful</h2>
        <p class="message">Your account has been created successfully!</p>
        <p>Please log in to continue.</p>
        <a href="login.jsp">Login Here</a>
    </div>
  </body>
</html>
