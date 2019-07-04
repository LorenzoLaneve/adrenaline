package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions.EntryPointType;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public class RevengeAtom extends AtomContext {

    private Player attacker;

    public RevengeAtom(AtomsContainer parent, Player attacker) {
        super(parent, EntryPointType.REVENGE);
        this.attacker = attacker;
    }

    @Override
    public Player getAttacker() {
        return attacker;
    }
/*
    @Override
    public void executeFromStart() throws UndoException {
        // attacker = parent.getDataSource().peekActor();
        // if actor was pushed, active player is victim
        super.executeFromStart();
    }
    */
}
