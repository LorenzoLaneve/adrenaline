package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerDisconnectEventTest {

    PlayerDisconnectEvent e;

    @Before
    public void setUp() throws Exception {
        e = new PlayerDisconnectEvent(PlayerColor.GREEN);
    }

    @Test
    public void testGetPlayerColor() throws Exception {
        assertEquals(PlayerColor.GREEN, e.getPlayerColor());
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.publish(new MockConnectionSender());
    }
}