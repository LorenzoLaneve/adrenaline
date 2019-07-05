package it.polimi.deib.newdem.adrenaline.model.items;


public class NoDrawableCardException extends Exception {

    /**
     * Thrown when there isn't any card left drawable in the deck.
     */
    public NoDrawableCardException() {
        super();
    }

    public NoDrawableCardException(String message) {
        super(message);
    }
}
