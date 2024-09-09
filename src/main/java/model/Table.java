package model;

public class Table {
    private int id;
    private String status;
    private int capacity;

    public Table(int id, String status, int capacity) {
        this.id = id;
        this.status = status;
        this.capacity = capacity;
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

        
}
