package it.polimi.deib.newdem.adrenaline.controller;

/**
 * Thrown when the TimedExecutor interrupts its execution due to a timeout on its timer.
 */
public class TimeoutException extends Exception {

    public TimeoutException() {
        super();
    }

    public TimeoutException(String message) {
        super(message);
    }

}
