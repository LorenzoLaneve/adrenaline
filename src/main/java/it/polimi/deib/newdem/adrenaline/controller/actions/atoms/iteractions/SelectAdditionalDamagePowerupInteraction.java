package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpTrigger;

import java.util.List;

public class SelectAdditionalDamagePowerupInteraction extends InteractionBase {

    public SelectAdditionalDamagePowerupInteraction(InteractionContext context) {
        super(context);
    }

    @Override
    public void execute() throws UndoException {
        List<PowerUpCard> legalPowerUps = context.getActor().getInventory().getPowerUpByTrigger(PowerUpTrigger.DAMAGE_DEALT);

        if(legalPowerUps.isEmpty()) { return; }

        PowerUpCard selectedPup = null;
        try{
            selectedPup = context.getDataSource().choosePowerUpCard(legalPowerUps);
        }
        catch (UndoException e) {
            // if undo, terminate this interaction stack.
        }

        if(null == selectedPup) { return; }

        context.pushInteraction(new SelectAdditionalDamageVictimInteraction(context, selectedPup));
    }

    @Override
    public void revert() {
        // dp nothing
    }

    @Override
    public boolean requiresInput() {
        return true;
    }
}
