package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.actions.Action;

public abstract class AtomBase implements AtomicAction {
    protected AtomsContainer parent;

    public AtomBase(AtomsContainer parent) {
        this.parent = parent;
    }
}
