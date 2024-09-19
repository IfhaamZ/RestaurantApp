package model;

import java.sql.Timestamp;

public class feedback {

    private int feedbackId; // Unique identifier for feedback
    private String customerName; // Customer's name
    private String customerEmail; // Customer's email
    private String feedbackText; // Customer's feedback
    private int rating; // Rating (1-5)
    private Timestamp createdAt; // Timestamp when the feedback was created

    // Constructor
    public feedback(int feedbackId, String customerName, String customerEmail, String feedbackText, int rating,
            Timestamp createdAt) {
        this.feedbackId = feedbackId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.feedbackText = feedbackText;
        this.rating = rating;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
