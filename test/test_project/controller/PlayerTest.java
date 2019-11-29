package test_project.controller;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import project.controller.*;
import project.model.*;

public class PlayerTest {
    /**
     * Test to verify if createPlayer function creates a Player object
     */
    @Test
    public void TestCreatePlayer(){
        Player testPlayer = new Player("test", new Hand());
        assertNotNull(testPlayer);
    }

    /**
     * Test to verify if getHand function returns a hand object
     */
    @Test
    public void TestGetHand(){
        Player testPlayer = new Player("test", new Hand());
        Hand testHand = testPlayer.getHand();
        assertNotNull(testHand);
    }

    /**
     * Test to verify if getName function returns a string object with player's name
     */
    @Test
    public void TestGetName(){
        Player testPlayer = new Player("test", new Hand());
        String name = testPlayer.getName();
        assertEquals("test", name);
    }
}
