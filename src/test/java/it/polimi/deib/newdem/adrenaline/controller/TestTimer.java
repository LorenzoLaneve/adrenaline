package it.polimi.deib.newdem.adrenaline.controller;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestTimer {

    private TimerListener emptyListener = new TimerListener() {
        @Override
        public void timerWillStart(int secondsLeft) {  }

        @Override
        public void timerSync(int secondsLeft) {  }

        @Override
        public void timerDidFinish() {  }

        @Override
        public void timerDidAbort() {  }
    };



    @Test
    public void testStartPositive() {

        Timer t = new Timer(1, emptyListener);

        try {
            t.start(50);
        }
        catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testStartNegative() {

        Timer t = new Timer(5, emptyListener);

        try{
            t.start(-7);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testConstructorPositive() {
        new Timer(1, emptyListener);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegative() {
        new Timer(-1, emptyListener);
        new Timer(0, emptyListener);
    }

}
