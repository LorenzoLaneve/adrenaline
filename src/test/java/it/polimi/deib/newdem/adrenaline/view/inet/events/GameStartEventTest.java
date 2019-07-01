package it.polimi.deib.newdem.adrenaline.view.inet.events;

import org.junit.Before;
import org.junit.Test;

public class GameStartEventTest {

    GameStartEvent e;

    @Before
    public void setUp() throws Exception {
        e = new GameStartEvent();
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.publish(new MockConnectionSender());
    }
}