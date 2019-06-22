package it.polimi.deib.newdem.adrenaline.controller;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TimedExecutorTest {

    private boolean executed = false;

    @Test
    public void testExecute() {
        executed = false;

        TimedExecutor executor = new TimedExecutor(() -> {
            TimedExecutor.pauseTimer();
            executed = true;
            TimedExecutor.resumeTimer();
        });

        try {
            executor.execute(30);
        } catch (Exception x) {
            fail();
        }

        assertTrue(executed);
    }

    @Test
    public void testAbort() {
        executed = false;

        TimedExecutor executor = new TimedExecutor(() -> {
            try {
                synchronized (this) { wait(); }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                TimedExecutor.abortExecution();
            }
        });

        executor.abort();

        try {
            executor.execute(30);
            fail();
        } catch (AbortedException e) {
            // ok
        } catch (TimeoutException e) {
            fail();
        }

    }

}
