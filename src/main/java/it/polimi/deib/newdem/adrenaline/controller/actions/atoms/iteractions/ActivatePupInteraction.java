package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectManager;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;

/**
 * Interaction encapsulating the application of the effect of a powerup within a USE_POWERUP atom
 */
public class ActivatePupInteraction extends InteractionBase {

    private PowerUpCard pupCard;

    /**
     * Builds a new {@code ActivatePupInteraction } bound to the given {@code InteractionContext}
     * @param context this interaction's environment
     * @param pupCard powerup to activate
     */
    public ActivatePupInteraction(InteractionContext context, PowerUpCard pupCard) {
        super(context);
        this.pupCard = pupCard;
    }

    @Override
    public void execute() throws UndoException {

        EffectManager manager = new EffectManager(context.getEffectContext());
        manager.execute(pupCard.getEffect());

        context.getActor().getInventory().removePowerUp(pupCard);
        context.getGame().getPowerUpDeck().discard(pupCard);

        context.pushInteraction(new SelectPupInteraction(context));
    }

    @Override
    public void revert() {
        // is terminal
    }

    @Override
    public boolean requiresInput() {
        // depends on effect. Often times, it's true.
        // this should never matter.
        return true;
    }
}
