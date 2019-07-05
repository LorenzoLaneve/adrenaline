package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;

/**
 * Execution sequence made up of {@code AtomicAction}s
 */
public interface AtomsRunSequence {

    /**
     * Add the given {@code Atom} to this run sequence
     *
     * @param atom to be added to this sequence
     */
    void append(AtomicAction atom);

    /**
     * Begin execution of this sequence.
     *
     * @throws UndoException the user wished to undo this action
     */
    void executeFromStart() throws UndoException;
}
