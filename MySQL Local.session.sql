use restaurantdb;

-- Inserting sample feedback entries into the feedback table
INSERT INTO feedback (customer_name, customer_email, feedback_text, rating, staff_response) VALUES
('John Doe', 'john.doe@example.com', 'Great service and amazing food!', 5, 'Thank you for the positive feedback!'),
('Jane Smith', 'jane.smith@example.com', 'Food was cold when delivered.', 2, 'We are sorry for the inconvenience, we will improve our service.'),
('Alice Johnson', 'alice.johnson@example.com', 'The ambiance was perfect, but the food was average.', 3, NULL),
('Bob Brown', 'bob.brown@example.com', 'Fantastic experience! Will definitely come again.', 5, 'We look forward to seeing you again!'),
('Emily Davis', 'emily.davis@example.com', 'Wait time was too long, but the food was good.', 4, 'We apologize for the wait time and are working to speed things up.'),
('Chris Lee', 'chris.lee@example.com', 'Loved the desserts, but the main course was lacking flavor.', 3, 'We appreciate your feedback and will work on improving our main courses.')