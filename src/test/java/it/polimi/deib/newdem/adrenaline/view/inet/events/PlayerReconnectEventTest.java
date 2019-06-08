package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerReconnectEventTest {

    PlayerReconnectEvent e;

    @Before
    public void setUp() throws Exception {
        e = new PlayerReconnectEvent(PlayerColor.GREEN);
    }

    @Test
    public void testGetPlayerColor() throws Exception {
        assertEquals(PlayerColor.GREEN, e.getPlayerColor());
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.notifyEvent(new MockConnectionSender(), new MockConnectionReceiver());
    }
}