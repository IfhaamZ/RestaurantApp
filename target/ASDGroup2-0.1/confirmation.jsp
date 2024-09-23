<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Submission Confirmation</title>
    <style>
        /* Basic Styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            text-align: center;
            padding: 50px;
        }
        .container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 50%;
            margin: auto;
        }
        h1 {
            color: #28a745;
        }
        p {
            margin-top: 20px;
            font-size: 18px;
        }
        .success-message {
            font-size: 20px;
            color: #28a745;
        }
        .error-id {
            font-size: 18px;
            color: #007bff;
            margin-top: 15px;
        }
        a {
            text-decoration: none;
            color: #007bff;
        }
        a:hover {
            text-decoration: underline;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
            transition: background-color 0.3s ease;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>Thank You!</h1>
        <!-- Display the success message dynamically passed from the servlet -->
        <p class="success-message">${successMessage}</p>
        <p>Your error report has been successfully submitted and is being reviewed.</p>

        <!-- Display the error ID for customer tracking -->
        <p class="error-id">Your error ID is: <strong>${errorId}</strong></p>
        
        <a href="/dashboard" class="btn">Return to Dashboard</a>
    </div>

</body>
</html>
