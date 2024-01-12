package com.example.medtrack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;

public class ControllerHospital_Info {

    @FXML
    private Label HospitalNameLabel;
    @FXML
    private Label CountryL;
    @FXML
    private Label AddressL;
    @FXML
    private Label ContactL;
    @FXML
    private Label ID_HospialL;
    @FXML
    private Label AdminH;
    @FXML
    private Label CityL;
    private HelloApplication application;

    public void setApplication(HelloApplication application) {
        this.application = application;
    }

    private static int HospitalID;  // Make it static

    public static void setHospitalID(int HospitalID) {
        ControllerHospital_Info.HospitalID = HospitalID;
        System.out.println(ControllerHospital_Info.HospitalID);
    }

    @FXML
    private Button Back;
    @FXML
    public void Back_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) Back.getScene().getWindow();
            stage.close();
            application.start_login_admin(); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

  public void HospialInfo_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) Back.getScene().getWindow();
            stage.close();
            application.start_HospitalDataBase(HospitalID); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void Employees_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) Back.getScene().getWindow();
            stage.close();
            application.start_employees(HospitalID); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void HospitalizedPatients_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) Back.getScene().getWindow();
            stage.close();
            application.start_hospitalized_patients(HospitalID); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void DeathStatistics_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) Back.getScene().getWindow();
            stage.close();
            application.start_death_statistics(HospitalID); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void Departments_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) Back.getScene().getWindow();
            stage.close();
            application.start_departments(HospitalID); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void Rooms_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) Back.getScene().getWindow();
            stage.close();
            application.start_rooms(HospitalID); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }
    public void RegisterPatient_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) Back.getScene().getWindow();
            stage.close();
            application.start_registerPatient(HospitalID); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void RegisterNewborn_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) Back.getScene().getWindow();
            stage.close();
            application.start_registerNewBorn(HospitalID);// Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    @FXML
    public void initialize() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        if (connectDB != null) {
            String NAME = "SELECT name FROM hopital2 WHERE id_hospital = ?";
            String COUNTRY = "SELECT country FROM hopital2 WHERE id_hospital = ?";
            String CITY = "SELECT city FROM hopital2 WHERE id_hospital = ?";
            String ADDRESS = "SELECT address FROM hopital2 WHERE id_hospital = ?";
            String CONTACT = "SELECT phone FROM hopital2 WHERE id_hospital = ?";
            String ADMIN = "select p.last_name || ' ' || p.first_name AS admin_name\n" +
                    "from staff s join hopital2 h on(h.id_admin = s.id_staff)\n" +
                    "             join person p on (s.cnp_staff = p.cnp)\n" +
                    "where h.id_hospital = ?";

            ID_HospialL.setText(String.valueOf(ControllerHospital_Info.HospitalID));

            try {
                // Retrieve name
                PreparedStatement preparedStatementFirstName = connectDB.prepareStatement(NAME);
                preparedStatementFirstName.setInt(1, HospitalID);
                ResultSet queryResultFirstName = preparedStatementFirstName.executeQuery();
                if (queryResultFirstName.next()) {
                    HospitalNameLabel.setText(queryResultFirstName.getString("name"));
                } else {
                    System.out.println("not found");
                }

// Retrieve country
                PreparedStatement preparedStatementLastName = connectDB.prepareStatement(COUNTRY);
                preparedStatementLastName.setInt(1, HospitalID);
                ResultSet queryResultLastName = preparedStatementLastName.executeQuery();
                if (queryResultLastName.next()) {
                    CountryL.setText(queryResultLastName.getString("country"));
                }

// Retrieve city
                PreparedStatement preparedStatementBirthPlace = connectDB.prepareStatement(CITY);
                preparedStatementBirthPlace.setInt(1, HospitalID);
                ResultSet queryResultBirthPlace = preparedStatementBirthPlace.executeQuery();
                if (queryResultBirthPlace.next()) {
                    CityL.setText(queryResultBirthPlace.getString("city"));
                }

// Retrieve address
                PreparedStatement preparedStatementNationality = connectDB.prepareStatement(ADDRESS);
                preparedStatementNationality.setInt(1, HospitalID);
                ResultSet queryResultNationality = preparedStatementNationality.executeQuery();
                if (queryResultNationality.next()) {
                    AddressL.setText(queryResultNationality.getString("address"));
                }

// Retrieve contact
                PreparedStatement preparedStatementBirthDate = connectDB.prepareStatement(CONTACT);
                preparedStatementBirthDate.setInt(1, HospitalID);
                ResultSet queryResultBirthDate = preparedStatementBirthDate.executeQuery();
                if (queryResultBirthDate.next()) {
                    ContactL.setText(queryResultBirthDate.getString("phone"));
                }

// Retrieve admin
                PreparedStatement preparedStatementAddress = connectDB.prepareStatement(ADMIN);
                preparedStatementAddress.setInt(1, HospitalID);
                ResultSet queryResultAddress = preparedStatementAddress.executeQuery();
                if (queryResultAddress.next()) {
                    AdminH.setText(queryResultAddress.getString("admin_name"));
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}