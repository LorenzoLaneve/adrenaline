package it.polimi.deib.newdem.adrenaline.model.game.turn;

public class TurnInterruptedException extends Exception {

    public TurnInterruptedException() {
        super();
    }

    public TurnInterruptedException(String message) {
        super(message);
    }
}
