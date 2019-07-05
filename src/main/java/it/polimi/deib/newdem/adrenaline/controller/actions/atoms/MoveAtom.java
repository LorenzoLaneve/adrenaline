package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions.EntryPointFactory;

/**
 * Atom representing a MOVE action of any radius
 */
public class MoveAtom extends AtomBase {

    /**
     * Builds a new atom bound to the given {@context parent} of the radius specified
     * within the {@code EntryPointFactory}
     *
     * @param parent this atom's context
     * @param entryPointFactory containing this MOVE's radius
     */
    public MoveAtom(AtomsContainer parent, EntryPointFactory entryPointFactory) {
        super(parent, entryPointFactory);
    }

}
