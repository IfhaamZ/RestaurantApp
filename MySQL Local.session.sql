
 
INSERT INTO InventoryAudit (productID, action, oldStockLevel, newStockLevel, updatedBy) 
VALUES
('P001', 'Stock Update', 20, 30, 'JohnDoe'),
('P002', 'Stock Check', 15, 15, 'JaneSmith'),
('P003', 'Stock Decrease', 40, 35, 'Staff'),
('P001', 'Stock Increase', 30, 40, 'JohnDoe');