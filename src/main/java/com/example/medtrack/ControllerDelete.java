package com.example.medtrack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ControllerDelete {
    @FXML
    private Button Back;

    @FXML
    private Button DeleteReg;

    @FXML
    private Button DeathStatistics;

    @FXML
    private Button Departments;

    @FXML
    private Label E_Position3;

    @FXML
    private Button Employees;

    @FXML
    private Button Enter;

    @FXML
    private TextField EnterID;

    @FXML
    private Label ErrorLabel;

    @FXML
    private Button HospitalInfoButton;

    @FXML
    private Button HospitalizedPatients;

    @FXML
    private Button RegisterNewborn;

    @FXML
    private Button RegisterNewborn1;

    @FXML
    private Button RegisterPatient;

    @FXML
    private Button Rooms;
    private HelloApplication application;

    public void setApplication(HelloApplication application) {
        this.application = application;
    }

    private static int HospitalID;  // Make it static

    public static void setHospitalID(int HospitalID) {
        ControllerDelete.HospitalID = HospitalID;
    }

    @FXML
    public void Back_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) EnterID.getScene().getWindow();
            stage.close();
            application.start_login_admin(); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void HospialInfo_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) EnterID.getScene().getWindow();
            stage.close();
            application.start_HospitalDataBase(HospitalID); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void Employees_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) EnterID.getScene().getWindow();
            stage.close();
            application.start_employees(HospitalID); // Call the start2 method from the application

        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void HospitalizedPatients_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) EnterID.getScene().getWindow();
            stage.close();
            application.start_hospitalized_patients(HospitalID); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void DeathStatistics_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) EnterID.getScene().getWindow();
            stage.close();
            application.start_death_statistics(HospitalID); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void Departments_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) EnterID.getScene().getWindow();
            stage.close();
            application.start_departments(HospitalID); // Call the start2 method from the application

        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void Rooms_onAction(ActionEvent e) throws IOException {
        if (application != null) {

            Stage stage = (Stage) EnterID.getScene().getWindow();
            stage.close();
            application.start_rooms(HospitalID); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void RegisterPatient_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) EnterID.getScene().getWindow();
            stage.close();
            application.start_registerPatient(HospitalID); // Call the start2 method from the application

        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void RegisterNewborn_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) EnterID.getScene().getWindow();
            stage.close();
            application.start_registerNewBorn(HospitalID);// Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void DeleteReg_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) EnterID.getScene().getWindow();
            stage.close();
            application.start_deleteRegistration(HospitalID);// Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void Enter_onAction() {
        if (!EnterID.getText().isEmpty()) {
            try {
                int registrationId = Integer.parseInt(EnterID.getText());
                initialize(registrationId);
            } catch (NumberFormatException | SQLException e) {
                System.out.println("Invalid input. Please enter a valid integer for Registration ID.");
                // You might want to display an error message to the user in your application.
            }
        } else {
            System.out.println("Please enter a Registration ID.");
            // You might want to display an error message to the user in your application.
        }
    }

    public void initialize(int registrationId) throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        // Find stay_id
        if (connectDB != null) {
            String STAY_ID_QUERY = "SELECT stay_id FROM registration WHERE registration_id = ?";
            try (PreparedStatement preparedStatementS = connectDB.prepareStatement(STAY_ID_QUERY)) {
                preparedStatementS.setInt(1, registrationId);
                ResultSet queryResultFirstName = preparedStatementS.executeQuery();

                if (queryResultFirstName.next()) {
                    // Get the stay_id value
                    String stayId = queryResultFirstName.getString("stay_id");

                    // Delete from registration table
                    String deleteREGI = "DELETE FROM registration WHERE registration_id = ?";
                    try (PreparedStatement preparedStatement2 = connectDB.prepareStatement(deleteREGI)) {
                        preparedStatement2.setInt(1, registrationId);
                        int rowsAffected = preparedStatement2.executeUpdate();

                        if (rowsAffected > 0) {
                            System.out.println("Row with ID " + stayId + " deleted from stay table successfully.");
                        } else {
                            System.out.println("No rows were deleted from stay table. Check if the ID exists in the table.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    // Delete from stay table
                    String deleteSTAY_ID = "DELETE FROM stay WHERE id_stay = ?";
                    try (PreparedStatement preparedStatement = connectDB.prepareStatement(deleteSTAY_ID)) {
                        preparedStatement.setInt(1, Integer.parseInt(stayId));
                        int rowsAffected = preparedStatement.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Row with ID " + EnterID.getText() + " deleted from registration table successfully.");
                        } else {
                            System.out.println("No rows were deleted from registration table. Check if the ID exists in the table.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Registration ID not found.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

