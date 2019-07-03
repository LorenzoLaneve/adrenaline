package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.Weapon;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;

public class NullWeaponCard implements WeaponCard {
    @Override
    public PaymentInvoice getPickupPrice() {
        return null;
    }

    @Override
    public PaymentInvoice getReloadPrice() {
        return null;
    }

    @Override
    public Effect getEffect() {
        return new NullEffect();
    }

    @Override
    public Weapon makeWeapon(Player player) {
        return null;
    }

    @Override
    public int getCardID() {
        return 42;
    }
}
