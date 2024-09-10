package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    
    private int id;
    private String name;
    private LocalDate date;
    private LocalTime time;
    private int guests;

    public Reservation(int id, String name, LocalDate date, LocalTime time, int guests) {
    this.id = id;
    this.name = name;
    this.date = date;
    this.time = time;
    this.guests = guests;
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
    

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }


    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

}
