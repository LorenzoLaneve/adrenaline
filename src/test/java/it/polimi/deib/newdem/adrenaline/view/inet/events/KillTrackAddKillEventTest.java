package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import org.junit.Before;
import org.junit.Test;

import java.time.Year;

import static org.junit.Assert.*;

public class KillTrackAddKillEventTest {

    KillTrackAddKillEvent e;

    @Before
    public void setUp() throws Exception {
        e = new KillTrackAddKillEvent(PlayerColor.YELLOW, 1);
    }

    @Test
    public void testGetColor() throws Exception {
        assertEquals(PlayerColor.YELLOW, e.getColor());
    }

    @Test
    public void testGetAmount() throws Exception {
        assertEquals(1, e.getAmount());
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.publish(new MockConnectionSender());
    }
}