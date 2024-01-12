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

public class Controller2 {
    @FXML
    private Button LoginButton2;

    @FXML
    private Button SearchPersonalHistoryB;
    @FXML
    private Button LoginAdminB;

    @FXML
    private Label loginMessageLabel2;

    @FXML
    private TextField FirstName;

    @FXML
    private TextField LastName;
    @FXML
    private Button GameButton;

    @FXML
    private TextField CNPTextField;
    private HelloApplication application;

    public void setApplication(HelloApplication application) {
        this.application = application;
    }

    public void SearchPersonalHistoryB_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) LoginButton2.getScene().getWindow();
            stage.close();
            application.start2(); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    public void LoginAdminB_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) LoginButton2.getScene().getWindow();
            stage.close();
            application.start_login_admin(); // Call the start2 method from the application

        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }
    public void GameButton_onAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) LoginButton2.getScene().getWindow();
            stage.close();
            application.start_SmallGame();
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }
    public void LoginButtonOnAction2(ActionEvent e) {
        if (!FirstName.getText().isBlank() && !LastName.getText().isBlank() && !CNPTextField.getText().isBlank()) {
            validateLogin2();
        } else {
            loginMessageLabel2.setText("Please enter both your name and CNP!");
        }
    }

    @FXML
    public void validateLogin2() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        if (connectDB != null) {
            String verifyLogin = "SELECT cnp FROM person WHERE last_name = ? AND first_name = ?";

            try {
                PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLogin);
                preparedStatement.setString(1, LastName.getText());
                preparedStatement.setString(2, FirstName.getText());

                ResultSet queryResult = preparedStatement.executeQuery();

                if (queryResult.next()) {
                    String retrievedCNP = queryResult.getString("cnp");
                    if (retrievedCNP.equals(CNPTextField.getText())) {
                        loginMessageLabel2.setText("Welcome!");
                        if (application != null) {
                            // Close the current stage
                            Stage stage = (Stage) LoginButton2.getScene().getWindow();
                            stage.close();
                            application.startPersonalHistory(retrievedCNP); // Pass the CNP value
                        } else {
                            System.err.println("Error: HelloApplication instance not set.");
                        }
                    } else {
                        loginMessageLabel2.setText("Incorrect CNP for this person!");
                    }
                } else {
                    loginMessageLabel2.setText("Person not found!");
                }
            } catch (SQLException | NumberFormatException ex) {
                ex.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            loginMessageLabel2.setText("Failed to connect to the database. Please try again later.");
        }
    }

    public String validateLogin3(String L_name, String F_name) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        if (connectDB != null) {
            String verifyLogin = "SELECT cnp FROM person WHERE last_name = ? AND first_name = ?";

            try {
                PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLogin);
                preparedStatement.setString(1, L_name);
                preparedStatement.setString(2, F_name);

                ResultSet queryResult = preparedStatement.executeQuery();

                if (queryResult.next()) {
                    String retrievedCNP = queryResult.getString("cnp");
                    return retrievedCNP;
                } else {
                    loginMessageLabel2.setText("Person not found!");
                }
            } catch (SQLException | NumberFormatException ex) {
                ex.printStackTrace();
            }
        } else {
            loginMessageLabel2.setText("Failed to connect to the database. Please try again later.");
        }
        return "0";
    }
}
