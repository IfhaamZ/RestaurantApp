use restaurantdb;

INSERT INTO orders (customerName, orderDetails, status, created_at)
VALUES 
('John Doe', '2x Margherita Pizza, 1x Caesar Salad', 'completed', '2024-09-01 12:15:30'),
('Jane Smith', '1x Spaghetti Carbonara, 1x Garlic Bread', 'preparing', '2024-09-02 13:45:50'),
('Sam Wilson', '3x Cheeseburger, 2x French Fries', 'completed', '2024-09-03 14:20:10'),
('Alex Johnson', '1x Grilled Chicken, 1x Mashed Potatoes', 'delivered', '2024-09-04 12:10:00'),
('Emily Davis', '4x Sushi Roll, 2x Miso Soup', 'pending', '2024-09-05 19:30:00'),
('Michael Brown', '2x Tacos, 1x Nachos', 'canceled', '2024-09-06 11:45:25'),
('Olivia Martinez', '1x Vegan Burger, 1x Sweet Potato Fries', 'preparing', '2024-09-07 20:25:40'),
('Liam White', '5x Chicken Wings, 3x Onion Rings', 'completed', '2024-09-08 13:35:50'),
('Sophia Green', '2x Grilled Salmon, 1x Caesar Salad', 'delivered', '2024-09-09 14:00:15'),
('William Clark', '1x Steak, 1x Baked Potato, 1x Side Salad', 'pending', '2024-09-10 18:18:00');
