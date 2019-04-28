package it.polimi.deib.newdem.adrenaline.common.model.game;

public class PlayerNotInitializedException extends IllegalStateException {
    public PlayerNotInitializedException() {
        super();
    }

    public PlayerNotInitializedException(String message) {
        super(message);
    }
}
