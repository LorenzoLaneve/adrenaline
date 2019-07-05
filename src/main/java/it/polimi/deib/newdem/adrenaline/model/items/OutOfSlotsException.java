package it.polimi.deib.newdem.adrenaline.model.items;

public class OutOfSlotsException extends Exception {
    /**
     * Thrown when the WeaponSet is already full.
     * @param message
     */
    public OutOfSlotsException(String message) {
        super(message);
    }

    public OutOfSlotsException() {
        super();
    }
}
