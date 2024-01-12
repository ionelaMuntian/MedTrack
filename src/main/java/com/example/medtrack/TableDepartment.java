package com.example.medtrack;

public class TableDepartment {
    private String department;
    private String head;


    public TableDepartment( String department, String head) {
        this.department= department;
        this.head = head;
    }

    public  String getDepartment() {
        return department;
    }

    public String getHead() {return head;}

}
