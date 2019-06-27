package it.polimi.deib.newdem.adrenaline.model.game;

/**
 * A caller tried to report new damage to a full damage board.
 */
public class DamageTrackFullException extends Exception {

    public DamageTrackFullException() {
        super();
    }

    public DamageTrackFullException(String message) {
        super(message);
    }

}
