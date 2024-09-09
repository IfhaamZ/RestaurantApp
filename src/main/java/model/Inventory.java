package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import model.Product; // Importing Product class

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
        Product product = productList.get(productID);
        if (product != null && newStock >= 0) {
            product.setStockQuantity(newStock);
            return true;
        }
        return false;
    }

    // Other methods related to inventory management can go here
}
