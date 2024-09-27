package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import DAO.DBConnector;
import model.Product;
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

    // Add a new product to the inventory
    public void addProduct(Product product) {
        productList.put(product.getProductID(), product);
    }

    // Remove a product from the inventory by productID
    public void removeProduct(String productID) {
        productList.remove(productID);
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

    // U139 - Update stock levels manually for a product
    public boolean updateStockLevel(String productID, int newStock) {
        // Update the in-memory map first
        Product product = productList.get(productID);
        if (product != null && newStock >= 0) {
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

}
