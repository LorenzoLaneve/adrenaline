package it.polimi.deib.newdem.adrenaline.view.inet.events;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UpdateUsernameResponseTest {

    UpdateUsernameResponse e;

    @Before
    public void setUp() throws Exception {
        e = new UpdateUsernameResponse("Larry");
    }

    @Test
    public void testGetNewUsername() throws Exception {
        assertEquals("Larry", e.getNewUsername());
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.notifyEvent(new MockConnectionSender(), new MockConnectionReceiver());
    }
}