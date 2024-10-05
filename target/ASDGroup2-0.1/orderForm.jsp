<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Place Order</title>
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
        }

        h2 {
            text-align: center;
            color: #333;
            font-weight: bold;
        }

        form label {
            display: block;
            margin-top: 10px;
            color: #333;
        }

        form input[type="text"], form textarea {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        form textarea {
            resize: vertical;
        }

        /* Button styling to match the theme */
        button {
            background-color: #ff6b6b; /* Matches the primary color */
            color: white;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            margin-top: 20px;
            width: 100%;
            border-radius: 5px;
            border: 2px solid #ff6b6b;
            font-weight: bold;
        }

        button:hover {
            background-color: white;
            color: #ff6b6b;
            border: 2px solid #ff6b6b;
        }

    </style>
</head>
<body>
    <div class="container">
        <h2>Place an Order</h2>
        <form action="insertorder" method="post">
            <label for="customerName">Customer Name:</label>
            <input type="text" name="customerName" id="customerName" required>

            <label for="orderDetails">Order Details:</label>
            <textarea name="orderDetails" id="orderDetails" rows="4" required></textarea>

            <button type="submit">Place Order</button>
        </form>
    </div>
</body>
</html>
