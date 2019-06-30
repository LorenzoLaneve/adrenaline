package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerConvertMarksEventTest {

    PlayerConvertMarksEvent e;

    @Before
    public void setUp() throws Exception {
        e = new PlayerConvertMarksEvent(PlayerColor.YELLOW, PlayerColor.CYAN);
    }

    @Test
    public void testGetDamagedPlayer() throws Exception {
        assertEquals(PlayerColor.YELLOW, e.getDamagedPlayer());
    }

    @Test
    public void testGetDealer() throws Exception {
        assertEquals(PlayerColor.CYAN, e.getDealer());
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.publish(new MockConnectionSender());
    }
}