package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovePlayerEventTest {

    MovePlayerEvent e;
    PlayerColor c;
    TilePosition p = new TilePosition(0,0);

    @Before
    public void setUp() throws Exception {
        e = new MovePlayerEvent(c,p);
    }

    @Test
    public void testGetPlayerColor() throws Exception {
        assertEquals(c,e.getPlayerColor());
    }

    @Test
    public void testGetDestination() throws Exception {
        assertEquals(p, e.getDestination());
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.publish(new MockConnectionSender());
    }
}