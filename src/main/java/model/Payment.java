package model;

import java.io.Serializable;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Payment implements Serializable {
    // Ensure the key is exactly 16 characters (16 bytes for AES-128 encryption)
    private static final String SECRET_KEY = "1234567890123456"; // Must be 16 characters

    // Attributes for payment details
    private String paymentID;
    private String method; // Cash, Card, etc.
    private String cardNum;
    private String expMonth;
    private String expYear;
    private String cvn;
    private String paymentAmount;
    private String paymentDate;
    private boolean isCancelled; // Field to track payment status
    private String staffRole; // Field to store the staff role for role-based access

    public Payment() {
        this.isCancelled = false; // Payment is not cancelled by default
    }

    public Payment(String paymentID, String method, String cardNum, String expMonth, String expYear, String cvn,
            String paymentAmount, String paymentDate, String staffRole) throws Exception {
        this.paymentID = paymentID;
        this.method = method;
        this.cardNum = encrypt(cardNum);
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.cvn = encrypt(cvn);
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.isCancelled = false; // Payment starts as not cancelled
        this.staffRole = staffRole;
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

    public void setCardNum(String cardNum) throws Exception {
        this.cardNum = encrypt(cardNum);
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

    public void setCVN(String cvn) throws Exception {
        this.cvn = encrypt(cvn);
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

    // Encryption method
    private static String encrypt(String data) throws Exception {
        System.out.println("Key Length: " + SECRET_KEY.getBytes().length); // Debugging

        if (SECRET_KEY.getBytes().length != 16) {
            throw new IllegalArgumentException("AES key length must be 16 bytes.");
        }

        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
    }

    // Decryption method
    public static String decrypt(String encryptedData) throws Exception {
        if (SECRET_KEY.getBytes().length != 16) {
            throw new IllegalArgumentException("AES key length must be 16 bytes.");
        }

        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)));
    }

    // Methods to get decrypted card number and CVN
    public String getCardNumDecrypted() throws Exception {
        return decrypt(this.cardNum); // Decrypt before displaying
    }

    public String getCVNDecrypted() throws Exception {
        return decrypt(this.cvn); // Decrypt before displaying
    }

    // Simulate payment processing
    public void processPayment() {
        // Simulate payment processing
        boolean paymentSuccess = true; // Let's assume the payment goes through

        if (paymentSuccess) {
            this.isCancelled = false; // Payment is successful
            System.out.println("Payment processed successfully!");
        } else {
            this.cancelPayment(); // Payment failed
            System.out.println("Payment failed. Please try again.");
        }
    }

    // Role-based access control (only staff with the right role can manage
    // payments)
    public boolean canManagePayments() {
        return "admin".equals(this.staffRole) || "staff".equals(this.staffRole);
    }

    public String getStaffRole() {
        return staffRole;
    }

    public void setStaffRole(String staffRole) {
        this.staffRole = staffRole;
    }
}
