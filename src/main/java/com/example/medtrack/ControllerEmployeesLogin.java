package com.example.medtrack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;

public class ControllerEmployeesLogin {
    @FXML
    private Button Back;

    @FXML
    private Button DeathStatistics;

    @FXML
    private Button Departments;

    @FXML
    private Button Employees;

    @FXML
    private Button HospitalInfoButton;

    @FXML
    private Button HospitalizedPatients;


    @FXML
    private Button RegisterNewborn;

    @FXML
    private Button RegisterPatient;

    @FXML
    private Button Rooms;

    private Label Name_L;
    private Label Education_L;
    private Label Contact_L;
    private Label Position_L;
    private Label Salary_L;
    private Label CNP_L;
    @FXML
    private TableColumn<EmployeesTable, String> CNP;

    @FXML
    private TableColumn<EmployeesTable, String> Contact;


    @FXML
    private TableColumn<EmployeesTable, String> Education;


    @FXML
    private TableColumn<EmployeesTable, String> Name;

    @FXML
    private TableColumn<EmployeesTable, String> Position;


    @FXML
    private TableColumn<EmployeesTable, Integer> Salary;

    @FXML
    private TableView<EmployeesTable> Table;
    private HelloApplication application;

    public void setApplication(HelloApplication application) {
        this.application = application;
    }

    private static int HospitalID;  // Make it static

    public static void setHospitalID(int HospitalID) {
        ControllerEmployeesLogin.HospitalID = HospitalID;
    }

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

    ObservableList<EmployeesTable> list = FXCollections.observableArrayList();

    public void initialize() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        //FILL THE TABLE
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Education.setCellValueFactory(new PropertyValueFactory<>("Education"));
        Contact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        Position.setCellValueFactory(new PropertyValueFactory<>("Position"));
        Salary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        CNP.setCellValueFactory(new PropertyValueFactory<>("CNP"));

        try {
            // Retrieve registration data
            String registrationQuery = "select p.last_name || ' ' || p.first_name AS name, s.education ,s.contact ,p2.name_position,p2.salary  ,s.cnp_staff \n" +
                    "from staff s join  person p on (s.cnp_staff = p.cnp)\n" +
                    "             join  \"position\" p2 on (s.id_staff = p2.id_staff)\n" +
                    "where s.id_hospital = ?";

            PreparedStatement preparedStatementRegistration = connectDB.prepareStatement(registrationQuery);
            preparedStatementRegistration.setInt(1, ControllerEmployeesLogin.HospitalID);
            ResultSet queryResultRegistration = preparedStatementRegistration.executeQuery();

            // Populate the list with registration data
            while (queryResultRegistration.next()) {

                String name = queryResultRegistration.getString("name");
                String education = queryResultRegistration.getString("education");
                String contact = queryResultRegistration.getString("contact");
                String position = queryResultRegistration.getString("name_position");
                Integer salary = queryResultRegistration.getInt("salary");
                String cnp = queryResultRegistration.getString("cnp_staff");

                list.add(new EmployeesTable(name, education, contact, position, salary, cnp));
            }
            Table.setItems(list);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}