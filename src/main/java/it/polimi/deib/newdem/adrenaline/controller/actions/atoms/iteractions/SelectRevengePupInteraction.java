package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpTrigger;

import java.util.List;

public class SelectRevengePupInteraction extends InteractionBase {

    public SelectRevengePupInteraction(InteractionContext context) {
        super(context);
    }

    @Override
    public void execute() throws UndoException {
        List<PowerUpCard> availablePups = context.getActor().getInventory().getPowerUpByTrigger(PowerUpTrigger.DAMAGE_TAKEN);

        if(availablePups.isEmpty()) { return; }

        PowerUpCard selectedPup = context.getDataSource().choosePowerUpCard(availablePups);

        if(null != selectedPup) {
            context.pushInteraction(new RevengeEffectApplicationIneraction(context, selectedPup));
        }
    }

    @Override
    public void revert() {
        // no changes were made
    }

    @Override
    public boolean requiresInput() {
        return true;
    }
}
