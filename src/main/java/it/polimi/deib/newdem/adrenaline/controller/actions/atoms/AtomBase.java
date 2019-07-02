package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions.*;
import it.polimi.deib.newdem.adrenaline.controller.effects.EffectContext;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;

public abstract class AtomBase implements AtomicAction {

    protected AtomsContainer parent;
    protected InteractionStackImpl interactionsStack;
    private EntryPointFactory entryPointFactory;

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
    public EffectContext getEffectContext() {
        return null;
    }
}
