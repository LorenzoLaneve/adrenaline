package it.polimi.deib.newdem.adrenaline.model.game.turn;

public class TurnTerminatedByUserException extends TurnInterruptedException {

    public TurnTerminatedByUserException() {
        super();
    }

    public TurnTerminatedByUserException(String message) {
        super(message);
    }
}
