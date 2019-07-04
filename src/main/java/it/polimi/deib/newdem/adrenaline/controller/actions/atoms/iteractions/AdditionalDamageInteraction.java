package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.List;

public class AdditionalDamageInteraction extends InteractionBase {

    public AdditionalDamageInteraction(InteractionContext context) {
        super(context);
    }

    @Override
    public void execute() {
        List<Player> damagedPlayers = context.getDamagedPlayers();

        if(damagedPlayers.isEmpty()) { return; }

        context.pushInteraction(new SelectAdditionalDamagePowerupInteraction(context));
    }

    @Override
    public void revert() {

    }

    @Override
    public boolean requiresInput() {
        return false;
    }
}
