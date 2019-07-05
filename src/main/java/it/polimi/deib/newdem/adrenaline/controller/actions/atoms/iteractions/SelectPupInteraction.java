package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectManager;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpTrigger;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Interaction encapsulating the selection of a powerup within a USE_POWERUP atom.
 */
public class SelectPupInteraction extends InteractionBase {

    /**
     * Builds a new {@code SelectPupInteraction } bound to the given {@code InteractionContext}
     * @param context this interaction's environment
     */
    public SelectPupInteraction(InteractionContext context) {
        super(context);
    }

    @Override
    public void execute() throws UndoException {
        List<PowerUpCard> availablePups = context.getActor().getInventory().getPowerUpByTrigger(PowerUpTrigger.CALL);

        if(availablePups.isEmpty()) { return; }

        PowerUpCard selectedPup = context.getDataSource().choosePowerUpCard(availablePups);

        if(null != selectedPup) {
            context.pushInteraction(new ActivatePupInteraction(context, selectedPup));
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
