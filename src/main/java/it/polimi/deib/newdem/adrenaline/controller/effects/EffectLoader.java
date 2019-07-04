package it.polimi.deib.newdem.adrenaline.controller.effects;

/**
 * Class that provides methods to create effect objects from their class names.
 * This class uses Java Reflection to treat effects as resources. This design
 * allows to add new effect classes and the corresponding cards completely obeying
 * the Open-Closed principle.
 */
public class EffectLoader {

    /**
     * Returns a new instance of the effect with the given class name.
     * @throws InvalidEffectException If the class does not exists, is not a subclass of {@code Effect},
     * or if the instantiation could not be possible.
     */
    public static Effect fromClass(String className) throws InvalidEffectException {

        try {
            Class effectClass = Class.forName(className);

            return (Effect) effectClass.newInstance();
        } catch (Exception x) {
            throw new InvalidEffectException(x);
        }
    }

}
