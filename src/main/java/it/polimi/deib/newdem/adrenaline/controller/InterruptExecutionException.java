package it.polimi.deib.newdem.adrenaline.controller;


/**
 * Thrown by TimedExecutor when the inner execution has requested to immediately return.
 */
public class InterruptExecutionException extends RuntimeException {

    public InterruptExecutionException() {
        super();
    }

    public InterruptExecutionException(String message) {
        super(message);
    }


}
