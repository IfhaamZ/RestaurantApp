<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f6f6f6;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        h1 {
            color: #1E90FF;
            margin-top: 30px;
            text-align: center;
            font-size: 32px;
        }

        .container {
            display: flex;
            justify-content: space-evenly;
            width: 80%;
            margin-top: 50px;
        }

        .card {
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
            text-align: center;
            width: 250px;
            transition: transform 0.3s, background-color 0.3s;
        }

        .card:hover {
            transform: translateY(-5px);
            background-color: #f0f8ff; /* Light blue background on hover */
        }
        
        .card h2 {
            color: #1E90FF;
            font-size: 20px;
            margin-bottom: 10px;
        }

        .card p {
            font-size: 14px;
            color: #555;
            margin-bottom: 20px;
        }

        .card button {
            background-color: #1E90FF;
            color: white;
            border: none;
            padding: 12px 20px;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s, box-shadow 0.3s;
        }

        .card button:hover {
            background-color: #007BFF;
            box-shadow: 0 4px 8px rgba(0, 123, 255, 0.3);
            transform: scale(1.05);
        }

        .footer {
            margin-top: 50px;
            text-align: center;
            font-size: 14px;
            color: #555;
            padding: 20px;
            width: 100%;
            background-color: #f6f6f6;
            position: fixed;
            bottom: 0;
        }

        .footer a {
            color: #1E90FF;
            text-decoration: none;
        }

        .footer a:hover {
            text-decoration: underline;
        }

        /* Media Query for Responsiveness */
        @media (max-width: 768px) {
            .container {
                flex-direction: column;
                align-items: center;
            }

            .card {
                width: 90%;
                margin-bottom: 20px;
            }

            h1 {
                font-size: 28px;
            }
        }
    </style>
</head>
<body>

    <h1>WELCOME TO STAFF DASHBOARD</h1>

    <div class="container">
        <div class="card">
            <h2>View and Update an Error</h2>
            <p>Issues reported by customers can be viewed</p>
            <button onclick="location.href='/staffDashboard'">View the Error</button>
        </div>

        <div class="card">
            <h2>View and Update Feedback</h2>
            <p>View Customer's Feedback</p>
            <button onclick="location.href='/viewFeedback'">Give Feedback</button>
        </div>

        <div class="card">
            <h2>View Average Feedback Rating</h2>
            <p>Analyze the average customer feedback rating.</p>
            <button onclick="location.href='/viewAverageRating'">View Average Rating</button>
        </div>
    </div>

    <div class="footer">
        <p>Powered by Restaurant App | <a href="#">Privacy Policy</a></p>
    </div>

</body>
</html>
