package com.example.medtrack;

import java.sql.Date;

public class TableRooms {
    private String name;
    private String disease;
    private String checkIn;
    private String checkOut;

    public TableRooms(String name, String disease, String checkIn, String checkOut) {
        this.name = name;
        this.disease = disease;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public String getName() {
        return name;
    }

    public String getDisease() {
        return disease;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }
}
