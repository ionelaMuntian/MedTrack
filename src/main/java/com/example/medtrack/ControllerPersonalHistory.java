package com.example.medtrack;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class ControllerPersonalHistory {
    @FXML
    private Button Back;
    @FXML
    private TableView<TableType> Table;

    @FXML
    private TableColumn<TableType, String> RegistrationDate;

    @FXML
    private TableColumn<TableType, String> Disease;

    @FXML
    private TableColumn<TableType, String> Hospital;

    @FXML
    private TableColumn<TableType, String> PrimaryDoctor;

    @FXML
    private TableColumn<TableType, String> Treatment;

    @FXML
    private Label first_name_label;
    @FXML
    private Label last_name_label;
    @FXML
    private Label cnp_label;
    @FXML
    private Label birth_date_label;
    @FXML
    private Label place_label;
    @FXML
    private Label nationality_label;
    @FXML
    private Label address_label;
    private HelloApplication application;
    ObservableList<TableType> list = FXCollections.observableArrayList();

    public void setApplication(HelloApplication application) {
        this.application = application;
    }

    private static String cnpValue;  // Make it static

    public static void setCNPValue(String cnpValue) {
        ControllerPersonalHistory.cnpValue = cnpValue;  // Use the class name to refer to the static variable
        System.out.println("CNP Value: " + ControllerPersonalHistory.cnpValue);
    }

    @FXML
    public void BackButtonOnAction(ActionEvent e) throws IOException {
        if (application != null) {
            Stage stage = (Stage) Back.getScene().getWindow();
            stage.close();
            application.start2(); // Call the start2 method from the application
        } else {
            System.err.println("Error: HelloApplication instance not set.");
        }
    }

    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();

    @FXML
    public void initialize() {

        if (connectDB != null) {
            String FIRST_NAME = "SELECT first_name FROM person WHERE cnp = ?";
            String LAST_NAME = "SELECT last_name FROM person WHERE cnp = ?";
            String BIRTH_PLACE = "SELECT place_of_birth FROM person WHERE cnp = ?";
            String NATIONALITY = "SELECT nationality FROM person WHERE cnp = ?";
            String BIRTH_DATE = "SELECT birth_date FROM person WHERE cnp = ?";
            String ADDRESS = "SELECT address FROM person WHERE cnp = ?";

            cnp_label.setText(ControllerPersonalHistory.cnpValue);
            try {
                // Retrieve first name
                PreparedStatement preparedStatementFirstName = connectDB.prepareStatement(FIRST_NAME);
                preparedStatementFirstName.setString(1, ControllerPersonalHistory.cnpValue);
                ResultSet queryResultFirstName = preparedStatementFirstName.executeQuery();
                if (queryResultFirstName.next()) {
                    first_name_label.setText(queryResultFirstName.getString("first_name"));
                } else {
                    System.out.println("not found");
                }

                // Retrieve last name
                PreparedStatement preparedStatementLastName = connectDB.prepareStatement(LAST_NAME);
                preparedStatementLastName.setString(1, cnpValue);
                ResultSet queryResultLastName = preparedStatementLastName.executeQuery();
                if (queryResultLastName.next()) {
                    last_name_label.setText(queryResultLastName.getString("last_name"));
                }

                // Retrieve birthplace
                PreparedStatement preparedStatementBirthPlace = connectDB.prepareStatement(BIRTH_PLACE);
                preparedStatementBirthPlace.setString(1, cnpValue);
                ResultSet queryResultBirthPlace = preparedStatementBirthPlace.executeQuery();
                if (queryResultBirthPlace.next()) {
                    place_label.setText(queryResultBirthPlace.getString("place_of_birth"));
                }

                // Retrieve nationality
                PreparedStatement preparedStatementNationality = connectDB.prepareStatement(NATIONALITY);
                preparedStatementNationality.setString(1, cnpValue);
                ResultSet queryResultNationality = preparedStatementNationality.executeQuery();
                if (queryResultNationality.next()) {
                    nationality_label.setText(queryResultNationality.getString("nationality"));
                }

                // Retrieve birthdate
                PreparedStatement preparedStatementBirthDate = connectDB.prepareStatement(BIRTH_DATE);
                preparedStatementBirthDate.setString(1, cnpValue);
                ResultSet queryResultBirthDate = preparedStatementBirthDate.executeQuery();
                if (queryResultBirthDate.next()) {
                    Date birthDate = queryResultBirthDate.getDate("birth_date");

                    // Format the Date as needed (e.g., using SimpleDateFormat)
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedBirthDate = dateFormat.format(birthDate);

                    birth_date_label.setText(formattedBirthDate);
                }
                // Retrieve birthdate
                PreparedStatement preparedStatementAddress = connectDB.prepareStatement(ADDRESS);
                preparedStatementAddress.setString(1, cnpValue);
                ResultSet queryResultAddress = preparedStatementAddress.executeQuery();
                if (queryResultAddress.next()) {
                    address_label.setText(queryResultAddress.getString("address"));
                }

                //FILL THE TABLE
                RegistrationDate.setCellValueFactory(new PropertyValueFactory<>("RegistrationDate"));
                Disease.setCellValueFactory(new PropertyValueFactory<>("Disease"));
                Hospital.setCellValueFactory(new PropertyValueFactory<>("Hospital"));
                PrimaryDoctor.setCellValueFactory(new PropertyValueFactory<>("PrimaryDoctor"));
                Treatment.setCellValueFactory(new PropertyValueFactory<>("Treatment"));

                try {
                    // Retrieve registration data
                    String registrationQuery = " select date, d.name , h.name as Hospital ,(select p2.last_name  || ' ' || p2.first_name as primary_doctor\n" +
                            "                                             from person p2 join staff s2 on (p2.cnp = s2.cnp_staff)\n" +
                            "                                             where s2.id_staff  = r.id_primary_doctor) ,d.treatment \n" +
                            "  from registration r join disease d on(r.id_disease = d.id_disease) \n" +
                            "                      join person p on(r.registr_cnp = p.cnp)\n" +
                            "                      join staff s on (r.id_primary_doctor=s.id_staff)\n" +
                            "                      join hopital2 h on (r.id_hospital=h.id_hospital)\n" +
                            " where p.cnp= ?";

                    PreparedStatement preparedStatementRegistration = connectDB.prepareStatement(registrationQuery);
                    preparedStatementRegistration.setString(1, ControllerPersonalHistory.cnpValue);
                    ResultSet queryResultRegistration = preparedStatementRegistration.executeQuery();


                    // Populate the list with registration data
                    while (queryResultRegistration.next()) {

                        Date date = queryResultRegistration.getDate("date");
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String formattedDate = dateFormat.format(date);

                        String disease = queryResultRegistration.getString("name");
                        String hospital = queryResultRegistration.getString("Hospital");
                        String primaryDoctor = queryResultRegistration.getString("primary_doctor");
                        String treatment = queryResultRegistration.getString("treatment");

                        list.add(new TableType(formattedDate, disease, hospital, primaryDoctor, treatment));
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

    public String initialize_test(String cnp_test, String first_name_test,String last_name_test, String place_label_test, String nationaliy_test,String date_test, String address_test) {

        if (connectDB != null) {
            String FIRST_NAME = "SELECT first_name FROM person WHERE cnp = ?";
            String LAST_NAME = "SELECT last_name FROM person WHERE cnp = ?";
            String BIRTH_PLACE = "SELECT place_of_birth FROM person WHERE cnp = ?";
            String NATIONALITY = "SELECT nationality FROM person WHERE cnp = ?";
            String BIRTH_DATE = "SELECT birth_date FROM person WHERE cnp = ?";
            String ADDRESS = "SELECT address FROM person WHERE cnp = ?";

            try {
                // Retrieve first name
                PreparedStatement preparedStatementFirstName = connectDB.prepareStatement(FIRST_NAME);
                preparedStatementFirstName.setString(1, cnp_test);
                ResultSet queryResultFirstName = preparedStatementFirstName.executeQuery();
                if (queryResultFirstName.next()) {
                    first_name_test = queryResultFirstName.getString("first_name");
                } else {
                    System.out.println("not found");
                }

                // Retrieve last name
                PreparedStatement preparedStatementLastName = connectDB.prepareStatement(LAST_NAME);
                preparedStatementLastName.setString(1, cnp_test);
                ResultSet queryResultLastName = preparedStatementLastName.executeQuery();
                if (queryResultLastName.next()) {
                     last_name_test = queryResultLastName.getString("last_name");
                }

                // Retrieve birthplace
                PreparedStatement preparedStatementBirthPlace = connectDB.prepareStatement(BIRTH_PLACE);
                preparedStatementBirthPlace.setString(1, cnp_test);
                ResultSet queryResultBirthPlace = preparedStatementBirthPlace.executeQuery();
                if (queryResultBirthPlace.next()) {
                    place_label_test = queryResultBirthPlace.getString("place_of_birth");
                }

                // Retrieve nationality
                PreparedStatement preparedStatementNationality = connectDB.prepareStatement(NATIONALITY);
                preparedStatementNationality.setString(1, cnp_test);
                ResultSet queryResultNationality = preparedStatementNationality.executeQuery();
                if (queryResultNationality.next()) {
                     nationaliy_test = queryResultNationality.getString("nationality");
                }

                // Retrieve birthdate
                PreparedStatement preparedStatementBirthDate = connectDB.prepareStatement(BIRTH_DATE);
                preparedStatementBirthDate.setString(1, cnp_test);
                ResultSet queryResultBirthDate = preparedStatementBirthDate.executeQuery();
                if (queryResultBirthDate.next()) {
                    Date birthDate = queryResultBirthDate.getDate("birth_date");

                    // Format the Date as needed (e.g., using SimpleDateFormat)
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                     date_test = dateFormat.format(birthDate);

                }
                // Retrieve birthdate
                PreparedStatement preparedStatementAddress = connectDB.prepareStatement(ADDRESS);
                preparedStatementAddress.setString(1, cnp_test);
                ResultSet queryResultAddress = preparedStatementAddress.executeQuery();
                if (queryResultAddress.next()) {
                     address_test = queryResultAddress.getString("address");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return first_name_test;
    }
}