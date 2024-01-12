package com.example.medtrack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HelloController {

    @FXML
    private Button CancelButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    private HelloApplication application;

    // Add a setApplication method
    public void setApplication(HelloApplication application) {
        this.application = application;
    }
    @FXML
    public void LoginButtonOnAction(ActionEvent e) throws IOException {
        if (!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()) {
            validateLogin();
        } else {
            loginMessageLabel.setText("Please enter username and password!");
        }
    }

    @FXML
    public void CancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) CancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        if (connectDB != null) {
            String verifyLogin = "SELECT count(1) FROM login WHERE username = ? AND password = ?";

            try {
                PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLogin);
                preparedStatement.setString(1, usernameTextField.getText());
                preparedStatement.setString(2, passwordPasswordField.getText());

                ResultSet queryResult = preparedStatement.executeQuery();

                while (queryResult.next()) {
                    if (queryResult.getInt(1) == 1) {
                        loginMessageLabel.setText("Welcome!");
                        if (application != null) {
                            Stage stage = (Stage) CancelButton.getScene().getWindow();
                            stage.close();
                            application.start2(); // Call the start2 method from the application
                        } else {
                            System.err.println("Error: HelloApplication instance not set.");
                        }
                    } else {
                        loginMessageLabel.setText("Invalid Login. Please try again!");
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            loginMessageLabel.setText("Failed to connect to the database. Please try again later.");
        }
    }
    public int validateLoginTest(String UName, String Password) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        if (connectDB != null) {
            String verifyLogin = "SELECT count(1) FROM login WHERE username = ? AND password = ?";

            try {
                PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLogin);
                preparedStatement.setString(1, UName);
                preparedStatement.setString(2, Password);

                ResultSet queryResult = preparedStatement.executeQuery();

                while (queryResult.next()) {
                    if (queryResult.getInt(1) == 1) {
                        return 1;
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            loginMessageLabel.setText("Failed to connect to the database. Please try again later.");
        }
        return 0;
    }
}
