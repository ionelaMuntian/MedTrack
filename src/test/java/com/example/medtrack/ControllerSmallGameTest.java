package com.example.medtrack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerSmallGameTest {
    @Test
    void theAgeIs66IfNoSleep() {
        ControllerSmallGame c = new ControllerSmallGame();
        //78-12
        assertEquals(66,c.Sleep_Test());
        //-2
        assertEquals(64,c.drink());
        //-5
        assertEquals(59,c.eatUnhealthy());
        //-6
        assertEquals(53,c.noSport());
        //-10
        assertEquals(43,c.smoke());
        //-5
        assertEquals(38,c.stress());
    }
    //incorect results
    @Test
    void RandomExpectedAges(){
        ControllerSmallGame c = new ControllerSmallGame();
        //78-12=66
        assertEquals(64,c.Sleep_Test());
    }

}