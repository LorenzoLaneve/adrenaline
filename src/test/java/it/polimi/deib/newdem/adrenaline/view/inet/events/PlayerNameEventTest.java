package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerNameEventTest {

    PlayerNameEvent e;

    @Before
    public void setUp() throws Exception {
        e = new PlayerNameEvent(PlayerColor.GREEN, "Hulk");
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("Hulk", e.getName());
    }

    @Test
    public void testGetColor() throws Exception {
        assertEquals(PlayerColor.GREEN, e.getPlayer());
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.notifyEvent(new MockConnectionSender(), new MockConnectionReceiver());
    }
}