package it.polimi.deib.newdem.adrenaline.view.client;

/**
 * Used to immediately rewind the stack in case of close requested by
 * the user.
 */
public class ClosedException extends RuntimeException {

    public ClosedException(String message) {
        super(message);
    }

}
