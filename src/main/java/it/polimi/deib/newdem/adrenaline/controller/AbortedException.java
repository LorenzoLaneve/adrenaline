package it.polimi.deib.newdem.adrenaline.controller;

/**
 * User by {@code TimedExecutor} class. This exception signals that the execution handled by the TimedExecutor
 * has been aborted due to the call to {@code TimedExecutor#abort()}.
 */
public class AbortedException extends Exception {

    public AbortedException() {
        super();
    }

    public AbortedException(String message) {
        super(message);
    }

}
