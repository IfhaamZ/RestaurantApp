package model;

import java.sql.Timestamp;

public class error {
    // Fields to store error details
    private int id; // Unique identifier for the error
    private String description; // Detailed description of the error
    private String steps; // Steps to reproduce the error
    private String category; // Error category (e.g., UI, Backend, Database)
    private String severity; // Severity level (Low, Medium, High)
    private String staffComments; // Comments provided by staff regarding the error
    private Timestamp createdAt; // Timestamp when the error was created
    private String status; // Status of the error report (e.g., "In Progress", "Resolved")

    // Constructor with staff comments, createdAt timestamp, and status
    public error(int id, String description, String steps, String category, String severity, String staffComments,
            Timestamp createdAt, String statusParam) {
        this.id = id;
        this.description = description;
        this.steps = steps;
        this.category = category;
        this.severity = severity;
        this.staffComments = staffComments;
        this.createdAt = createdAt;
        this.status = statusParam; // Initialize status
    }

    // Constructor without staff comments, but with createdAt timestamp and status
    public error(int id, String description, String steps, String category, String severity, Timestamp createdAt,
            String statusParam) {
        this.id = id;
        this.description = description;
        this.steps = steps;
        this.category = category;
        this.severity = severity;
        this.createdAt = createdAt;
        this.status = statusParam; // Initialize status
    }

    public error(int id, String description, String steps, String category, String severity, Timestamp createdAt) {
        this.id = id;
        this.description = description;
        this.steps = steps;
        this.category = category;
        this.severity = severity;
        this.createdAt = createdAt;
    }

    // Getter and Setter for ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for Description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter and Setter for Steps
    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    // Getter and Setter for Category
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Getter and Setter for Severity
    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    // Getter and Setter for Staff Comments
    public String getStaffComments() {
        return staffComments;
    }

    public void setStaffComments(String staffComments) {
        this.staffComments = staffComments;
    }

    // Getter and Setter for CreatedAt
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    // Getter and Setter for Status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
