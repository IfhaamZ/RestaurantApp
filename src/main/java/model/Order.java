public class Order {
    private int orderId;
    private int userId;
    private String reservationDate;
    private int numberOfGuests;

    // Constructors, getters and setters
    public Order(int orderId, int userId, String reservationDate, int numberOfGuests) {
        this.orderId = orderId;
        this.userId = userId;
        this.reservationDate = reservationDate;
        this.numberOfGuests = numberOfGuests;
    }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getReservationDate() { return reservationDate; }
    public void setReservationDate(String reservationDate) { this.reservationDate = reservationDate; }

    public int getNumberOfGuests() { return numberOfGuests; }
    public void setNumberOfGuests(int numberOfGuests) { this.numberOfGuests = numberOfGuests; }
}
