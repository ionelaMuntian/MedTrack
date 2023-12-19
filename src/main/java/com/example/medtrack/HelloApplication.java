package com.example.medtrack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root =FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root,753,567));
        primaryStage.show();
    }
    public void start2(Stage secondaryStage) throws Exception {

        Parent root =FXMLLoader.load(getClass().getResource("type.fxml"));
        secondaryStage.initStyle(StageStyle.UNDECORATED);
        secondaryStage.setScene(new Scene(root,753,567));
        secondaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}