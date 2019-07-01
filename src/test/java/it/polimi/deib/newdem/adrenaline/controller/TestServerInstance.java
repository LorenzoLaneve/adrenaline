package it.polimi.deib.newdem.adrenaline.controller;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class TestServerInstance {

    Logger logger;
    Config currentConfig;
    ServerInstance instance;

    @Before
    public void setUp() throws Exception {
        logger = Logger.getGlobal();
        currentConfig = Config.getDefaultConfig();
    }

    @Test
    public void testStartSuccess() {
        instance = new ServerInstance(Logger.getGlobal(), currentConfig);

        assertNull(instance.getUserRegistry());
        assertNull(instance.getLobbyRegistry());
        instance.init();
        assertNotNull(instance.getUserRegistry());
        assertNotNull(instance.getLobbyRegistry());

        new Thread(() -> {
            try {
                Thread.sleep(100);
                // just to let the instance call start
            } catch (InterruptedException x) {
                //nothing to do here.
            }

            instance.kill();
        }).start();

        try {
            instance.start();
        } catch (InvalidStateException x) {
            fail();
        }
    }

    @Test
    public void testStartFailure() {
        instance = new ServerInstance(Logger.getGlobal(), Config.getDefaultConfig());

        try {
            instance.start();
        } catch (InvalidStateException x) {
            // ok.
        }
    }

}