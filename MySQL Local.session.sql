use restaurantdb;

ALTER TABLE users ADD COLUMN role VARCHAR(50) NOT NULL;

-- Mock data
INSERT INTO users (name, email, password, role) 
VALUES 
('John Doe', 'john@example.com', 'password123', 'customer'),
('Jane Smith', 'jane@example.com', 'password456', 'staff'),
('Admin User', 'admin@example.com', 'adminpass', 'admin');