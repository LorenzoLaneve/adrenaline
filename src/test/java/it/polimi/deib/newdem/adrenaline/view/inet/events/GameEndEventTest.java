package it.polimi.deib.newdem.adrenaline.view.inet.events;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameEndEventTest {

    GameEndEvent e;

    @Before
    public void setUp() throws Exception {
        e = new GameEndEvent();
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.publish(new MockConnectionSender());
    }
}