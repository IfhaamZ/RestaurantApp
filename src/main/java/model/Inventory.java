package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import DAO.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Inventory implements Serializable {

    // HashMap to store Product objects with their productID as the key
    private Map<String, Product> productList;
    private int lowStockThreshold = 10; // Threshold for low stock notifications

    // Constructor initializing the product list
    public Inventory() {
        this.productList = new HashMap<>();
    }

    // Add a new product to the inventory with updatedBy parameter
    public void addProduct(Product product, String updatedBy) {
        productList.put(product.getProductID(), product);
        logAudit(product.getProductID(), "add", 0, product.getStockQuantity(), updatedBy); // Log audit for adding a
                                                                                           // product
    }

    // Remove a product from the inventory by productID with updatedBy parameter
    public void removeProduct(String productID, String updatedBy) {
        Product removedProduct = productList.remove(productID);
        if (removedProduct != null) {
            logAudit(productID, "remove", removedProduct.getStockQuantity(), 0, updatedBy); // Log audit for removal
        }
    }

    // Get product details by productID
    public Product getProductByID(String productID) {
        return productList.get(productID);
    }

    // U136 - View and monitor stock levels
    public Map<String, Integer> viewStockLevels() {
        Map<String, Integer> stockLevels = new HashMap<>();
        for (Product product : productList.values()) {
            stockLevels.put(product.getName(), product.getStockQuantity());
        }
        return stockLevels;
    }

    // U137 - Receive a stock level notification
    public String checkLowStockLevels() {
        StringBuilder lowStockMessage = new StringBuilder();
        for (Product product : productList.values()) {
            if (product.getStockQuantity() <= lowStockThreshold) {
                lowStockMessage.append("Warning: Product ")
                        .append(product.getName())
                        .append(" is low on stock! Current level: ")
                        .append(product.getStockQuantity())
                        .append("\n");
            }
        }
        return lowStockMessage.length() > 0 ? lowStockMessage.toString() : "All stock levels are sufficient.";
    }

    // U139 - Update stock levels manually for a product with updatedBy parameter
    public boolean updateStockLevel(String productID, int newStock, String updatedBy) {
        Product product = productList.get(productID);
        if (product != null && newStock >= 0) {
            int oldStock = product.getStockQuantity();
            product.setStockQuantity(newStock);

            // Perform SQL update as well
            Connection connection = null;
            PreparedStatement statement = null;

            try {
                connection = DBConnector.getConnection(); // Get the database connection
                String updateQuery = "UPDATE Product SET stockQuantity = ? WHERE productID = ?";
                statement = connection.prepareStatement(updateQuery);
                statement.setInt(1, newStock);
                statement.setString(2, productID);

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    logAudit(productID, "update", oldStock, newStock, updatedBy); // Log audit for stock update
                }
                return rowsUpdated > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                // Close the resources
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    // Audit logging method
    private void logAudit(String productID, String action, int oldStock, int newStock, String updatedBy) {
        // Code to insert audit record into audit table
        // You may already have this implemented from earlier code
    }
}
