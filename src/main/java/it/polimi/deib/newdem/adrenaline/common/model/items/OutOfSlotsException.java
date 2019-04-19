package it.polimi.deib.newdem.adrenaline.common.model.items;

public class OutOfSlotsException extends Exception {

    public OutOfSlotsException() {
        super();
    }

    public OutOfSlotsException(String message) {
        super(message);
    }
}
