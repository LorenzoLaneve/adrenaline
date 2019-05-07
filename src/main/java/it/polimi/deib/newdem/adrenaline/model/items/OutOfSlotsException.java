package it.polimi.deib.newdem.adrenaline.model.items;

public class OutOfSlotsException extends Exception {

    public OutOfSlotsException(String message) {
        super(message);
    }

    public OutOfSlotsException() {
        super();
    }
}
