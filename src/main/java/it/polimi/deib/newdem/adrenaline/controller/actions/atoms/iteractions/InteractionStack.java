package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomEffectContext;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;

public interface InteractionStack {

    /**
     * Visits this {@code InteractionStack} starting from first interaction on it
     * until it no longer modifies itself.
     *
     * @throws UndoException this stack has been emptied and the user requested to propagate undo.
     */
    void resolve() throws UndoException;

    /**
     * pushes a new {@code Interaction} on this stack
     * @param interaction to append on top of this stack
     */
    void push(Interaction interaction);

    /**
     * Revisits this {@code InteractionStack} starting from the most recent interaction
     * which required input
     *
     * @throws UndoException this stack has been emptied and the user requested to propagate undo.
     */
    void revisit() throws UndoException;

    /**
     * Registers the given {@code AtomEffectContext} for later use by {@code Interaction}s
     * within this stack.
     * @param context to be registered
     */
    void registerContext(AtomEffectContext context);
}
