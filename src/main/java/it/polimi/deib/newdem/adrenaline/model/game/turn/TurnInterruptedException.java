package it.polimi.deib.newdem.adrenaline.model.game.turn;

/**
 * Signals that the current turn has been interrupted abruptly for any reason
 */
public class TurnInterruptedException extends Exception {

    /**
     * Signals that the current turn has been interrupted abruptly for any reason
     */
    public TurnInterruptedException() {
        super();
    }

    /**
     * Signals that the current turn has been interrupted abruptly for any reason
     * with a human readable message
     */
    public TurnInterruptedException(String message) {
        super(message);
    }
}
