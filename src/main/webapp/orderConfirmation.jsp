<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Confirmation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }

        .container {
            max-width: 800px;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h2 {
            color: #333;
            font-weight: bold;
        }

        p {
            font-size: 18px;
            color: #666;
            margin: 20px 0;
        }

        /* Link and button styling to match the theme */
        .btn, .link {
            display: inline-block;
            margin: 10px 0;
            padding: 10px 20px;
            background-color: #ff6b6b;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
            border: 2px solid #ff6b6b;
        }

        .btn:hover, .link:hover {
            background-color: white;
            color: #ff6b6b;
            border: 2px solid #ff6b6b;
        }

        form {
            margin-top: 20px;
        }

        input[type="submit"] {
            background-color: #ff6b6b;
            color: white;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            font-weight: bold;
            border: 2px solid #ff6b6b;
        }

        input[type="submit"]:hover {
            background-color: white;
            color: #ff6b6b;
            border: 2px solid #ff6b6b;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Thank you! Your order has been placed.</h2>
        <p>Your order will be processed shortly.</p>

        <!-- Link to place another order -->
        <a href="placeorder" class="link">Place another order</a>
        
        <!-- Button to go back to home (index.jsp) -->
        <form action="index.jsp" method="get">
            <input type="submit" value="Go Back to Home">
        </form>
    </div>
</body>
</html>
