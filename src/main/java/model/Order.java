package model;

import java.time.LocalDate;

public class Order {
    
    private int orderId;
    private int userId;
    private LocalDate reservationDate;
    private int numberOfGuests;
    
    public Order(int orderId, int userId, LocalDate reservationDate, int numberOfGuests) {
        this.orderId = orderId;
        this.userId = userId;
        this.reservationDate = reservationDate;
        this.numberOfGuests = numberOfGuests;
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public LocalDate getReservationDate() {
        return reservationDate;
    }
    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }
    public int getNumberOfGuests() {
        return numberOfGuests;
    }
    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }
}
