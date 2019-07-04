package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions.EntryPointType;

public class ReloadAtom extends AtomContext {

    public ReloadAtom(AtomsContainer parent) {
        super(parent, EntryPointType.RELOAD);
    }
}
