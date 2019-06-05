package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerDamageEventTest {

    PlayerDamageEvent e;

    @Before
    public void setUp() throws Exception {
        e = new PlayerDamageEvent(PlayerColor.YELLOW, PlayerColor.GREEN, 1,2);
    }

    @Test
    public void testGetDamagedPlayer() throws Exception {
        assertEquals(PlayerColor.YELLOW, e.getDamagedPlayer());
    }

    @Test
    public void testGetAttacker() throws Exception {
        assertEquals(PlayerColor.GREEN, e.getAttacker());
    }

    @Test
    public void testGetDamageAmount() throws Exception {
        assertEquals(1, e.getDamageAmount());
    }

    @Test
    public void testGetMarkAmount() throws Exception {
        assertEquals(2, e.getMarkAmount());
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.notifyEvent(new MockConnectionSender(), new MockConnectionReceiver());
    }
}