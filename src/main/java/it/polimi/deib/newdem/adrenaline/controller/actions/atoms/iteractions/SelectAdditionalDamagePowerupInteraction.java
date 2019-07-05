package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpTrigger;

import java.util.List;

/**
 * Interaction encapsulating the selection of a player for additional damage after a SHOOT atom
 */
public class SelectAdditionalDamagePowerupInteraction extends InteractionBase {

    /**
     * Builds a new {@code SelectAdditionalDamagePowerupInteraction } bound to the given {@code InteractionContext}
     * @param context this interaction's environment
     */
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
