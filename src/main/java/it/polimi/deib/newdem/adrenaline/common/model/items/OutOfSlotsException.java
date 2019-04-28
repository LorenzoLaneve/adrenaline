package it.polimi.deib.newdem.adrenaline.common.model.items;

public class OutOfSlotsException extends Exception {

    public OutOfSlotsException(String message) {
        super(message);
    }

    public OutOfSlotsException() {
        super();
    }
}
