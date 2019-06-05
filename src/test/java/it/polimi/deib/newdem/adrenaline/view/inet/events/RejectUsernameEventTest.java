package it.polimi.deib.newdem.adrenaline.view.inet.events;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RejectUsernameEventTest {

    RejectUsernameEvent e;

    @Before
    public void setUp() throws Exception {
        e = new RejectUsernameEvent("Nope");
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("Nope", e.getName());
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.notifyEvent(new MockConnectionSender(), new MockConnectionReceiver());
    }
}