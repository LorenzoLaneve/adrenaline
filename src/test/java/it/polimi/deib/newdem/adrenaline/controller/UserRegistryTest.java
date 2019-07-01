package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnectionBase;
import it.polimi.deib.newdem.adrenaline.view.inet.UserEventSubscriber;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UpdateUsernameResponse;
import it.polimi.deib.newdem.adrenaline.view.inet.events.UserEvent;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserRegistryTest {

    class MockConnection extends UserConnectionBase {

        public MockConnection(User user) {
            super(user);
        }

        @Override
        public void start() {

        }

        @Override
        public void sendEvent(UserEvent event) {

        }

    }


    @Test
    public void registryTest() {
        ServerInstance instance = new ServerInstance(Logger.getGlobal(), Config.getDefaultConfig());
        instance.init();

        UserRegistry registry = instance.getUserRegistry();

        User user1 = new User();
        User user2 = new User();

        registry.registerUser(user1);
        assertNull(user1.getName());

        registry.unregisterUser(user1);

        registry.registerUser(user1);
        MockConnection connection1 = new MockConnection(user1);
        connection1.publishEvent(new UpdateUsernameResponse("name1"));
        try {
            Thread.sleep(100); // just to let the event propagate
        } catch (InterruptedException x) {
            // nothing to do...
        }
        assertEquals(user1, registry.getUserByName("name1"));

        registry.registerUser(user2);
        MockConnection connection2 = new MockConnection(user2);
        connection2.publishEvent(new UpdateUsernameResponse("name1"));
        assertEquals(user1, registry.getUserByName("name1"));

        connection2.publishEvent(new UpdateUsernameResponse("name2"));

        try {
            Thread.sleep(100); // just to let the event propagate
        } catch (InterruptedException x) {
            // nothing to do...
        }

        assertEquals(user2, registry.getUserByName("name2"));
    }

}
