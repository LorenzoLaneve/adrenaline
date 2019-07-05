package it.polimi.deib.newdem.adrenaline.model.game.turn;

/**
 * Signals that the current turn has been interrupted by the user
 */
public class TurnTerminatedByUserException extends TurnInterruptedException {

    /**
     * Signals that the current turn has been interrupted by the user
     */
    public TurnTerminatedByUserException() {
        super();
    }

    /**
     * Signals that the current turn has been interrupted by the user
     * with a human readable message
     */
    public TurnTerminatedByUserException(String message) {
        super(message);
    }
}
