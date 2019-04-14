package it.polimi.deib.newdem.adrenaline.common.controller;

import it.polimi.deib.newdem.adrenaline.common.model.mgmt.User;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserGreeterTest {

    private class MockUserModule implements IncomingUserModule{

        private boolean istriggered;
        private boolean closed;

        @Override
        public void init() {
            istriggered = true;
            closed = false;
        }

        @Override
        public User newUser() throws InterruptedException {
            return null;
        }

        @Override
        public void close() {
            closed = true;
        }

        public MockUserModule() {
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

    /*
    @Test
    public void testConstructorNegative() {

    }
    */

    @Test
    public void testAddUserModulePositive() {

        UserGreeter u = new UserGreeter();
        MockUserModule m1 = new MockUserModule();
        MockUserModule m2 = new MockUserModule();
        MockUserModule m3 = new MockUserModule();
        MockUserModule m4 = new MockUserModule();

        u.addUserModule(m1);
        u.addUserModule(m2);
        u.addUserModule(m3);
        u.addUserModule(m4);

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

        u.addUserModule(m);
        u.init();

        assertTrue(m.isTriggered());

        ////

        // testing .init() on empty greeter
        (new UserGreeter()).init();

    }

    @Test
    public void testStartPositive() {
        //TODO unclear behavior
    }

    @Test
    public void testAcceptPositive() {
        //TODO unclear behavior
    }

    @Test
    public void testClosePositive() {

        UserGreeter u = new UserGreeter();
        MockUserModule m1 = new MockUserModule();
        MockUserModule m2 = new MockUserModule();
        MockUserModule m3 = new MockUserModule();
        MockUserModule m4 = new MockUserModule();

        u.addUserModule(m1);
        u.addUserModule(m2);
        u.addUserModule(m3);
        u.addUserModule(m4);

        u.init();

        u.close();

        assertTrue(m1.isClosed() &&
                m2.isClosed() &&
                m3.isClosed() &&
                m4.isClosed()
        );

        // test closing user module not started

        u = new UserGreeter();
        m1 = new MockUserModule();
        u.addUserModule(m1);

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
        }
        catch (NullPointerException | IllegalArgumentException e ) {
            // ok
        }
        catch (Exception e ) {
            fail();
        }
    }

    @Test
    public void testInitNegative() {

        UserGreeter u = new UserGreeter();
        MockUserModule m1 = new MockUserModule();
        MockUserModule m2 = new MockUserModule();
        MockUserModule m3 = new MockUserModule();
        MockUserModule m4 = new MockUserModule();

        u.addUserModule(m1);
        u.addUserModule(m2);

        u.init();

        u.addUserModule(m3);
        u.addUserModule(m4);

        assertTrue(m1.isTriggered() &&
                m2.isTriggered() &&
                !m3.isTriggered() &&
                !m4.isTriggered()
        );

    }

    @Test
    public void testStartNegative() {
        //TODO unclear behavior
    }

    @Test
    public void testAcceptNegative() {
        //TODO unclear behavior
    }

    @Test
    public void testCloseNegative() {

    UserGreeter u = new UserGreeter();
    MockUserModule m = new MockUserModule();

    u.close();

    u.addUserModule(m);

    assertTrue(!m.isClosed());
    }
}