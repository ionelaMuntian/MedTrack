package com.example.medtrack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.engine.JupiterTestEngine;

class Controller2Test {
    @Test
    void CNP_MatchesName() {
        Controller2 person = new Controller2();
        String L_name = "Muntian";
        String F_name = "Mariana-Ionela";

        assertEquals("6040303350038",person.validateLogin3(L_name,F_name));
    }
    @Test
    void CNP_DOES_NOT_MatchesName() {
        Controller2 person = new Controller2();
        String L_name = "Muntian";
        String F_name = "Mariana-Ionela";

        assertEquals("5040303350038",person.validateLogin3(L_name,F_name));
    }
    @Test
    void PersonDoesNotExist() {
        Controller2 person = new Controller2();
        String L_name = "Muntian";
        String F_name = "Armina";

        assertEquals("6040303350038",person.validateLogin3(L_name,F_name));
    }

}