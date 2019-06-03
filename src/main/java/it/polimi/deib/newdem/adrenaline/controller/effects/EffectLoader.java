package it.polimi.deib.newdem.adrenaline.controller.effects;

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
