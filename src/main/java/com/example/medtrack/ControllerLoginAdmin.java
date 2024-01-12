package com.example.medtrack;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ControllerLoginAdmin {
    @FXML
    private Button SearchPersonalHistoryB;
    @FXML
    private Button LoginAdminB;
    @FXML
    private Button GameButton;
    @FXML
    private Button LoginButton3;
    @FXML
    private Label loginMessageLabel3;
    @FXML
    private TextField HospitalID;
    @FXML
    private TextField AdminID;
    private HelloApplication application;
    public void setApplication(HelloApplication application) {
        this.application = application;
    }
    public void LoginButtonOnAction3(ActionEvent actionEvent) throws IOException {
        if (!HospitalID.getText().isBlank() && !AdminID.getText().isBlank()) {
            validateLogin();
        } else {
            loginMessageLabel3.setText("Please enter both the admin ID and the hospital ID!");
        }
    }
    public void SearchPersonalHistoryB_onAction(ActionEvent actionEvent) throws IOException {
        if (application != null) {
            Stage stage = (Stage) LoginButton3.getScene().getWindow();
            stage.close();
            application.start2(); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }
    public void LoginAdminB_onAction(ActionEvent actionEvent) throws IOException {
        if (application != null) {
            Stage stage = (Stage) LoginButton3.getScene().getWindow();
            stage.close();
            application.start_login_admin(); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }
    public void GameButton_onAction(ActionEvent actionEvent) throws IOException {
        if (application != null) {
            Stage stage = (Stage) LoginButton3.getScene().getWindow();
            stage.close();
            application.start_SmallGame(); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }
    public void validateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        if (connectDB != null) {
            String verifyLogin = "SELECT id_admin FROM hopital2 WHERE id_hospital = ? ";

            try {
                PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLogin);

                // Parse HospitalID.getText() to an integer
                int hospitalId = Integer.parseInt(HospitalID.getText());
                preparedStatement.setInt(1, hospitalId);

                ResultSet queryResult = preparedStatement.executeQuery();

                if (queryResult.next()) {

                    String retrievedID_ADMIN = queryResult.getString("id_admin");
                    if (retrievedID_ADMIN.equals(AdminID.getText())) {
                        loginMessageLabel3.setText("Welcome!");
                        if (application != null) {
                            // Close the current stage
                            Stage stage = (Stage) LoginButton3.getScene().getWindow();
                            stage.close();
                            application.start_HospitalDataBase(hospitalId); // Pass the value
                        } else {
                            System.err.println("Error: HelloApplication instance not set.");
                        }
                    } else {
                        loginMessageLabel3.setText("Incorrect ID_ADMIN for this ID_HOSPITAL!");
                    }
                } else {
                    loginMessageLabel3.setText("This Hospital does not exist!");
                }
            } catch (SQLException | NumberFormatException ex) {
                ex.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            loginMessageLabel3.setText("Failed to connect to the database. Please try again later.");
        }
    }
}
