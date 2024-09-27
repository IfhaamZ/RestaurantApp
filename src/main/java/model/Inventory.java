package model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import DAO.DBConnector;

/**
 * Inventory class to manage the products and track stock levels.
 */
public class Inventory implements Serializable {

    // HashMap to store Product objects with their productID as the key
    private Map<String, Product> productList;
    private int lowStockThreshold = 10; // Threshold for low stock notifications

    // Constructor initializing the product list
    public Inventory() {
        this.productList = new HashMap<>();
    }

    /**
     * Loads products from the database and populates the in-memory product list.
     */
    public void loadProductsFromDB() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnector.getConnection();
            String query = "SELECT * FROM Product"; // Query to fetch all products from DB
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            // Clear the in-memory product list
            productList.clear();

            // Iterate over the results and populate the product list
            while (resultSet.next()) {
                String productID = resultSet.getString("productID");
                String productName = resultSet.getString("productName");
                int stockQuantity = resultSet.getInt("stockQuantity");

                // Create a Product object and add it to the in-memory productList
                Product product = new Product(productID, productName, stockQuantity);
                productList.put(productID, product);
            }

            System.out.println("Products successfully loaded from the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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

    /**
     * Adds a new product to the inventory.
     * 
     * @param product   Product object to be added.
     * @param updatedBy The user who is adding the product.
     */
    public void addProduct(Product product, String updatedBy) {
        productList.put(product.getProductID(), product);
        logAudit(product.getProductID(), "add", 0, product.getStockQuantity(), updatedBy); // Log audit for adding a
                                                                                           // product
    }

    /**
     * Removes a product from the inventory by its productID.
     * 
     * @param productID ID of the product to remove.
     * @param updatedBy The user who is removing the product.
     */
    public void removeProduct(String productID, String updatedBy) {
        Product removedProduct = productList.remove(productID);
        if (removedProduct != null) {
            logAudit(productID, "remove", removedProduct.getStockQuantity(), 0, updatedBy); // Log audit for product
                                                                                            // removal
        }
    }

    /**
     * Get product details by productID.
     * 
     * @param productID The ID of the product to get.
     * @return The Product object if found, or null.
     */
    public Product getProductByID(String productID) {
        return productList.get(productID);
    }

    /**
     * U136 - View and monitor stock levels.
     * 
     * @return A map of product names and their current stock levels.
     */
    public Map<String, Integer> viewStockLevels() {
        Map<String, Integer> stockLevels = new HashMap<>();
        for (Product product : productList.values()) {
            stockLevels.put(product.getName(), product.getStockQuantity());
        }
        return stockLevels;
    }

    /**
     * U137 - Check for low stock levels.
     * 
     * @return A message showing which products are low on stock.
     */
    public String checkLowStockLevels() {
        StringBuilder lowStockMessage = new StringBuilder();
        boolean hasLowStock = false; // Flag to check if any product has low stock

        // Loop through products to check stock levels
        for (Product product : productList.values()) {
            if (product.getStockQuantity() <= lowStockThreshold) {
                lowStockMessage.append("Warning: Product ")
                        .append(product.getName())
                        .append(" is low on stock! Current level: ")
                        .append(product.getStockQuantity())
                        .append("\n");
                hasLowStock = true; // Set flag to true if a low stock product is found
            }
        }

        // If there are no low stock products, return the "All stock levels are
        // sufficient" message
        if (!hasLowStock) {
            return "All stock levels are sufficient.";
        }

        // Return only the low stock warnings if there are any
        return lowStockMessage.toString();
    }

    /**
     * U139 - Updates the stock level for a product manually.
     * Updates the stock both in-memory and in the database.
     * 
     * @param productID The ID of the product to update.
     * @param newStock  The new stock quantity.
     * @param updatedBy The user who is updating the stock.
     * @return true if the stock was successfully updated, false otherwise.
     */
    public boolean updateStockLevel(String productID, int newStock, String updatedBy) {
        Product product = productList.get(productID);
        if (product != null && newStock >= 0) {
            System.out.println("Updating product: " + productID + " with new stock: " + newStock);

            int oldStock = product.getStockQuantity();

            // Perform SQL update first
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
                    System.out.println("Successfully updated product in DB: " + productID);
                    product.setStockQuantity(newStock); // Update in-memory product
                    logAudit(productID, "update", oldStock, newStock, updatedBy); // Log audit for stock update
                    return true;
                } else {
                    System.out.println("Product not updated in DB: " + productID);
                }
            } catch (SQLException e) {
                System.err.println("Error updating product in DB: " + productID);
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
        } else {
            System.out.println("Product not found in memory or invalid stock quantity: " + productID);
        }
        return false;
    }

    /**
     * Logs audit information when any stock changes occur.
     * 
     * @param productID The ID of the product.
     * @param action    The action performed (add, remove, update).
     * @param oldStock  The old stock quantity.
     * @param newStock  The new stock quantity.
     * @param updatedBy The user who performed the action.
     */
    private void logAudit(String productID, String action, int oldStock, int newStock, String updatedBy) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DBConnector.getConnection(); // Get the database connection
            String insertAuditQuery = "INSERT INTO inventory_audit (productID, action, oldStock, newStock, updatedBy, timestamp) "
                    + "VALUES (?, ?, ?, ?, ?, NOW())";
            statement = connection.prepareStatement(insertAuditQuery);
            statement.setString(1, productID);
            statement.setString(2, action);
            statement.setInt(3, oldStock);
            statement.setInt(4, newStock);
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
