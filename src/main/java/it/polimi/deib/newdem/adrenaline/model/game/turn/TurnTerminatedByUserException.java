package it.polimi.deib.newdem.adrenaline.model.game.turn;

public class TurnTerminatedByUserException extends Exception {

    public TurnTerminatedByUserException() {
        super();
    }

    public TurnTerminatedByUserException(String message) {
        super(message);
    }
}
