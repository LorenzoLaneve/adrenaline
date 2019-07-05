package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions.EntryPointType;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

/**
 * Atom representing an atomic REVENGE action.
 *
 * This is not strictly detailed in the game's rules, but it fits nicely with the other atoms.
 */
public class RevengeAtom extends AtomContext {

    private Player attacker;

    /**
     * Builds a new atom bound to the given {@context parent}
     * aware of the {@code Player} that it needs to take revenge from
     *
     * @param parent this atom's context
     * @param attacker to get revenge from
     */
    public RevengeAtom(AtomsContainer parent, Player attacker) {
        super(parent, EntryPointType.REVENGE);
        this.attacker = attacker;
    }

    @Override
    public Player getAttacker() {
        return attacker;
    }
}
