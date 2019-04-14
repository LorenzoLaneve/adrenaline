package it.polimi.deib.newdem.adrenaline.common.controller;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestLobbyTimer {

    @Test
    public void testStartPositive() {

        LobbyTimer t = new LobbyTimer(1);

        try {
            t.start(50);
        }
        catch (InterruptedException e) {
            fail();
        }
    }

    @Test
    public void testStartNegative() {

        LobbyTimer t = new LobbyTimer(5);

        try{
            t.start(-7);
        }
        catch (InterruptedException e) {
            fail();
        }
        catch (IllegalArgumentException e) {
            assertTrue(true);
        }

        try{
            t.start(0);
        }
        catch (InterruptedException e) {
            fail();
        }
        catch (IllegalArgumentException e) {
            // ok
        }
    }

    @Test
    public void testConstructorPositive() {
        LobbyTimer t;

        t = new LobbyTimer(1);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegative() {
        LobbyTimer t;

        t = new LobbyTimer(-1);
        t = new LobbyTimer(0);
    }

    @Test
    public void testAbortPositive() {
        //TODO unclear expected behavior
    }

    @Test
    public void testAbortNegative(){
        // TODO unclear expected behavior
    }

    @Test
    public void testGetSecondsLeftPositive(){
        // TODO unclear expected behavior
    }

    @Test
    public void testGetSecondsLeftNegative(){
        // TODO unclear expected behavior
    }

    @Test
    public void testResetPositive(){
        // TODO unclear expected behavior
    }
    @Test
    public void testResetEgative(){
        // TODO unclear expected behavior
    }


    @Test
    public void testSetListenerPositive(){
        // TODO unclear expected behavior
    }

    @Test
    public void testSetListenerNegative(){
        // TODO unclear expected behavior
    }
}
