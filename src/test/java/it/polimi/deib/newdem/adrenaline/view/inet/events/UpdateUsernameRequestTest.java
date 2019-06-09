package it.polimi.deib.newdem.adrenaline.view.inet.events;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UpdateUsernameRequestTest {

    @Test
    public void testNotifyEvent() throws Exception {
        new UpdateUsernameRequest().publish(new MockConnectionSender());
    }
}