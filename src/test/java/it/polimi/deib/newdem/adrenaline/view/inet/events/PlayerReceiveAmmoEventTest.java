package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerReceiveAmmoEventTest {

    PlayerReceiveAmmoEvent e;

    @Before
    public void setUp() throws Exception {
        e = new PlayerReceiveAmmoEvent(PlayerColor.GREEN, 1,2,3);
    }

    @Test
    public void testGetPlayer() throws Exception {
        assertEquals(PlayerColor.GREEN, e.getPlayer());
    }

    @Test
    public void testGetBlueAmount() throws Exception {
        assertEquals(3, e.getBlueAmount());
    }

    @Test
    public void testGetRedAmount() throws Exception {
        assertEquals(2, e.getRedAmount());
    }

    @Test
    public void testGetYellowAmount() throws Exception {
        assertEquals(1, e.getYellowAmount());
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.notifyEvent(new MockConnectionSender(), new MockConnectionReceiver());
    }
}