package it.polimi.deib.newdem.adrenaline.model.items;

public class DeckAlreadyLoadedException extends Exception {
    public DeckAlreadyLoadedException() {
        super();
    }

    public DeckAlreadyLoadedException(String message) {
        super(message);
    }
}
