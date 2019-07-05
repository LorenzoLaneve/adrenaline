package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions.EntryPointType;
import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.Weapon;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Atom representing an atomic SHOOT action
 */
public class ShootAtom extends AtomContext {

    private Player victim;

    /**
     * Builds a new atom bound to the given {@context parent}
     *
     * @param parent this atom's context
     */
    public ShootAtom(AtomsContainer parent) {
        super(parent, EntryPointType.SHOOT);
    }

    @Override
    public Player getVictim() {
        return victim;
    }

    @Override
    public void setVictim(Player victim) {
        this.victim = victim;
    }
}
