package it.polimi.deib.newdem.adrenaline.view.inet.events;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExitLobbyEventTest {

    ExitLobbyEvent e;

    @Before
    public void setUp() throws Exception {
        e = new ExitLobbyEvent("Larry");
    }

    @Test
    public void testGetUsername() throws Exception {
        assertEquals(e.getUsername(), "Larry");
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.notifyEvent(new MockConnectionSender(), new MockConnectionReceiver());
    }
}