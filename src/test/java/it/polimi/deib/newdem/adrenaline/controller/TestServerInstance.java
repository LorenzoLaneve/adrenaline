package it.polimi.deib.newdem.adrenaline.controller;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.utils.FileUtils;
import it.polimi.deib.newdem.adrenaline.view.inet.IncomingUserModule;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class TestServerInstance {

    class MockModule implements IncomingUserModule {

        @Override
        public void init() {

        }

        @Override
        public User newUser() throws InterruptedException {
            return null;
        }

        @Override
        public void close() {

        }
    }


    @Test
    public void testStartSuccess() {
        Config c;
        try {
            c = Config.fromFile(FileUtils.getAbsoluteDecodedFilePath("configtest.json", this.getClass()));
        } catch (InvalidConfigException e) {
            fail();
            return;
        }

        ServerInstance instance = new ServerInstance(Logger.getGlobal(), c);

        try {
            instance.getUserGreeter().addUserModule(new MockModule());
        } catch (InvalidStateException x) {
            fail();
        }

        assertNull(instance.getUserRegistry());
        assertNull(instance.getLobbyRegistry());
        instance.init();
        assertNotNull(instance.getUserRegistry());
        assertNotNull(instance.getLobbyRegistry());

        try {
            instance.getUserGreeter().addUserModule(new MockModule());
            fail();
        } catch (InvalidStateException x) {
            // ok
        }

        try {
            instance.start();
        } catch (InvalidStateException x) {
            fail();
        }
    }

    @Test
    public void testStartFailure() {
        Config c;
        try {
            c = Config.fromFile(FileUtils.getAbsoluteDecodedFilePath("configtest.json", this.getClass()));
        } catch (InvalidConfigException e) {
            fail();
            return;
        }

        ServerInstance instance = new ServerInstance(Logger.getGlobal(), Config.getDefaultConfig());

        try {
            instance.start();
        } catch (InvalidStateException x) {
            // ok.
        }
    }

}