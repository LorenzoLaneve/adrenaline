package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.controller.effects.EffectManager;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.items.Weapon;

/**
 * Interaction encapsulating the resolution of the weapon's effect at the core of a SHOOT atom
 */
public class DischargeInteraction extends InteractionBase {

    private Weapon weapon;

    /**
     * Builds a new {@code DischargeInteraction } bound to the given {@code InteractionContext}
     * @param context this interaction's environment
     * @param weapon to apply the effect of
     */
    public DischargeInteraction(InteractionContext context, Weapon weapon) {
        super(context);
        this.weapon = weapon;
    }

    @Override
    public void execute() throws UndoException {
        weapon.discharge();
        Effect effect = weapon.getCard().getEffect();
        effect.apply(new EffectManager(context.getEffectContext()), context.getActor());
        // ^ effect manager reports to context (atom) which players were damaged

        context.pushInteraction(new AdditionalDamageInteraction(context));
        // push nothing, is terminal.

    }

    @Override
    public void revert() {
        // doesn't matter, will always be at the end of action.
    }

    @Override
    public boolean requiresInput() {
        // in general, this is dependent on the effect of the given weapon.
        // however we don't really care, as it will never be undone.
        // we return true simply because that's how most effects behave.
        return true;
    }
}
