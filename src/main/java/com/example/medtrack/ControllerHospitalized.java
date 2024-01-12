package com.example.medtrack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;

public class ControllerHospitalized {
    @FXML
    private TableColumn<TableHospitalized, String> CNP;

    @FXML
    private TableColumn<TableHospitalized, String> CheckIn;

    @FXML
    private TableColumn<TableHospitalized, String> CheckOut;

    @FXML
    private TableColumn<TableHospitalized, String> FirstName;

    @FXML
    private TableColumn<TableHospitalized, String> LastName;

    @FXML
    private TableColumn<TableHospitalized, Integer> ROOM;

    @FXML
    private TableView<TableHospitalized> Table;
    ObservableList<TableHospitalized> hospitalizedList = FXCollections.observableArrayList();
    private HelloApplication application;
    public void setApplication(HelloApplication application) {
        this.application = application;
    }

    private static int HospitalID;  // Make it static

    public static void setHospitalID(int HospitalID) {
        ControllerHospitalized.HospitalID = HospitalID;
    }

    @FXML
    private Button Back;
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

    public void initialize() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        //FILL THE TABLE
        CNP.setCellValueFactory(new PropertyValueFactory<>("cnp"));
        CheckIn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
        CheckOut.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
        FirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        LastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        ROOM.setCellValueFactory(new PropertyValueFactory<>("room"));

        try {
            // Retrieve registration data
            String registrationQuery = "select p.last_name, p.first_name ,p.cnp ,s.check_in ,s.check_out ,r2.room_name \n" +
                    "from stay s join registration r on (s.id_stay = r.stay_id )\n" +
                    "      join person p  on (r.registr_cnp = p.cnp)\n" +
                    "      join room r2 on (s.id_room = r2.id_room)\n" +
                    "where CURRENT_DATE >=s.check_in and CURRENT_DATE <=s.check_out and  r.id_hospital = ?";

            PreparedStatement preparedStatementRegistration = connectDB.prepareStatement(registrationQuery);
            preparedStatementRegistration.setInt(1, HospitalID);
            ResultSet queryResultRegistration = preparedStatementRegistration.executeQuery();

            // Populate the list with registration data
            while (queryResultRegistration.next()) {

                String cnp = queryResultRegistration.getString("cnp");
                Date checkIn = queryResultRegistration.getDate("check_in");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedCheckIn = dateFormat.format(checkIn);

                Date checkOut = queryResultRegistration.getDate("check_out");
                String formattedCheckOut = dateFormat.format(checkOut);

                String firstName = queryResultRegistration.getString("first_name");
                String lastName = queryResultRegistration.getString("last_name");
                Integer room = queryResultRegistration.getInt("room_name");

                hospitalizedList.add(new TableHospitalized(cnp, formattedCheckIn, formattedCheckOut, firstName, lastName, room));
            }
            Table.setItems(hospitalizedList);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}