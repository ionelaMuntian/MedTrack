package com.example.medtrack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloControllerTest {
    @Test
    void DatabaseConnection() {
        HelloController user = new HelloController();
        String UName = "postgres";
        String Password = "MiculPrint2004!";

        assertEquals(1,user.validateLoginTest(UName,Password));
    }
    @Test
    void DatabaseConnection_WrongPassword() {
        HelloController user = new HelloController();
        String UName = "postgres";
        String Password = "MiculPrint!";

        assertEquals(1,user.validateLoginTest(UName,Password));
    }
    @Test
    void DatabaseConnection_WrongUName() {
        HelloController user = new HelloController();
        String UName = "post";
        String Password = "MiculPrint2004!";

        assertEquals(1,user.validateLoginTest(UName,Password));
    }
    @Test
    void DatabaseConnection_EverythingWrong() {
        HelloController user = new HelloController();
        String UName = "post";
        String Password = "MiculPrint!";

        assertEquals(1,user.validateLoginTest(UName,Password));
    }
}