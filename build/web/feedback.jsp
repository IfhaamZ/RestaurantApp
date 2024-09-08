<!DOCTYPE html>
<html lang="en">
<head>
    
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Provide Feedback</title>
    <style>
        /* Basic Styles */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .container {
            width: 50%;
            margin: 50px auto;
            padding: 30px;
            background-color: white;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            transition: all 0.3s ease;
        }

        .container:hover {
            box-shadow: 0 12px 24px rgba(0, 0, 0, 0.2);
        }

        h2 {
            color: #333;
            font-size: 26px;
            font-weight: 600;
            text-align: center;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
            color: #333;
        }

        .form-group input[type="text"],
        .form-group input[type="email"],
        .form-group textarea,
        .form-group select {
            width: 100%;
            padding: 12px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            transition: border-color 0.3s ease;
            background-color: #f9f9f9;
        }

        .form-group input[type="text"]:focus,
        .form-group input[type="email"]:focus,
        .form-group textarea:focus,
        .form-group select:focus {
            border-color: #28a745;
            background-color: #fff;
        }

        .form-group textarea {
            height: 100px;
            resize: vertical;
        }

        .form-group select {
            height: 45px;
        }

        .submit-btn {
            display: block;
            width: 100%;
            padding: 15px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
            text-align: center;
        }

        .submit-btn:hover {
            background-color: #218838;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        .form-group small {
            color: #666;
            font-size: 12px;
        }

        .form-header {
            background-color: #28a745;
            padding: 20px;
            color: white;
            border-radius: 10px 10px 0 0;
            margin-bottom: 30px;
            text-align: center;
        }

        .form-header h2 {
            margin: 0;
            font-size: 28px;
            font-weight: normal;
        }

        .required-field {
            color: red;
        }

        /* Star Rating Styles */
        .star-rating {
            display: flex;
            gap: 5px;
            justify-content: center;
            margin: 10px 0;
        }

        .star-rating input[type="radio"] {
            display: none;
        }

        .star-rating label {
            font-size: 24px;
            color: #ccc;
            cursor: pointer;
            transition: color 0.3s ease;
        }

        .star-rating input[type="radio"]:checked ~ label {
            color: #ffc107;
        }

        .star-rating label:hover,
        .star-rating label:hover ~ label {
            color: #ffc107;
        }

        /* Character count for textarea */
        .char-counter {
            display: block;
            text-align: right;
            font-size: 12px;
            color: #666;
        }

        @media (max-width: 768px) {
            .container {
                width: 90%;
            }

            .form-group label,
            .form-group small {
                font-size: 14px;
            }
        }
    </style>
</head>
<body>

    <div class="container">
        <div class="form-header">
            <h2>Provide Feedback</h2>
        </div>
        <form action="submitFeedback" method="post" onsubmit="showLoading()">
            <!-- Name -->
            <div class="form-group">
                <label for="name">Your Name</label>
                <input type="text" id="name" name="name" placeholder="Enter your name">
            </div>

            <!-- Email (with required and validation) -->
            <div class="form-group">
                <label for="email">Your Email <span class="required-field">*</span></label>
                <input type="email" id="email" name="email" placeholder="Enter your email" required 
                       oninvalid="this.setCustomValidity('Please enter a valid email address')" 
                       oninput="setCustomValidity('')">
                <small>Please enter a valid email address (e.g., name@example.com)</small>
            </div>

            <!-- Star Rating -->
            <div class="form-group">
                <label for="rating">Overall Experience Rating <span class="required-field">*</span></label>
                <div class="star-rating">
                    <input type="radio" id="rating5" name="rating" value="5" required>
                    <label for="rating5">&#9733;</label>
                    <input type="radio" id="rating4" name="rating" value="4">
                    <label for="rating4">&#9733;</label>
                    <input type="radio" id="rating3" name="rating" value="3">
                    <label for="rating3">&#9733;</label>
                    <input type="radio" id="rating2" name="rating" value="2">
                    <label for="rating2">&#9733;</label>
                    <input type="radio" id="rating1" name="rating" value="1">
                    <label for="rating1">&#9733;</label>
                </div>
            </div>

            <!-- Feedback Comments -->
            <div class="form-group">
                <label for="comments">Feedback <span class="required-field">*</span></label>
                <textarea id="comments" name="comments" placeholder="Share your experience with us..." required oninput="updateCharCount()"></textarea>
                <small id="charCount" class="char-counter">0/250 characters</small>
            </div>

            <!-- Submit Button -->
            <button type="submit" class="submit-btn" id="submitBtn">Submit Feedback</button>
        </form>
    </div>

    <script>
        // Update character count
        function updateCharCount() {
            const charCount = document.getElementById('comments').value.length;
            document.getElementById('charCount').innerText = `${charCount}/250 characters`;
        }

        // Show loading state on submit
        function showLoading() {
            const btn = document.getElementById('submitBtn');
            btn.innerHTML = 'Submitting...';
            btn.disabled = true;
        }
    </script>

</body>
</html>
