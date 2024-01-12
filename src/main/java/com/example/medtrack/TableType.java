package com.example.medtrack;
import java.sql.Date;
public class TableType {
    private String RegistrationDate;
    private String disease;
    private String hospital;
    private String primaryDoctor;
    private String treatment;

    public TableType( String RegistrationDate, String disease, String hospital, String primaryDoctor, String treatment) {
        this.RegistrationDate= RegistrationDate;
        this.disease = disease;
        this.hospital = hospital;
        this.primaryDoctor = primaryDoctor;
        this.treatment = treatment;
    }

    public  String getRegistrationDate() {
        return RegistrationDate;
    }

    public String getDisease() {
        return disease;
    }

    public String getHospital() {
        return hospital;
    }

    public String getPrimaryDoctor() {
        return primaryDoctor;
    }

    public String getTreatment() {
        return treatment;
    }
}
