package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.model.items.Weapon;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;

public class MockWeaponCard implements WeaponCard {

    private String name;

    public MockWeaponCard(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

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
        return null;
    }

    @Override
    public Weapon makeWeapon() {
        return null;
    }

    @Override
    public int getCardID() {
        return 0;
    }
}
