use restaurantdb;

INSERT INTO InventoryAudit (productID, action, oldStockLevel, newStockLevel, updatedBy) 
VALUES
('P001', 'update', 20, 30, 'staff'),
('P002', 'update', 15, 15, 'staff'),
('P003', 'update', 40, 35, 'staff'),
('P001', 'update', 30, 40, 'staff');