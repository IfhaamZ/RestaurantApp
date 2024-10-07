package model;

import java.util.Date;

public class Order {
    private int orderId;
    private String customerName;
    private String dishName;
    private String action;
    

    private int quantity;
    private Date orderDate;

    public Order(int orderId, String customerName, String dishName, int quantity, Date orderDate) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.dishName = dishName;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    public Order(int orderId, String customerName, String dishName, String action) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.dishName = dishName;
        this.action = action;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
