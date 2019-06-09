package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerScoreEventTest {

    PlayerScoreEvent e;

    @Before
    public void setUp() throws Exception {
        e = new PlayerScoreEvent(PlayerColor.GREEN, 7);
    }

    @Test
    public void testGetPlayerColor() throws Exception {
        assertEquals(PlayerColor.GREEN, e.getPlayerColor());
    }

    @Test
    public void testGetNewScore() throws Exception {
        assertEquals(7, e.getNewScore());
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.publish(new MockConnectionSender());
    }
}