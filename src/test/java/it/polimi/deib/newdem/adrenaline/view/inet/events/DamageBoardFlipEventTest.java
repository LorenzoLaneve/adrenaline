package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DamageBoardFlipEventTest {

    DamageBoardFlipEvent e;

    @Before
    public void setUp() throws Exception {
        e = new DamageBoardFlipEvent(PlayerColor.YELLOW);
    }

    @Test
    public void testGetColor() throws Exception {
        assertEquals(e.getColor(), PlayerColor.YELLOW);
    }

    @Test
    public void testNotify() throws Exception {
        e.publish(new MockConnectionSender());
    }
}