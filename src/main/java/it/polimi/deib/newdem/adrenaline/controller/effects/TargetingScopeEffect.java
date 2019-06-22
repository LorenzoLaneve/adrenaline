package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public class TargetingScopeEffect implements Effect {

    private static final int ADDITIONAL_DAMAGE = 1;

    private static final PaymentInvoice ADDITIONAL_DAMAGE_PAYMENT = new PaymentInvoice(0, 0, 0, 1);


    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {

        if (manager.pay(ADDITIONAL_DAMAGE, ADDITIONAL_DAMAGE_PAYMENT)) {
            manager.damagePlayer(actor, manager.getVictim(), 1, 0);
        } else {
            throw new UndoException();
        }

    }

}
