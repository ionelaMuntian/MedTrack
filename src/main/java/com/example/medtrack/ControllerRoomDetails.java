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
import java.sql.Date;
import java.text.SimpleDateFormat;

public class ControllerRoomDetails {
    @FXML
    private TableColumn<TableRooms, String> CheckIn;

    @FXML
    private TableColumn<TableRooms,String> CheckOut;

    @FXML
    private TableColumn<TableRooms, String> Disease;

    @FXML
    private Label E_Position3;

    @FXML
    private TableColumn<TableRooms, String> Name;

    @FXML
    private Label NrSeats;

    @FXML
    private TableView<TableRooms> Table;

    @FXML
    private Label Title;

    @FXML
    private Label building;

    @FXML
    private Label floor;
    ObservableList<TableRooms> list = FXCollections.observableArrayList();
    private HelloApplication application;
    public void setApplication(HelloApplication application) {
        this.application = application;
    }

    private static int HospitalID;  // Make it static

    public static void setHospitalID(int HospitalID) {
        ControllerRoomDetails.HospitalID = HospitalID;
    }
    private static String RoomName;  // Make it static

    public static void setRoomName(String RoomName) {
        ControllerRoomDetails.RoomName = RoomName;
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
            application.start_registerNewBorn(HospitalID);// Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }
    @FXML
    public void initialize() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        if (connectDB != null) {
            String seats = "select r.nr_of_seats from room r where r.room_name = ?";
            String FLOOR = "select r.floor from room r where r.room_name = ?";
            String BUILDING = "select r.building from room r where r.room_name = ?";

            Title.setText(ControllerRoomDetails.RoomName);
            try {
                // Retrieve first name
                PreparedStatement preparedStatementFirstName = connectDB.prepareStatement(seats);
                preparedStatementFirstName.setString(1,ControllerRoomDetails.RoomName);
                ResultSet queryResultFirstName = preparedStatementFirstName.executeQuery();
                if (queryResultFirstName.next()) {
                    NrSeats.setText(queryResultFirstName.getString("nr_of_seats"));
                } else {
                    System.out.println("not found");
                }

                // Retrieve last name
                PreparedStatement preparedStatementLastName = connectDB.prepareStatement(FLOOR);
                preparedStatementLastName.setString(1,ControllerRoomDetails.RoomName);
                ResultSet queryResultLastName = preparedStatementLastName.executeQuery();
                if (queryResultLastName.next()) {
                    floor.setText(queryResultLastName.getString("floor"));
                }

                // Retrieve birthplace
                PreparedStatement preparedStatementBirthPlace = connectDB.prepareStatement(BUILDING);
                preparedStatementBirthPlace.setString(1,ControllerRoomDetails.RoomName);
                ResultSet queryResultBirthPlace = preparedStatementBirthPlace.executeQuery();
                if (queryResultBirthPlace.next()) {
                    building.setText(queryResultBirthPlace.getString("building"));
                }

                //FILL THE TABLE
                Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
                Disease.setCellValueFactory(new PropertyValueFactory<>("Disease"));
                CheckIn.setCellValueFactory(new PropertyValueFactory<>("CheckIn"));
                CheckOut.setCellValueFactory(new PropertyValueFactory<>("CheckOut"));

                try {
                    // Retrieve registration data
                    String registrationQuery = "\n" +
                            "select p.last_name  || ' ' || p.first_name as name, d.\"name\" as disease_name,s.check_in ,s.check_out \n" +
                            "from person p join registration r on (p.cnp = r.registr_cnp )\n" +
                            "              join stay s on (s.id_stay = r.stay_id)\n" +
                            "              join disease d  on (r.id_disease = d.id_disease)\n" +
                            "              join room r2 on (s.id_room = r2.id_room)\n" +
                            " where r2.room_name =? and r.id_hospital = ? and s.check_in <=CURRENT_DATE and s.check_out>=CURRENT_DATE";

                    PreparedStatement preparedStatementRegistration = connectDB.prepareStatement(registrationQuery);
                    preparedStatementRegistration.setString(1,ControllerRoomDetails.RoomName);
                    preparedStatementRegistration.setInt(2,ControllerRoomDetails.HospitalID);
                    ResultSet queryResultRegistration = preparedStatementRegistration.executeQuery();

                    // Populate the list with registration data
                    while (queryResultRegistration.next()) {

                        Date checkIn = queryResultRegistration.getDate("check_in");
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String formattedDate1 = dateFormat.format(checkIn);

                        Date checkOut= queryResultRegistration.getDate("check_out");
                        String formattedDate2 = dateFormat.format(checkOut);

                        String disease = queryResultRegistration.getString("disease_name");
                        String name = queryResultRegistration.getString("name");


                        list.add(new TableRooms(name, disease, formattedDate1, formattedDate2));
                    }
                    Table.setItems(list);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
