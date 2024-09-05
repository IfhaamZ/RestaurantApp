package model;

import java.util.Date;

public class Event {
    
    private int id;
    private String name;
    private String description;
    private Date date;
    private String type;
    
    public Event(int id, String name, String description, Date date, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.type = type;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
