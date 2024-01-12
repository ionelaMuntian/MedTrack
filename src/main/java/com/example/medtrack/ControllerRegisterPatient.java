package com.example.medtrack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Date;

public class ControllerRegisterPatient {

    @FXML
    private ChoiceBox<String> DiseaseC;
    ChoiceBox<String> choiceBox = new ChoiceBox<>();

    @FXML
    private Button Back;

    @FXML
    private TextField CNP_Field;

    @FXML
    private TextField CheckIn_Field;

    @FXML
    private TextField CheckOut_Field;

    @FXML
    private TextField Date_Field;

    @FXML
    private Button DeathStatistics;

    @FXML
    private Button Departments;

    @FXML
    private Label E_Salary;

    @FXML
    private Label EmployeeName;

    @FXML
    private Button Employees;

    @FXML
    private Label ErrorLabel;

    @FXML
    private TextField FirstName_Field;

    @FXML
    private Button HospitalInfoButton;

    @FXML
    private Button HospitalizedPatients;

    @FXML
    private TextField IDHospital_Field;

    @FXML
    private TextField Disease_Field;

    @FXML
    private TextField LastName_Field;

    @FXML
    private TextField PrimaryDrCNP_Field;

    @FXML
    private Button Register;

    @FXML
    private Button RegisterNewborn;

    @FXML
    private Button RegisterPatient;

    @FXML
    private TextField Room_Field;

    @FXML
    private Button Rooms;

    @FXML
    private TextField Treatment_Field;

    private HelloApplication application;

    public void setApplication(HelloApplication application) {
        this.application = application;
    }

    private static int HospitalID;  // Make it static

    public static void setHospitalID(int HospitalID) {
        ControllerRegisterPatient.HospitalID = HospitalID;
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
            application.start_registerNewBorn(HospitalID); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void Register_onAction(ActionEvent e) throws IOException {
        register();
    }

    @FXML
    public void initialize() {
        // Initialize the ChoiceBox with diseases retrieved from the database
        populateDiseaseChoices();
    }

    private void populateDiseaseChoices() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        if (connectDB != null) {
            try {
                String fetchDiseases = "SELECT name FROM disease";

                PreparedStatement preparedStatementDiseases = connectDB.prepareStatement(fetchDiseases);
                ResultSet queryResultDiseases = preparedStatementDiseases.executeQuery();

                ObservableList<String> diseases = FXCollections.observableArrayList();

                while (queryResultDiseases.next()) {
                    String diseaseName = queryResultDiseases.getString("name");
                    diseases.add(diseaseName);
                }

                DiseaseC.setItems(diseases);

            } catch (SQLException e) {
                throw new RuntimeException("Error fetching diseases from the database", e);
            }
        }
    }
    public void register() {


        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        if (connectDB != null) {
            String first_name = "select first_name from person where cnp = ?";
            String last_name = "select last_name from person where cnp = ?";

            try {
                // Retrieve first name
                PreparedStatement preparedStatementFirstName = connectDB.prepareStatement(first_name);
                preparedStatementFirstName.setString(1, CNP_Field.getText());
                ResultSet queryResultFirstName = preparedStatementFirstName.executeQuery();

                PreparedStatement preparedStatementLastName = connectDB.prepareStatement(last_name);
                preparedStatementLastName.setString(1, CNP_Field.getText());
                ResultSet queryResultLastName = preparedStatementLastName.executeQuery();

                if (queryResultFirstName.next() && queryResultLastName.next()) {
                    String FN = queryResultFirstName.getString("first_name");
                    String LN = queryResultLastName.getString("last_name");

                    if (FN.equals(FirstName_Field.getText()) && LN.equals(LastName_Field.getText())) {

                        //Find fields that are deduced
                        String ID_PRIMARY_DR = "select id_staff from staff where cnp_staff = ?";

                        PreparedStatement preparedStatementPrimaryDr = connectDB.prepareStatement(ID_PRIMARY_DR);
                        preparedStatementPrimaryDr.setString(1, PrimaryDrCNP_Field.getText());
                        ResultSet queryResultPrimaryDr = preparedStatementPrimaryDr.executeQuery();

                        //Find data that can be deduced for stay table
                        String ROOM = "select id_room from room where room_name = ? and hospital_id =?";

                        PreparedStatement preparedStatementRoom = connectDB.prepareStatement(ROOM);
                        preparedStatementRoom.setString(1, Room_Field.getText());
                        preparedStatementRoom.setInt(2, ControllerRegisterPatient.HospitalID);
                        ResultSet queryResultRoom = preparedStatementRoom.executeQuery();

                        if (queryResultPrimaryDr.next()) {
                            Integer id_primary_doc = queryResultPrimaryDr.getInt("id_staff");

                            String selectedDisease = DiseaseC.getValue();//
                            String ID_DISEASE = "select id_disease from disease where name = ?";

                            PreparedStatement preparedStatementDisease = connectDB.prepareStatement(ID_DISEASE);
                            preparedStatementDisease.setString(1, selectedDisease);//
                            ResultSet queryResultDisease = preparedStatementDisease.executeQuery();

                            if (queryResultRoom.next()) {
                                String idRoom = queryResultRoom.getString("id_room");

                                Date checkInDate = Date.valueOf(CheckIn_Field.getText());
                                Date checkOutDate = Date.valueOf(CheckOut_Field.getText());

                                //Insert in stay table
                                String insertStay = "INSERT INTO stay (id_hospital,check_in,check_out,id_room) " + "VALUES (?, ?, ?, ?)";

                                PreparedStatement preparedStatementIns = connectDB.prepareStatement(insertStay, PreparedStatement.RETURN_GENERATED_KEYS);
                                preparedStatementIns.setInt(1, ControllerRegisterPatient.HospitalID);
                                preparedStatementIns.setDate(2, checkInDate);
                                preparedStatementIns.setDate(3, checkOutDate);
                                preparedStatementIns.setInt(4, Integer.parseInt(idRoom));

                                int affectedRowsStay = preparedStatementIns.executeUpdate();

                                if (affectedRowsStay > 0) {
                                    //restive stay_id from (was inserted automatically)
                                    ResultSet generatedKeys = preparedStatementIns.getGeneratedKeys();

                                    if (generatedKeys.next()) {
                                        int idStay = generatedKeys.getInt("id_stay");

                                        // Data inserted successfully
                                        System.out.println("Patient information inserted successfully!");

                                        if (queryResultDisease.next()) {

                                           // Integer idDisease = queryResultDisease.getInt("id_disease");
                                            Integer idDisease = queryResultDisease.getInt("id_disease");

                                            // Insert data into the patient table
                                            String insertPatient = "INSERT INTO registration (registr_cnp, id_hospital, date, id_disease, id_primary_doctor,personal_treatment,stay_id) " + "VALUES (?, ?, ?, ?, ?, ?,?)";

                                            PreparedStatement preparedStatementInsert = connectDB.prepareStatement(insertPatient);
                                            preparedStatementInsert.setString(1, CNP_Field.getText());
                                            preparedStatementInsert.setInt(2, ControllerRegisterPatient.HospitalID);
                                            preparedStatementInsert.setDate(3, Date.valueOf(Date_Field.getText()));
                                            preparedStatementInsert.setInt(4, idDisease);
                                            preparedStatementInsert.setInt(5, id_primary_doc);
                                            preparedStatementInsert.setString(6, Treatment_Field.getText());
                                            preparedStatementInsert.setInt(7, idStay);

                                            int affectedRowsPatient = preparedStatementInsert.executeUpdate();

                                            if (affectedRowsPatient > 0) {
                                                // Stay information inserted successfully
                                                System.out.println("Stay information inserted successfully!");
                                            } else {
                                                // Failed to insert stay information
                                                System.err.println("Failed to insert stay information!");
                                            }
                                        } else {
                                            // Handle the case where the room information is not found
                                            System.err.println("Room information not found!");
                                        }
                                    } else {
                                        System.err.println("Failed to retrive id_stay information!");
                                    }
                                } else {
                                    // Failed to insert patient information
                                    System.err.println("Failed to insert patient information!");
                                }

                            } else {
                                // Handle the case where disease information is not found
                                System.err.println("Disease information not found!");
                            }
                        } else {
                            // Handle the case where primary doctor information is not found
                            System.err.println("Primary doctor information not found!");
                        }
                    } else {
                        ErrorLabel.setText("The name does not match the CNP!");
                    }
                } else {
                    ErrorLabel.setText("This person does not exist!");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}