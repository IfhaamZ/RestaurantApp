<head>
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

        h1 {
            text-align: center;
            color: #333;
            font-weight: bold;
        }

        form label {
            display: block;
            margin-top: 10px;
            color: #333;
        }

        form select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        /* Matching the color scheme for the buttons */
        button {
            background-color: #ff6b6b; /* Matches the primary color */
            color: white;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            width: auto;
            border-radius: 5px;
            border: 2px solid #ff6b6b; /* Matches header */
            font-weight: bold;
        }

        button:hover {
            background-color: white;
            color: #ff6b6b;
            border: 2px solid #ff6b6b; /* Matches hover effect */
        }

        /* Flexbox container for buttons */
        .button-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 20px;
        }

        /* Go Back to Home button aligned to the left */
        .back-button {
            background-color: #ff6b6b; /* Matches the primary color */
            color: white;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            border: 2px solid #ff6b6b; /* Matches header */
            font-weight: bold;
        }

        .back-button:hover {
            background-color: white;
            color: #ff6b6b;
            border: 2px solid #ff6b6b; /* Matches hover effect */
        }
    </style>

    <!-- JavaScript to handle redirection -->
    <script>
        function redirectBasedOnOption() {
            const selectedOption = document.getElementById("option").value;
            
            if (selectedOption === "customer") {
                window.location.href = "orderForm.jsp";
            } else if (selectedOption === "staff") {
                window.location.href = "stafforders";
            }
        }

        function goBackHome() {
            window.location.href = "index.jsp";
        }
    </script>
</head>

<body>
    <div class="container">
        <h1>Select Your Options</h1>
        <form>
            <label for="option">Choose an option:</label>
            <select id="option" name="option">
                <option value="customer">Order as Customer</option>
                <option value="staff">Staff View</option>
            </select>
            <!-- Flexbox container to position buttons -->
            <div class="button-container">
                <button class="back-button" type="button" onclick="goBackHome()">Go Back to Home</button>
                <button type="button" onclick="redirectBasedOnOption()">Confirm Option</button>
            </div>
        </form>
    </div>
</body>
