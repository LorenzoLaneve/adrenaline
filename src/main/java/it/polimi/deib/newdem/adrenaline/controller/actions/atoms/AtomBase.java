package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions.*;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;

/**
 * Base implementation of {@code AtomicAction}. Implments common functionality
 */
public abstract class AtomBase implements AtomicAction {

    protected AtomsContainer parent;
    protected InteractionStackImpl interactionsStack;
    private EntryPointFactory entryPointFactory;

    /**
     * Creates a new {@code AtomBase} bound to the given {@code AtomsContainer} and
     * with the set {@code EntryPointFactory}
     *
     * @param parent this atom's container
     * @param entryFactory this atom's entrypoint
     */
    public AtomBase(AtomsContainer parent, EntryPointFactory entryFactory) {
        this.parent = parent;
        this.interactionsStack = new InteractionStackImpl(parent);
        this.entryPointFactory = entryFactory;
    }

    @Override
    public void executeFromStart() throws UndoException {
        interactionsStack.push(entryPointFactory.makeInteraction(interactionsStack));
        interactionsStack.resolve();
    }

    @Override
    public void executeFromLatest() throws UndoException {
        interactionsStack.revisit();
    }

    @Override
    public AtomEffectContext getEffectContext() {
        return null;
    }
}
