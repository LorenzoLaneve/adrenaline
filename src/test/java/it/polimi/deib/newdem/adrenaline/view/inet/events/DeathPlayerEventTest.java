package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeathPlayerEventTest {

    DeathPlayerEvent e;

    @Before
    public void setUp() throws Exception {
        e = new DeathPlayerEvent(PlayerColor.YELLOW);
    }

    @Test
    public void testGetPlayerColor() throws Exception {
        assertEquals(PlayerColor.YELLOW, e.getPlayerColor());
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.publish(new MockConnectionSender());
    }
}