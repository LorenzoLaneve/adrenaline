package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerDiscardWeaponEventTest {

    PlayerDiscardWeaponEvent e;

    @Before
    public void setUp() throws Exception {
        e = new PlayerDiscardWeaponEvent(PlayerColor.GREEN, 7);
    }

    @Test
    public void testGetPlayer() throws Exception {
        assertEquals(PlayerColor.GREEN, e.getPlayer());
    }

    @Test
    public void testGetCardID() throws Exception {
        assertEquals(7, e.getCardID());
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.publish(new MockConnectionSender());
    }
}