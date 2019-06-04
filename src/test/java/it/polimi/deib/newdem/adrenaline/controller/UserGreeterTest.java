package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.inet.IncomingUserModule;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserGreeterTest {

    private static User[] mockUsers = {
            new User() {
                @Override
                public String getName() {
                    return "Mario Rossi";
                }
            },

            new User() {
                @Override
                public String getName() {
                    return "Marco Bianchi";
                }
            }
    };



    private class MockUserModule implements IncomingUserModule {

        private boolean istriggered;
        private boolean closed;

        private int userIndex;

        @Override
        public void init() {
            istriggered = true;
            closed = false;
            userIndex = 0;
        }

        @Override
        public synchronized User newUser() throws InterruptedException {
            if (userIndex < 2) {
                return mockUsers[userIndex++];
            }

            return null;
        }

        @Override
        public void close() {
            closed = true;
        }

        public MockUserModule() {
            closed = true;
            istriggered = false;
        }

        public boolean isClosed() {
            return closed;
        }

        public boolean isTriggered() {
            return istriggered;
        }
    }

    @Test
    public void testConstructorPositive() {
        new UserGreeter();
    }

    @Test
    public void testAddUserModulePositive() {

        UserGreeter u = new UserGreeter();
        MockUserModule m1 = new MockUserModule();
        MockUserModule m2 = new MockUserModule();
        MockUserModule m3 = new MockUserModule();
        MockUserModule m4 = new MockUserModule();

        try {
            u.addUserModule(m1);
            u.addUserModule(m2);
            u.addUserModule(m3);
            u.addUserModule(m4);
        } catch (InvalidStateException x) {
            fail();
        }

        u.init();

        assertTrue(m1.isTriggered() &&
                m2.isTriggered() &&
                m3.isTriggered() &&
                m4.isTriggered()
        );
    }

    @Test
    public void testInitPositive() {

        UserGreeter u = new UserGreeter();
        MockUserModule m = new MockUserModule();

        try {
            u.addUserModule(m);
        } catch (InvalidStateException e) {
            fail();
        }
        u.init();

        assertTrue(m.isTriggered());

        ////

        // testing .init() on empty greeter
        (new UserGreeter()).init();

    }

    @Test
    public void testStartPositive() {
        UserGreeter u = new UserGreeter();

        MockUserModule m1 = new MockUserModule();

        try {
            u.addUserModule(m1);
        } catch (InvalidStateException x) {
            fail();
        }

        u.init();

        try {
            u.start();
        } catch (InvalidStateException e) {
            fail();
        }
    }

    @Test
    public void testStartNegative() {
        UserGreeter u = new UserGreeter();

        MockUserModule m1 = new MockUserModule();

        try {
            u.addUserModule(m1);
        } catch (InvalidStateException x) {
            fail();
        }

        try {
            u.start();
            fail();
        } catch (InvalidStateException e) {
            // ok
        }
    }


    @Test
    public void testAcceptPositive() {

        UserGreeter u = new UserGreeter();

        MockUserModule m1 = new MockUserModule();

        try {
            u.addUserModule(m1);
        } catch (InvalidStateException x) {
            fail();
        }

        u.init();

        try {
            u.start();
        } catch (InvalidStateException e) {
            fail();
        }

        try {
            assertEquals(u.accept(), mockUsers[0]);
            assertEquals(u.accept(), mockUsers[1]);
        } catch (InterruptedException x) {
            fail();
        }

    }

    @Test
    public void testClosePositive() {

        UserGreeter u = new UserGreeter();
        MockUserModule m1 = new MockUserModule();
        MockUserModule m2 = new MockUserModule();
        MockUserModule m3 = new MockUserModule();
        MockUserModule m4 = new MockUserModule();

        try {
            u.addUserModule(m1);
            u.addUserModule(m2);
            u.addUserModule(m3);
            u.addUserModule(m4);
        } catch (InvalidStateException x) {
            fail();
        }

        u.init();

        assertTrue(m1.isTriggered() &&
                m2.isTriggered() &&
                m3.isTriggered() &&
                m4.isTriggered()
        );


        u.close();

        assertTrue(m1.isClosed() &&
                m2.isClosed() &&
                m3.isClosed() &&
                m4.isClosed()
        );

        // test closing user module not started

        u = new UserGreeter();
        m1 = new MockUserModule();

        try {
            u.addUserModule(m1);
        } catch (Exception x) {
            fail();
        }

        u.close();

        assertTrue(m1.isClosed());

    }

    @Test
    public void testAddUserModuleNegative() {
        UserGreeter u = new UserGreeter();
        MockUserModule m = null;

        try {
            u.addUserModule(m);
            fail();
        } catch (IllegalArgumentException e) {
            // ok
        } catch (Exception e) {
            fail();
        }

        u.init();
        m = new MockUserModule();

        u.init();

        try {
            u.addUserModule(m);
            fail();
        } catch (IllegalArgumentException x) {
            fail();
        } catch (InvalidStateException x) {
            // ok
        }
    }
}