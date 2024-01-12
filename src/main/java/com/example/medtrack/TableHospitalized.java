package com.example.medtrack;

import java.sql.Date;

public class TableHospitalized {
    private String cnp;
    private String checkIn;
    private String checkOut;
    private String firstName;
    private String lastName;
    private Integer room;

    // Constructors, getters, and setters

    public TableHospitalized(String cnp, String checkIn, String checkOut, String firstName, String lastName, Integer room) {
        this.cnp = cnp;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.firstName = firstName;
        this.lastName = lastName;
        this.room = room;
    }

    public String getCnp() {
        return cnp;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getRoom() {return room;}
}


