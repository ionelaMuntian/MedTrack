package com.example.medtrack;

public class EmployeesTable {
    private String CNP;
    private String contact;
    private String education;
    private String name;
    private String position;
    private int salary;

    // Constructors, getters, and setters

    public EmployeesTable(String name,String education,String contact, String position, int salary,String CNP) {
        this.name = name;
        this.education = education;
        this.contact = contact;
        this.position = position;
        this.salary = salary;
        this.CNP = CNP;
    }
    public String getCNP    () {
        return CNP;
    }
    public String getContact() {
        return contact;
    }
    public String getEducation() {
        return education;
    }
    public String getName() {
        return name;
    }
    public String getPosition() {
        return position;
    }
    public int getSalary() {
        return salary;
    }


}
