package it.polimi.deib.newdem.adrenaline.model.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameParametersTest {

    @Test(expected = IllegalStateException.class)
    public void testIsColorPlayerMapSet() throws Exception {
        GameParameters gp = new GameParameters();
        gp.isColorPlayerMapSet();
        gp.getColorPlayerMap();
    }

    @Test
    public void testSetKillTrackInitialLength() throws Exception {
        GameParameters gp = new GameParameters();
        gp.setKillTrackInitialLength(6);
        assertEquals(6, gp.getKillTrackInitialLength());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetKilltrackInitialLengthNegativeLessMin() throws Exception {
        GameParameters gp = new GameParameters();
        gp.setKillTrackInitialLength(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetKilltrackInitialLengthNegativeMoreMax() throws Exception {
        GameParameters gp = new GameParameters();
        gp.setKillTrackInitialLength(10);
    }
}