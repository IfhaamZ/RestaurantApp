package model;

public class error {

    private int id; // Unique identifier for the error
    private String description; // Detailed description of the error
    private String steps; // Steps to reproduce the error
    private String category; // Error category (e.g., UI, Backend, Database)
    private String severity; // Severity level (Low, Medium, High)
    private String staffComments; // Comments provided by staff regarding the error

    // Constructor with staff comments
    public error(int id, String description, String steps, String category, String severity, String staffComments) {
        this.id = id;
        this.description = description;
        this.steps = steps;
        this.category = category;
        this.severity = severity;
        this.staffComments = staffComments;
    }

    // Constructor without staff comments
    public error(int id, String description, String steps, String category, String severity) {
        this.id = id;
        this.description = description;
        this.steps = steps;
        this.category = category;
        this.severity = severity;
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
}
