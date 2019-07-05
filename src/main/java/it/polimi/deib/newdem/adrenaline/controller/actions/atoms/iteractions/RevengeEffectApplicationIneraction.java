package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.controller.effects.EffectManager;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.Weapon;

/**
 * This class will only wrap the execution of the revenge effect.
 *
 * Any other sub-effects, such as payment, are handled by the {@code Effect} itself.
 */
public class RevengeEffectApplicationIneraction extends InteractionBase {

    private PowerUpCard selectedPup;

    /**
     * Builds a new {@code RevengeEffectApplicationIneraction } bound to the given {@code InteractionContext}
     * @param context this interaction's environment
     * @param selectedPup the card of the effect to apply
     */
    public RevengeEffectApplicationIneraction(InteractionContext context, PowerUpCard selectedPup) {
        super(context);
        this.selectedPup = selectedPup;
    }

    @Override
    public void execute() throws UndoException {
        context.getActor().getInventory().removePowerUp(selectedPup);
        context.getGame().getPowerUpDeck().discard(selectedPup);

        Effect effect = selectedPup.getEffect();
        effect.apply(new EffectManager(context.getEffectContext()), context.getActor());

        context.pushInteraction(new SelectRevengePupInteraction(context));
    }

    @Override
    public void revert() {
        // doesn't matter, will always be at the end of action.
    }

    @Override
    public boolean requiresInput() {
        return true;
    }

}
