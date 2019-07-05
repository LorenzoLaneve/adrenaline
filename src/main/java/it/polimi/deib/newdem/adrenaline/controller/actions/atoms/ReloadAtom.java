package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions.EntryPointType;

/**
 * Atom representing an atomic RELOAD action
 */
public class ReloadAtom extends AtomContext {

    /**
     * Builds a new atom bound to the given {@context parent}
     *
     * @param parent this atom's context
     */
    public ReloadAtom(AtomsContainer parent) {
        super(parent, EntryPointType.RELOAD);
    }
}
