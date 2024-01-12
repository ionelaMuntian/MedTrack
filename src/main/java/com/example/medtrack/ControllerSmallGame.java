package com.example.medtrack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerSmallGame {
    @FXML
    private Button Drink_alcohol_regularly;

    @FXML
    private Button Eat_unhealthy;

    @FXML
    private Button GameButton;

    @FXML
    private Button LoginAdminB;

    @FXML
    private Button NoSport;

    @FXML
    private Button SearchPersonalHistoryB;

    @FXML
    private Button Sleep;

    @FXML
    private Button Smoke;

    @FXML
    private Button Stress;
    Integer years = 0;

    @FXML
    void Drink_alcohol_regularly_onAction(ActionEvent event) {
        years = years + 2;
        computeLifeSpan();
    }

    @FXML
    void Eat_unhealthy_onAction(ActionEvent event) {
        years = years + 5;
        computeLifeSpan();
    }

    private HelloApplication application;

    public void setApplication(HelloApplication application) {
        this.application = application;
    }

    @FXML
    void LoginAdminB_onAction(ActionEvent event) throws IOException {
        if (application != null) {
            Stage stage = (Stage) Sleep.getScene().getWindow();
            stage.close();
            application.start_login_admin(); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    @FXML
    void SearchPersonalHistoryB_onAction(ActionEvent event) throws IOException {
        if (application != null) {
            Stage stage = (Stage) Sleep.getScene().getWindow();
            stage.close();
            application.start2(); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    @FXML
    void GameButton_onAction(ActionEvent event) throws IOException {
        if (application != null) {
            Stage stage = (Stage) Sleep.getScene().getWindow();
            stage.close();
            application.start_SmallGame(); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    @FXML
    void NoSport_onAction(ActionEvent event) {
        years = years + 6;
        computeLifeSpan();
    }

    @FXML
    void Sleep_onAction(ActionEvent event) {
        years = years + 12;
        computeLifeSpan();
    }

    @FXML
    void Smoke_onAction(ActionEvent event) {
        years = years + 10;
        computeLifeSpan();
    }

    @FXML
    void Stress_onAction(ActionEvent event) {
        years = years + 5;
        computeLifeSpan();
    }

    @FXML
    private LineChart<?, ?> MyChart;

    public void computeLifeSpan() {
        Integer lifespanExpectancy = 78;
        lifespanExpectancy = lifespanExpectancy - years;
        String lifespanExpectancy_S = String.valueOf(lifespanExpectancy);

        XYChart.Series series = new XYChart.Series();


        series.getData().add(new XYChart.Data("0", 0));
        series.getData().add(new XYChart.Data(lifespanExpectancy_S, lifespanExpectancy));

        MyChart.getData().add(series);
    }

    public int computeLifeSpanTest() {
        Integer lifespanExpectancy = 78;
        lifespanExpectancy = lifespanExpectancy - years;
        return lifespanExpectancy;
    }

    int Sleep_Test() {
        years = years + 12;
        return computeLifeSpanTest();
    }

    int eatUnhealthy() {
        years = years + 5;
        return computeLifeSpanTest();
    }
    int smoke() {
        years = years + 10;
        return computeLifeSpanTest();
    }
    int drink() {
        years = years + 2;
        return computeLifeSpanTest();
    }
    int stress() {
        years = years + 5;
        return computeLifeSpanTest();
    }
    int noSport() {
        years = years + 6;
        return computeLifeSpanTest();
    }
}
