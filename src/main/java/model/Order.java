package model;

public class Order {
    private int id;
    private String customerName;
    private String orderDetails;
    private String status;

    public Order(int id, String customerName, String orderDetails, String status) {
        this.id = id;
        this.customerName = customerName;
        this.orderDetails = orderDetails;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}