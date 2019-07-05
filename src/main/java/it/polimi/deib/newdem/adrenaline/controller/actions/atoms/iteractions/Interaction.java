package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;

/**
 * The simplest interaction possible between the system and a human.
 */
public interface Interaction {

    /**
     * Executes this interaction.
     *
     * MAy add new interactions on the context's stack.
     *
     * @throws UndoException user requested undo. Guarantees that the context's state
     * at the moment of throwing this exception is
     * the same as when this method was called.
     */
    void execute() throws UndoException;

    /**
     * Reverts this interaction, restoring the game state preceding it.
     * Assumes that this is the last interaction on the stack.
     */
    void revert();

    /**
     * Checks whether this interaction requires user input.
     * @return whether this interaction requires user input
     */
    boolean requiresInput();
}
