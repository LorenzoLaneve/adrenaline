package it.polimi.deib.newdem.adrenaline.view.inet.events;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class LobbyDataEventTest {

    LobbyDataEvent e;
    String[] arr = {"one", "two"};

    @Before
    public void setUp() throws Exception {
        e = new LobbyDataEvent(Arrays.asList(arr));
    }

    @Test
    public void testGetUsers() throws Exception {
        assertArrayEquals(e.getUsers().toArray(), arr);
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.notifyEvent(new MockConnectionSender(), new MockConnectionReceiver());
    }
}