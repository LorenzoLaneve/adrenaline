package it.polimi.deib.newdem.adrenaline.controller;

/**
 * Thrown when the callee has received a message not allowed in its current state.
 */
public class InvalidStateException extends Exception {

    public InvalidStateException(String message) {
        super(message);
    }

}
