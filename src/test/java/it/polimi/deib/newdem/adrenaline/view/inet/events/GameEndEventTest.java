package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.GameResults;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameEndEventTest {

    GameEndEvent e;

    GameResults r;

    @Before
    public void setUp() throws Exception {
        r = new GameResults();
        e = new GameEndEvent(r);
    }

    @Test
    public void testGetResults() {
        assertEquals(r, e.getResults());
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.publish(new MockConnectionSender());
    }

}