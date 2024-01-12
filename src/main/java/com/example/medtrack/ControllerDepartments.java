package com.example.medtrack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class ControllerDepartments {
    @FXML
    private TableColumn<TableDepartment, String> Department;
    @FXML
    private TableColumn<TableDepartment, String> Head;

    @FXML
    private TableView<TableDepartment> Table;
    ObservableList<TableDepartment> list = FXCollections.observableArrayList();
    private HelloApplication application;

    public void setApplication(HelloApplication application) {
        this.application = application;
    }

    private static int HospitalID;  // Make it static

    public static void setHospitalID(int HospitalID) {
        ControllerDepartments.HospitalID = HospitalID;
    }

    @FXML
    public void Back_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) Table.getScene().getWindow();
            stage.close();
            application.start_login_admin(); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void HospialInfo_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) Table.getScene().getWindow();
            stage.close();
            application.start_HospitalDataBase(HospitalID); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void Employees_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) Table.getScene().getWindow();
            stage.close();
            application.start_employees(HospitalID); // Call the start2 method from the application

        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void HospitalizedPatients_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) Table.getScene().getWindow();
            stage.close();
            application.start_hospitalized_patients(HospitalID); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void DeathStatistics_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) Table.getScene().getWindow();
            stage.close();
            application.start_death_statistics(HospitalID); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void Departments_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) Table.getScene().getWindow();
            stage.close();
            application.start_departments(HospitalID); // Call the start2 method from the application

        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void Rooms_onAction(ActionEvent e) throws IOException {
        if (application != null) {

            Stage stage = (Stage) Table.getScene().getWindow();
            stage.close();
            application.start_rooms(HospitalID); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void RegisterPatient_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) Table.getScene().getWindow();
            stage.close();
            application.start_registerPatient(HospitalID); // Call the start2 method from the application

        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void RegisterNewborn_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) Table.getScene().getWindow();
            stage.close();
            application.start_registerNewBorn(HospitalID);// Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }
    public void initialize() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        //FILL THE TABLE
        Department.setCellValueFactory(new PropertyValueFactory<>("Department"));
        Head.setCellValueFactory(new PropertyValueFactory<>("Head"));

        try {
            // Retrieve registration data
            String registrationQuery = "select d.department_name, p.last_name || ' ' || p.first_name AS head\n" +
                    "from department d join staff s on (d.id_head = s.id_staff)\n" +
                    "                  join person p on(s.cnp_staff = p.cnp)\n" +
                    "where d.id_hospital = ?";

            PreparedStatement preparedStatementRegistration = connectDB.prepareStatement(registrationQuery);
            preparedStatementRegistration.setInt(1, ControllerDepartments.HospitalID);
            ResultSet queryResultRegistration = preparedStatementRegistration.executeQuery();

            // Populate the list with registration data
            while (queryResultRegistration.next()) {

                String department = queryResultRegistration.getString("department_name");
                String head = queryResultRegistration.getString("head");

                list.add(new TableDepartment(department,head));
            }
            Table.setItems(list);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}