package com.example.medtrack;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class ControllerNewborn {
    @FXML
    private Button DeleteReg;

    @FXML
    private TextField Address_L;

    @FXML
    private TextField BirthDate_L;

    @FXML
    private TextField CNP_L;

    @FXML
    private Label E_Positions;

    @FXML
    private Label E_Positions1;

    @FXML
    private Label E_Salary;

    @FXML
    private Label EmployeeName;

    @FXML
    private Label Error_L;

    @FXML
    private TextField FirstName_L;

    @FXML
    private TextField LastName_L;

    @FXML
    private TextField Nationality_L;

    @FXML
    private TextField PlaceOfBirth_L;

    @FXML
    private Button Register;

    private HelloApplication application;

    public void setApplication(HelloApplication application) {
        this.application = application;
    }

    private static int HospitalID;  // Make it static

    public static void setHospitalID(int HospitalID) {
        ControllerNewborn.HospitalID = HospitalID;
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
            application.start_registerNewBorn(HospitalID); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }
    public void DeleteReg_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) Back.getScene().getWindow();
            stage.close();
            application.start_deleteRegistration(HospitalID);// Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void Register_onAction(ActionEvent e) throws IOException, SQLException {
        register();
    }

    public void register() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        if (connectDB != null) {
            // Check if CNP already exists
            String checkCNPQuery = "SELECT COUNT(*) AS cnpCount FROM person WHERE cnp = ?";
            PreparedStatement preparedStatementCheckCNP = connectDB.prepareStatement(checkCNPQuery);
            preparedStatementCheckCNP.setString(1, CNP_L.getText());
            ResultSet queryResultCheckCNP = preparedStatementCheckCNP.executeQuery();
            queryResultCheckCNP.next(); // Move the cursor to the first row

            int cnpCount = queryResultCheckCNP.getInt("cnpCount");

            if (cnpCount == 0) {
                // CNP does not exist, proceed with the registration
                Date BDate = Date.valueOf(BirthDate_L.getText());

                // Insert in the person table
                String insertPerson = "INSERT INTO person (cnp, last_name, first_name, place_of_birth, nationality, address, birth_date)" +
                        " " + "VALUES (?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement preparedStatementIns = connectDB.prepareStatement(insertPerson);
                preparedStatementIns.setString(1, CNP_L.getText());
                preparedStatementIns.setString(2, LastName_L.getText());
                preparedStatementIns.setString(3, FirstName_L.getText());
                preparedStatementIns.setString(4, PlaceOfBirth_L.getText());
                preparedStatementIns.setString(5, Nationality_L.getText());
                preparedStatementIns.setString(6, Address_L.getText());
                preparedStatementIns.setDate(7, BDate);

                int affectedRowsStay = preparedStatementIns.executeUpdate();

                if (affectedRowsStay > 0) {
                    System.out.println("Patient information inserted successfully!");
                    Error_L.setText("Patient information inserted successfully!");
                } else {
                    System.out.println("Patient information could NOT be inserted!");
                }
            } else {
                // CNP already exists, show an error message
                Error_L.setText("Error: CNP already exists in the database!");
            }
        }
    }

}