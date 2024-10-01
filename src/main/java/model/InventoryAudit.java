package model;

import java.sql.Timestamp;

public class InventoryAudit {
    private String productID;
    private int oldStock;
    private int newStock;
    private Timestamp timestamp;
    private String updatedBy;

    // Getters and Setters
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getOldStock() {
        return oldStock;
    }

    public void setOldStock(int oldStock) {
        this.oldStock = oldStock;
    }

    public int getNewStock() {
        return newStock;
    }

    public void setNewStock(int newStock) {
        this.newStock = newStock;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
