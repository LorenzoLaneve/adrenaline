package it.polimi.deib.newdem.adrenaline.model.game.player;

/**
 * Signals that a {@code Player} has not been initialized yet
 */
public class PlayerNotInitializedException extends IllegalStateException {

    /**
     * Signals that a {@code Player} has not been initialized yet
     */
    public PlayerNotInitializedException() {
        super();
    }

    /**
     * Signals that a {@code Player} has not been initialized yet with an additional
     * human readable message
     * @param message
     */
    public PlayerNotInitializedException(String message) {
        super(message);
    }
}
