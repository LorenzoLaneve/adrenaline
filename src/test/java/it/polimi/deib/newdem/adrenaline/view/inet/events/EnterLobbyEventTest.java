package it.polimi.deib.newdem.adrenaline.view.inet.events;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnterLobbyEventTest {

    EnterLobbyEvent e;

    @Before
    public void setUp() throws Exception {
        e = new EnterLobbyEvent("Larry");
    }

    @Test
    public void testGetUsername() throws Exception {
        assertEquals("Larry", e.getUsername());
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.notifyEvent(new MockConnectionSender(), new MockConnectionReceiver());
    }
}