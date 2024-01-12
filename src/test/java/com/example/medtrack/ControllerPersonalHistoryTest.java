package com.example.medtrack;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class ControllerPersonalHistoryTest {
    @Test
    void display_correct_info() {
     ControllerPersonalHistory c = new ControllerPersonalHistory();
        String first_name_test="";
        String last_name_test="";
        String place_label_test="";
        String nationaliy_test="";
        String date_test="";
        String address_test="";
        c.initialize_test("6040303350038",first_name_test, last_name_test,  place_label_test,  nationaliy_test,   date_test,  address_test);
        assertEquals("Mariana-Ionela",c.initialize_test("6040303350038",first_name_test, last_name_test,  place_label_test,  nationaliy_test,   date_test,  address_test));
    }
}