package it.polimi.deib.newdem.adrenaline.model.items;

public class InvalidJSONException extends Exception {

    /**
     * Thrown when the json file used to load a deck is invalid.
     * @param message
     */
    public InvalidJSONException(String message) {
        super(message);
    }

    public InvalidJSONException() {
        super();
    }

}
