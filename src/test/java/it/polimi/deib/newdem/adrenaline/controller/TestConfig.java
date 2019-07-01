package it.polimi.deib.newdem.adrenaline.controller;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestConfig {

    @Test
    public void jsonReadTestSuccess() {
        Config c;
        try {
            c = Config.fromFile(getClass().getClassLoader().getResource("configtest.json").getFile());
        } catch (InvalidConfigException e) {
            fail();
            return;
        }

        assertFalse(c.isSocketActive());
        assertFalse(c.isRMIActive());

        assertEquals(450, c.getSocketPort());
        assertEquals(356, c.getRMIPort());
        assertEquals("test", c.getRMIIdentifier());

        assertEquals(7, c.getTimerLength());
        assertEquals(3, c.getMinPlayers());
        assertEquals(5, c.getMaxPlayers());

        assertEquals(92, c.getTurnTime());
    }

    @Test
    public void jsonReadTestFailure() {
        Config c;
        try {
            c = Config.fromFile("nonexisting.json");
            fail();
        } catch (InvalidConfigException e) {
            // ok
        }
    }

}
