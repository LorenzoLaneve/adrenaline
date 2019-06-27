package it.polimi.deib.newdem.adrenaline.model.game;

/**
 * A pop to the damage board was attempted, but the damage board was already empty.
 */
public class DamageTrackEmptyException extends Exception {

    public DamageTrackEmptyException() {
        super();
    }

    public DamageTrackEmptyException(String message) {
        super(message);
    }

}
