package model;

import java.io.Serializable;

public class Payment implements Serializable {
    // Attributes for payment details
    private String paymentID;
    private String method; // Cash, Card, etc.
    private String cardNum;
    private String expMonth;
    private String expYear;
    private String cvn;
    private String paymentAmount;
    private String paymentDate;
    private boolean isCancelled; // New field to track payment status
    private String staffRole; // New field to store the staff role for role-based access

    public Payment() {
        this.isCancelled = false; // Payment is not cancelled by default
    }

    public Payment(String paymentID, String method, String cardNum, String expMonth, String expYear, String cvn,
            String paymentAmount, String paymentDate, String staffRole) {
        this.paymentID = paymentID;
        this.method = method;
        this.cardNum = cardNum;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.cvn = cvn;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.isCancelled = false; // Payment starts as not cancelled
        this.staffRole = staffRole; // Role-based access control
    }

    // Getters and Setters
    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    public String getCVN() {
        return cvn;
    }

    public void setCVN(String cvn) {
        this.cvn = cvn;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void cancelPayment() {
        this.isCancelled = true;
    }

    public String getStaffRole() {
        return staffRole;
    }

    public void setStaffRole(String staffRole) {
        this.staffRole = staffRole;
    }

    // Role-based access control (only staff with the right role can manage
    // payments)
    public boolean canManagePayments() {
        return "admin".equals(this.staffRole) || "staff".equals(this.staffRole);
    }
}
