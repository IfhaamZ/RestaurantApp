use restaurantdb;

-- Insert sample menu items into the menu table
INSERT INTO menu (name, description, price, category) VALUES
('Margherita Pizza', 'Classic pizza with tomato sauce, mozzarella, and fresh basil.', 12.99, 'Main Course'),
('Caesar Salad', 'Crisp romaine lettuce, Caesar dressing, croutons, and Parmesan cheese.', 8.99, 'Appetizer'),
('Spaghetti Carbonara', 'Spaghetti with creamy sauce, pancetta, egg, and Parmesan cheese.', 14.99, 'Main Course'),
('Garlic Bread', 'Toasted bread with garlic butter and herbs.', 4.99, 'Appetizer'),
('Grilled Chicken', 'Grilled chicken breast served with steamed vegetables and mashed potatoes.', 16.99, 'Main Course'),
('Chocolate Lava Cake', 'Warm chocolate cake with a gooey center, served with vanilla ice cream.', 7.99, 'Dessert'),
('Tiramisu', 'Classic Italian dessert with layers of coffee-soaked ladyfingers, mascarpone, and cocoa.', 6.99, 'Dessert'),
('Margarita', 'Refreshing cocktail with tequila, lime juice, and a salted rim.', 9.99, 'Beverage'),
('Espresso', 'Strong and rich coffee served in a small cup.', 2.99, 'Beverage'),
('Iced Tea', 'Chilled black tea served with lemon.', 3.99, 'Beverage');
