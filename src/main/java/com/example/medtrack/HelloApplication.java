package com.example.medtrack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        openLoginStage(primaryStage);
    }

    public void start2() throws IOException {
        Stage secondaryStage = new Stage();
        openTypeStage(secondaryStage);
    }

    public void startPersonalHistory(String cnpValue) throws IOException {
        Stage startPersonalStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("personal_history.fxml"));
        loader.setControllerFactory(controllerClass -> {
            ControllerPersonalHistory controller = new ControllerPersonalHistory();
            controller.setApplication(HelloApplication.this);
            controller.setCNPValue(cnpValue);
            return controller;
        });

        Parent root = loader.load();

        startPersonalStage.initStyle(StageStyle.UNDECORATED);
        startPersonalStage.setScene(new Scene(root, 753, 567));
        startPersonalStage.show();
    }

    public void start_login_admin() throws IOException {
        Stage startLoginAdmin = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("type2.fxml"));
        loader.setControllerFactory(controllerClass -> {
            ControllerLoginAdmin controller = new ControllerLoginAdmin();
            controller.setApplication(HelloApplication.this);
            return controller;
        });
        Parent root = loader.load();

        startLoginAdmin.initStyle(StageStyle.UNDECORATED);
        startLoginAdmin.setScene(new Scene(root, 753, 567));
        startLoginAdmin.show();
    }

    public void start_HospitalDataBase(int HospitalID) throws IOException {
        Stage startHospitalInfo = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hospital_info.fxml"));
        loader.setControllerFactory(controllerClass -> {
            ControllerHospital_Info controller = new ControllerHospital_Info();
            controller.setApplication(HelloApplication.this);
            controller.setHospitalID(HospitalID);
            return controller;
        });
        Parent root = loader.load();

        startHospitalInfo.initStyle(StageStyle.UNDECORATED);
        startHospitalInfo.setScene(new Scene(root, 753, 567));
        startHospitalInfo.show();
    }

    public void start_employees(int HospitalID) throws IOException {
        Stage stageEmployees = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("employees_login.fxml"));
        loader.setControllerFactory(controllerClass -> {
            ControllerEmployeesLogin controller = new ControllerEmployeesLogin();
            controller.setApplication(HelloApplication.this);
            controller.setHospitalID(HospitalID);
            return controller;
        });
        Parent root = loader.load();

        stageEmployees.initStyle(StageStyle.UNDECORATED);
        stageEmployees.setScene(new Scene(root, 753, 567));
        stageEmployees.show();
    }
    public void start_hospitalized_patients(int HospitalID) throws IOException {
        Stage stageHospitalized_patients = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hospitalized_patients.fxml"));
        loader.setControllerFactory(controllerClass -> {
            ControllerHospitalized controller = new  ControllerHospitalized();
            controller.setApplication(HelloApplication.this);
            controller.setHospitalID(HospitalID);
            return controller;
        });
        Parent root = loader.load();

        stageHospitalized_patients.initStyle(StageStyle.UNDECORATED);
        stageHospitalized_patients.setScene(new Scene(root, 753, 567));
        stageHospitalized_patients.show();
    }
    public void start_death_statistics(int HospitalID) throws IOException {
        Stage stageDeath_statistics = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("death_statistics.fxml"));
        loader.setControllerFactory(controllerClass -> {
            ControllerDeathStatistics controller = new  ControllerDeathStatistics();
            controller.setApplication(HelloApplication.this);
            controller.setHospitalID(HospitalID);
            return controller;
        });
        Parent root = loader.load();

        stageDeath_statistics.initStyle(StageStyle.UNDECORATED);
        stageDeath_statistics.setScene(new Scene(root, 753, 567));
        stageDeath_statistics.show();
    }

    public void start_departments(int HospitalID) throws IOException {
        Stage stageDepartments = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("departments.fxml"));
        loader.setControllerFactory(controllerClass -> {
            ControllerDepartments controller= new  ControllerDepartments();
            controller.setApplication(HelloApplication.this);
            controller.setHospitalID(HospitalID);
            return controller;
        });
        Parent root = loader.load();

        stageDepartments.initStyle(StageStyle.UNDECORATED);
        stageDepartments.setScene(new Scene(root, 753, 567));
        stageDepartments.show();
    }
    public void start_rooms(int HospitalID) throws IOException {
        Stage stageRooms = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("rooms.fxml"));
        loader.setControllerFactory(controllerClass -> {
            ControllerRooms controller= new  ControllerRooms();
            controller.setApplication(HelloApplication.this);
            controller.setHospitalID(HospitalID);
            return controller;
        });
        Parent root = loader.load();

        stageRooms.initStyle(StageStyle.UNDECORATED);
        stageRooms.setScene(new Scene(root, 753, 567));
        stageRooms.show();
    }
    public void start_room_details(int HospitalID, String RoomName) throws IOException {
        Stage stageRoomsDetails = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("room_details.fxml"));
        loader.setControllerFactory(controllerClass -> {
            ControllerRoomDetails controller= new ControllerRoomDetails();
            controller.setApplication(HelloApplication.this);
            controller.setHospitalID(HospitalID);
            controller.setRoomName(RoomName);
            return controller;
        });
        Parent root = loader.load();

        stageRoomsDetails.initStyle(StageStyle.UNDECORATED);
        stageRoomsDetails.setScene(new Scene(root, 753, 567));
        stageRoomsDetails.show();
    }
    public void start_registerPatient(int HospitalID) throws IOException {
        Stage stageRegisterPatient = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("register_patient.fxml"));
        loader.setControllerFactory(controllerClass -> {
            ControllerRegisterPatient controller= new  ControllerRegisterPatient();
            controller.setApplication(HelloApplication.this);
            controller.setHospitalID(HospitalID);
            return controller;
        });
        Parent root = loader.load();

        stageRegisterPatient.initStyle(StageStyle.UNDECORATED);
        stageRegisterPatient.setScene(new Scene(root, 753, 567));
        stageRegisterPatient.show();
    }
    public void start_registerNewBorn(int HospitalID) throws IOException {
        Stage stageRegisterNewBorn = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newborn.fxml"));
        loader.setControllerFactory(controllerClass -> {
            ControllerNewborn controller= new  ControllerNewborn();
            controller.setApplication(HelloApplication.this);
            controller.setHospitalID(HospitalID);
            return controller;
        });
        Parent root = loader.load();

        stageRegisterNewBorn.initStyle(StageStyle.UNDECORATED);
        stageRegisterNewBorn.setScene(new Scene(root, 753, 567));
        stageRegisterNewBorn.show();
    }
    public void start_SmallGame() throws IOException {
        Stage stageSmallGame = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SmallGame.fxml"));
        loader.setControllerFactory(controllerClass -> {
            ControllerSmallGame controller = new  ControllerSmallGame();
            controller.setApplication(HelloApplication.this);
            return controller;
        });
        Parent root = loader.load();

        stageSmallGame.initStyle(StageStyle.UNDECORATED);
        stageSmallGame.setScene(new Scene(root, 753, 567));
        stageSmallGame.show();
    }
    public void start_deleteRegistration(int HospitalID)  throws IOException {
        Stage stageD = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteRegistration.fxml"));
        loader.setControllerFactory(controllerClass -> {
            ControllerDelete controller = new  ControllerDelete();
            controller.setApplication(HelloApplication.this);
            return controller;
        });
        Parent root = loader.load();

        stageD.initStyle(StageStyle.UNDECORATED);
        stageD.setScene(new Scene(root, 753, 567));
        stageD.show();
    }
    private void openLoginStage(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        HelloController controller = loader.getController();
        controller.setApplication(this); // Set the application reference in the controller

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root, 753, 567));
        stage.show();
    }

    private void openTypeStage(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("type.fxml"));
        Parent root = loader.load();

        Controller2 controller2 = loader.getController();
        controller2.setApplication(this); // Set the application reference in Controller2

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root, 753, 567));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}