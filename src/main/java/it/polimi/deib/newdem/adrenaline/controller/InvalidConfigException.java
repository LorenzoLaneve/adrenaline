package it.polimi.deib.newdem.adrenaline.controller;

/**
 * Thrown when the read configuration does not exist or is malformed.
 */
public class InvalidConfigException extends Exception {

    public InvalidConfigException() {
        super();
    }

    public InvalidConfigException(String message) {
        super(message);
    }


}
