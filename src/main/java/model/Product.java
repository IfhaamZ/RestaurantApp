package model;

import java.io.Serializable;

public class Product implements Serializable {

    private String productID;
    private String name;
    private int stockQuantity;

    public Product(String productID, String name, int stockQuantity) {
        this.productID = productID;
        this.name = name;
        this.stockQuantity = stockQuantity;
    }

    public String getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
