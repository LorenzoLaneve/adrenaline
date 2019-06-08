package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import org.junit.Before;
import org.junit.Test;

import java.time.Year;

import static it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor.YELLOW;
import static org.junit.Assert.*;

public class PlayerAcquireWeaponEventTest {

    PlayerAcquireWeaponEvent e;

    @Before
    public void setUp() throws Exception {
        e = new PlayerAcquireWeaponEvent(YELLOW, 7);
    }

    @Test
    public void testGetPlayerColor() throws Exception {
        assertEquals(YELLOW, e.getPlayerColor());
    }

    @Test
    public void testGetWeaponCardID() throws Exception {
        assertEquals(e.getWeaponCardID(), 7);
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.notifyEvent(new MockConnectionSender(), new MockConnectionReceiver());
    }
}