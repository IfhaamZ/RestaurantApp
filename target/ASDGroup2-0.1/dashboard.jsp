<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Dashboard</title>
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
            background-color: #f0f8ff;
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
            background-color: #28a745; /* Green button */
            color: white;
            border: none;
            padding: 12px 20px;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s, box-shadow 0.3s;
        }

        .card button:hover {
            background-color: #218838;
            box-shadow: 0 4px 8px rgba(0, 123, 255, 0.3);
            transform: scale(1.05);
        }

        /* Dropdown */
        .dropdown {
            position: relative;
            display: inline-block;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: white;
            min-width: 200px;
            box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.1);
            z-index: 1;
            border-radius: 5px;
            padding: 12px 0;
        }

        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            text-align: left;
        }

        .dropdown-content a:hover {
            background-color: #f1f1f1;
        }

        .dropdown:hover .dropdown-content {
            display: block;
        }

        .footer {
            margin-top: 50px;
            text-align: center;
            font-size: 14px;
            color: #555;
            padding: 20px;
            width: 100%;
            background-color: #f6f6f6;
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

    <h1>WELCOME TO YOUR DASHBOARD</h1>

    <div class="container">
        <div class="card">
            <h2>Report an Error</h2>
            <p>If you've encountered an issue, click below to report it to us.</p>
            <button onclick="location.href='/form'">Report an Error</button>
        </div>

        <div class="card">
            <h2>Provide Feedback</h2>
            <p>Tell us about your dining experience and let us know how we did!</p>
            <button onclick="location.href='/formSubmit'">Give Feedback</button>
        </div>

        <div class="card">
            <h2>Staff Updates</h2>
            <p>View updates related to errors and customer feedback.</p>
            <div class="dropdown">
                <button class="dropbtn">Updates</button>
                <div class="dropdown-content">
                    <a href="/lookupFeedback">Feedback Update</a>
                    <a href="/viewError">Error Update</a>
                </div>
            </div>
        </div>
    </div>

    <div class="footer">
        <p>Powered by Restaurant App | <a href="#">Privacy Policy</a></p>
    </div>

</body>
</html>
