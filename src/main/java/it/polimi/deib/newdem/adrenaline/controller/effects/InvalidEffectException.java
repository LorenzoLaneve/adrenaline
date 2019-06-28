package it.polimi.deib.newdem.adrenaline.controller.effects;

/**
 * Thrown when the effect could not be loaded for problems with reflection and/or class file reading.
 */
public class InvalidEffectException extends Exception {

    public InvalidEffectException(Exception x) {
        super(x);
    }

}
