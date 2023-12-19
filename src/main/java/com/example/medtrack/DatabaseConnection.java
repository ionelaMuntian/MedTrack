package com.example.medtrack;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "lab10";
        String databaseUser = "postgres";
        String databasePassword = "MiculPrint2004!";
        String url = "jdbc:postgresql://localhost:5432/lab10";

        try {
            Class.forName("org.postgresql.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return databaseLink;
    }
}