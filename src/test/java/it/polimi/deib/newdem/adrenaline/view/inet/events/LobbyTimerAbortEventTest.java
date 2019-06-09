package it.polimi.deib.newdem.adrenaline.view.inet.events;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LobbyTimerAbortEventTest {

    @Test
    public void testNotifyEvent() throws Exception {
        new LobbyTimerAbortEvent().publish(new MockConnectionSender());
    }
}