package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions.EntryPointType;
import it.polimi.deib.newdem.adrenaline.controller.effects.EffectManager;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpTrigger;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Atom representing an atomic action of using one or more powerups.
 */
public class PowerUpAtom extends AtomContext {

    /**
     * Builds a new atom bound to the given {@context parent}
     *
     * @param parent this atom's context
     */
    public PowerUpAtom(AtomsContainer parent) {
        super(parent, EntryPointType.POWERUP);
    }

}
