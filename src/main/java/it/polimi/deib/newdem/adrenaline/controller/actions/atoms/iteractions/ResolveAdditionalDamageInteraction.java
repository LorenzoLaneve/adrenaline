package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.InterruptExecutionException;
import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.controller.effects.EffectManager;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.items.OutOfSlotsException;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;

/**
 * Interaction encapsulating the application of additional damage after a SHOOT action.
 *
 * This is the only instance in the game of a damage which does not realize a mark.
 */
public class ResolveAdditionalDamageInteraction extends InteractionBase {

    private PowerUpCard selectedPup;

    /**
     * Builds a new {@code ResolveAdditionalDamageInteraction } bound to the given {@code InteractionContext}
     * @param context this interaction's environment
     * @param selectedPup the card of the effect to apply
     */
    public ResolveAdditionalDamageInteraction(InteractionContext context, PowerUpCard selectedPup) {
        super(context);
        this.selectedPup = selectedPup;
    }

    @Override
    public void execute() throws UndoException {

        Effect effect = selectedPup.getEffect();

        try {
            context.getActor().getInventory().removePowerUp(selectedPup);
            effect.apply(new EffectManager(context.getEffectContext()), context.getActor());
            context.getGame().getPowerUpDeck().discard(selectedPup);
        }
        catch (InterruptExecutionException e) {
            context.getGame().getPowerUpDeck().discard(selectedPup);
        }

        context.pushInteraction(new AdditionalDamageInteraction(context));
    }

    @Override
    public void revert() {
        // this will never be undone, AdditionalDamageInteraction::execute
        // is not allowed to throw UndoException.
    }

    @Override
    public boolean requiresInput() {
        return false;
    }
}
