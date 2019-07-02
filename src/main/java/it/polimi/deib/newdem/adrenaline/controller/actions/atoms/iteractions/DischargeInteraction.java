package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.controller.effects.EffectManager;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.items.Weapon;

public class DischargeInteraction extends InteractionBase {

    private Weapon weapon;

    public DischargeInteraction(InteractionContext context, Weapon weapon) {
        super(context);
        this.weapon = weapon;
    }

    @Override
    public void execute() throws UndoException {
        weapon.discharge();
        Effect effect = weapon.getCard().getEffect();
        effect.apply(new EffectManager(context.getEffectContext()), context.getActor());

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
