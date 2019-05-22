package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static it.polimi.deib.newdem.adrenaline.model.game.PlayerColor.GREEN;
import static it.polimi.deib.newdem.adrenaline.model.game.PlayerColor.YELLOW;
import static org.junit.Assert.*;

public class GameParametersTest {

    @Test(expected = IllegalStateException.class)
    public void testIsColorPlayerMapSet() throws Exception {
        GameParameters gp = new GameParameters();
        gp.isColorUserOrderSet();
        gp.getColorUserOrder();
    }

    @Test
    public void testSetKillTrackInitialLength() throws Exception {
        GameParameters gp = new GameParameters();
        gp.setKillTrackInitialLength(6);
        assertEquals(6, gp.getKillTrackInitialLength());
    }

    @Test
    public void testGetPlayerOrder() throws Exception {
        GameParameters gp = new GameParameters();
        gp.setColorUserOrder(Arrays.asList(
                new ColorUserPair(PlayerColor.MAGENTA, new User()),
                new ColorUserPair(PlayerColor.GREEN, new User())
        ));

        assertTrue(gp.getPlayerOrder().contains(PlayerColor.MAGENTA));
        assertTrue(gp.getPlayerOrder().contains(PlayerColor.GREEN));
        assertFalse(gp.getPlayerOrder().contains(YELLOW));
    }

    @Test
    public void testSetPlayerOrder() throws Exception {
        GameParameters gp = new GameParameters();
        gp.setPlayerOrder(Arrays.asList(
                PlayerColor.MAGENTA,
                PlayerColor.GRAY,
                GREEN
        ));

        assertTrue(gp.getPlayerOrder().contains(PlayerColor.MAGENTA));
        assertTrue(gp.getPlayerOrder().contains(PlayerColor.GREEN));
        assertFalse(gp.getPlayerOrder().contains(YELLOW));

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

    @Test(expected = IllegalStateException.class)
    public void testGetPlayerOrderNegativeEmptyMap() throws Exception {
        GameParameters gp = new GameParameters();
        gp.getPlayerOrder();
    }
}