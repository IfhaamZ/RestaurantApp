<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Feedback Average Rating</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f6f9;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            color: #333;
        }

        .header {
            text-align: center;
            margin-bottom: 20px;
        }

        .header h1 {
            font-size: 32px;
            color: #2c3e50;
        }

        .container {
            text-align: center;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
            padding: 30px;
            width: 400px;
        }

        h2 {
            color: #2c3e50;
            font-size: 28px;
            margin-bottom: 20px;
        }

        p {
            font-size: 18px;
            color: #34495e;
            margin: 15px 0;
        }

        .rating {
            font-size: 36px;
            color: #27ae60; /* Green color for emphasis */
            font-weight: bold;
        }

        .btn {
            background-color: #2980b9;
            color: white;
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            margin-top: 20px;
            transition: background-color 0.3s ease;
        }

        .btn:hover {
            background-color: #3498db;
        }

        /* Responsive design */
        @media (max-width: 768px) {
            .container {
                width: 90%;
                padding: 20px;
            }

            h2 {
                font-size: 24px;
            }

            p {
                font-size: 16px;
            }

            .rating {
                font-size: 32px;
            }

            .btn {
                font-size: 14px;
                padding: 8px 16px;
            }
        }
    </style>
</head>
<body>

    <div class="header">
        <h1>Data Analysis of Feedback Review</h1>
    </div>

    <div class="container">
        <h2>Customer Feedback Average Rating</h2>
        <p>The average rating from customer feedback is:</p>
        <p class="rating">
            <%= request.getAttribute("averageRating") != null ? String.format("%.2f", (Double) request.getAttribute("averageRating")) : "N/A" %>
        </p> <!-- Dynamically display the rating -->
        <a href="/mainStaffDashboard" class="btn">Back to Dashboard</a>
    </div>

</body>
</html>
