package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerDiscardPowerUpEventTest {

    PlayerDiscardPowerUpEvent e;

    @Before
    public void setUp() throws Exception {
        e = new PlayerDiscardPowerUpEvent(PlayerColor.GREEN, 7);
    }

    @Test
    public void testGetCardID() throws Exception {
        assertEquals(7, e.getCardID());
    }

    @Test
    public void testGetColor() throws Exception {
        assertEquals(PlayerColor.GREEN, e.getColor());
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.publish(new MockConnectionSender());
    }
}