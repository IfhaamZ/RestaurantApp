package model;

public class error {

    private int id; // Unique identifier for the error
    private String description; // Detailed description of the error
    private String steps; // Steps to reproduce the error
    private String category; // Error category (e.g., UI, Backend, Database)
    private String severity; // Severity level (Low, Medium, High)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public error(int id, String description, String steps, String category, String severity) {

        this.id = id;
        this.description = description;
        this.steps = steps;
        this.category = category;
        this.severity = severity;
    }

}
