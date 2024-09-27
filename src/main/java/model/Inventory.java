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
    public void addProduct(Product product, String updatedBy) {
        productList.put(product.getProductID(), product);
        logAudit(product.getProductID(), "add", 0, product.getStockQuantity(), updatedBy);
    }

    // Remove a product from the inventory by productID
    public void removeProduct(String productID, String updatedBy) {
        Product product = productList.get(productID);
        if (product != null) {
            logAudit(productID, "delete", product.getStockQuantity(), 0, updatedBy);
            productList.remove(productID);
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

    // U139 - Update stock levels manually for a product
    public boolean updateStockLevel(String productID, int newStock, String updatedBy) {
        // Update the in-memory map first
        Product product = productList.get(productID);
        if (product != null && newStock >= 0) {
            int oldStock = product.getStockQuantity();
            product.setStockQuantity(newStock);

            // Perform SQL update and log the action
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
                    logAudit(productID, "update", oldStock, newStock, updatedBy); // Log the audit
                    return true;
                }
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

    // Log changes in the inventory_audit table
    private void logAudit(String productID, String action, int oldValue, int newValue, String updatedBy) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnector.getConnection(); // Get the database connection
            String insertAuditQuery = "INSERT INTO inventory_audit (product_id, action, old_value, new_value, updated_by) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(insertAuditQuery);
            statement.setString(1, productID);
            statement.setString(2, action);
            statement.setInt(3, oldValue);
            statement.setInt(4, newValue);
            statement.setString(5, updatedBy);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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

}
