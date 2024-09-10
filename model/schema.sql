-- Create the 'errors' table
CREATE TABLE errors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description TEXT NOT NULL,
    steps TEXT NOT NULL,
    category VARCHAR(100),
    severity VARCHAR(50)
);

-- Add additional tables if necessary
-- CREATE TABLE another_table (...);
