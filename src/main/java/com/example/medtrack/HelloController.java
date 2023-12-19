package com.example.medtrack;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {
    @FXML
    private Button CancelButton;

    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;

    public void LoginButtonOnAction(ActionEvent e) {
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
                    } else {
                        loginMessageLabel.setText("Invalid Login. Please try again!");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            loginMessageLabel.setText("Failed to connect to the database. Please try again later.");
        }
    }
}
