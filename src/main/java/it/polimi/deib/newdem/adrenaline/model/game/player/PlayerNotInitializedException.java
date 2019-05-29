package it.polimi.deib.newdem.adrenaline.model.game.player;

public class PlayerNotInitializedException extends IllegalStateException {
    public PlayerNotInitializedException() {
        super();
    }

    public PlayerNotInitializedException(String message) {
        super(message);
    }
}
