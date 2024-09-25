package model;

import java.time.LocalDateTime;

public class Table {
    private int id;
    private String status;
    private int capacity;
    private LocalDateTime reservationTime;
    private String reservedByName;
    private String reservedByPhone;
    private String reservedByEmail;

    public String getReservedByName() {
        return reservedByName;
    }

    public void setReservedByName(String reservedByName) {
        this.reservedByName = reservedByName;
    }

    public String getReservedByPhone() {
        return reservedByPhone;
    }

    public void setReservedByPhone(String reservedByPhone) {
        this.reservedByPhone = reservedByPhone;
    }

    public String getReservedByEmail() {
        return reservedByEmail;
    }

    public void setReservedByEmail(String reservedByEmail) {
        this.reservedByEmail = reservedByEmail;
    }

    public Table(int id, String status, int capacity, LocalDateTime reservationTime, String reservedByName,
        String reservedByPhone, String reservedByEmail) {
        this.id = id;
        this.status = status;
        this.capacity = capacity;
        this.reservationTime = reservationTime;
        this.reservedByName = reservedByName;
        this.reservedByPhone = reservedByPhone;
        this.reservedByEmail = reservedByEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }
}
