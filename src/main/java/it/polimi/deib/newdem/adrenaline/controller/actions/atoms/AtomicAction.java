package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;

/**
 * Building block of {@code Action}, combinable with others in a complete {@code Action}
 */
public interface AtomicAction {

    /**
     * Start execution of this {@code Atom} from its entry point
     *
     * @throws UndoException when this atom reports the need to revert its predecessor
     */
    void executeFromStart() throws UndoException;

    /**
     * Start execution of an {@code Atom} that has already been run
     * from the last {@code Interaction} which required user input.
     *
     * @throws UndoException then this atom reports the need to revert its predecessor
     */
    void executeFromLatest() throws UndoException;

    /**
     * Retrieves the
     * @return
     */
    AtomEffectContext getEffectContext();
}
