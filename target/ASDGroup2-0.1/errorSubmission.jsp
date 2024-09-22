<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Report an Error</title>
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
        .form-group textarea:focus,
        .form-group select:focus {
            border-color: #007bff;
            background-color: #fff;
        }

        .form-group textarea {
            height: 120px;
            resize: vertical;
        }

        .form-group select {
            height: 45px;
        }

        /* Button Styles */
        .submit-btn,
        .view-btn {
            display: block;
            width: 100%;
            padding: 15px;
            margin-top: 10px;
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

        .view-btn {
            background-color: #28a745;
        }

        .view-btn:hover {
            background-color: #218838;
        }

        /* Additional Styling */
        .form-group small {
            color: #666;
            font-size: 12px;
        }

        .form-header {
            background-color: #007bff;
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

        .submit-message {
            display: none;
            text-align: center;
            margin-top: 20px;
            color: #28a745;
            font-size: 16px;
        }

        /* Responsive Design */
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

    <script>
        function validateForm() {
            const description = document.getElementById('description').value.trim();
            const steps = document.getElementById('steps').value.trim();
            const category = document.getElementById('category').value;
            const severity = document.getElementById('severity').value;
            const submitMessage = document.getElementById('submit-message');
            const submitButton = document.getElementById('submit-btn');

            if (description === '' || steps === '' || category === '' || severity === '') {
                alert('Please fill out all required fields.');
                return false; // Prevent form submission
            }

            // Show the submitting message
            submitMessage.style.display = 'block';
            // Disable the submit button to prevent multiple submissions
            submitButton.disabled = true;
            submitButton.textContent = 'Submitting...';

            return true; // Allow form submission
        }
    </script>

</head>
<body>

    <div class="container">
        <div class="form-header">
            <h2>Report an Error</h2>
        </div>
        <form action="error/submit" method="post" onsubmit="return validateForm()">
            
            <!-- Error Description -->
            <div class="form-group">
                <label for="description">Error Description <span class="required-field">*</span></label>
                <textarea id="description" name="description" required></textarea>
                <small>Provide a detailed description of the error you encountered.</small>
            </div>

            <!-- Steps Taken Before Error -->
            <div class="form-group">
                <label for="steps">Steps to Reproduce <span class="required-field">*</span></label>
                <textarea id="steps" name="steps" required></textarea>
                <small>Describe the steps you took leading to this error.</small>
            </div>

            <!-- Error Category -->
            <div class="form-group">
                <label for="category">Error Category <span class="required-field">*</span></label>
                <select id="category" name="category" required>
                    <option value="" disabled selected>Select error type</option>
                    <option value="UI">UI Error</option>
                    <option value="Backend">Backend Error</option>
                    <option value="Database">Database Error</option>
                    <option value="Network">Network Error</option>
                </select>
                <small>Select the category that best describes the error.</small>
            </div>

            <!-- Severity Level -->
            <div class="form-group">
                <label for="severity">Severity <span class="required-field">*</span></label>
                <select id="severity" name="severity" required>
                    <option value="" disabled selected>Select severity level</option>
                    <option value="Low">Low</option>
                    <option value="Medium">Medium</option>
                    <option value="High">High</option>
                </select>
                <small>Choose the impact level of the issue.</small>
            </div>

            <!-- Submit Button -->
            <button type="submit" id="submit-btn" class="submit-btn">Submit Error Report</button>
        </form>

        <!-- View Update Button -->
        <form action="viewError.jsp" method="get">
            <input type="text" name="id" placeholder="Enter Error ID to View Staff Update" required>
            <button type="submit" class="view-btn">View Update from Staff</button>
        </form>

        <!-- Submitting message -->
        <div id="submit-message" class="submit-message">Submitting your error report, please wait...</div>
    </div>

</body>
</html>
