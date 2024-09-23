<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Submit Feedback</title>
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

        .form-group input:focus,
        .form-group textarea:focus,
        .form-group select:focus {
            border-color: #007bff;
            background-color: #fff;
        }

        .form-group textarea {
            height: 120px;
            resize: vertical;
        }

        .submit-btn {
            display: block;
            width: 100%;
            padding: 15px;
            background-color: #007bff;
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
            background-color: #0056b3;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        .submit-message {
            display: none;
            text-align: center;
            margin-top: 20px;
            color: #28a745;
            font-size: 16px;
        }

        .view-feedback {
            text-align: center;
            margin-top: 20px;
        }

        .view-feedback a {
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
        }

        .view-feedback a:hover {
            color: #0056b3;
            text-decoration: underline;
        }

        @media (max-width: 768px) {
            .container {
                width: 90%;
            }
        }
    </style>

    <script>
        function validateForm() {
            const name = document.getElementById('name').value.trim();
            const email = document.getElementById('email').value.trim();
            const rating = document.getElementById('rating').value.trim();
            const comments = document.getElementById('comments').value.trim();
            const submitMessage = document.getElementById('submit-message');
            const submitButton = document.getElementById('submit-btn');

            if (name === '' || email === '' || rating === '' || comments === '') {
                alert('Please fill out all required fields.');
                return false; // Prevent form submission
            }

            submitMessage.style.display = 'block';
            submitButton.disabled = true;
            submitButton.textContent = 'Submitting...';

            return true; // Allow form submission
        }
    </script>
</head>
<body>

    <div class="container">
        <div class="form-header">
            <h2>Submit Feedback</h2>
        </div>
        <form action="/feedbackSubmit" method="post" onsubmit="return validateForm()">
            
            <!-- Name -->
            <div class="form-group">
                <label for="name">Name <span class="required-field">*</span></label>
                <input type="text" id="name" name="name" required>
            </div>

            <!-- Email -->
            <div class="form-group">
                <label for="email">Email <span class="required-field">*</span></label>
                <input type="email" id="email" name="email" required>
            </div>

            <!-- Rating -->
            <div class="form-group">
                <label for="rating">Rating (1-5) <span class="required-field">*</span></label>
                <select id="rating" name="rating" required>
                    <option value="" disabled selected>Select rating</option>
                    <option value="1">1 - Poor</option>
                    <option value="2">2 - Fair</option>
                    <option value="3">3 - Good</option>
                    <option value="4">4 - Very Good</option>
                    <option value="5">5 - Excellent</option>
                </select>
            </div>

            <!-- Feedback Comments -->
            <div class="form-group">
                <label for="comments">Comments <span class="required-field">*</span></label>
                <textarea id="comments" name="comments" required></textarea>
            </div>

            <!-- Submit Button -->
            <button type="submit" id="submit-btn" class="submit-btn">Submit Feedback</button>
        </form>

        <!-- Submitting message -->
        <div id="submit-message" class="submit-message">Submitting your feedback, please wait...</div>

        <!-- Add a section to link to customer feedback view -->
        <div class="view-feedback">
            <p>Want to check the status of your feedback? <a href="/lookupFeedback.jsp">View Feedback Status</a></p>
        </div>
    </div>

</body>
</html>
