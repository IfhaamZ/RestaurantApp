use restaurantdb;


INSERT INTO errors (description, steps, category, severity, staff_comments, status) VALUES
('Login page not loading', 'Navigate to login page and observe', 'UI Bug', 'High', 'Reproduced and assigned to development team', 'In Progress'),
('Payment gateway failure', 'Attempt to make a payment with Visa card', 'Payment Issue', 'Critical', 'Reported to payment provider', 'Resolved'),
('Image upload error', 'Try uploading an image larger than 5MB', 'Upload Issue', 'Medium', 'User notified about file size limit', 'In Progress'),
('Slow loading on homepage', 'Open homepage and observe load time', 'Performance', 'Low', 'Will be optimized in the next sprint', 'In Progress');