package it.polimi.deib.newdem.adrenaline.model.items;

import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;

public class PowerUpCardImpl implements PowerUpCard {

    Effect effect;
    AmmoColor color;
    int id;


    public PowerUpCardImpl(int id, AmmoColor ammoColor, Effect effect){
        this.effect = effect;
        this.color = ammoColor;
        this.id = id;
    }

    @Override
    public PaymentInvoice getUsePrice() {
        return null;
    }

    @Override
    public Effect getEffect() {
        return effect;
    }

    @Override
    public int getCardID() {
        return id;
    }

    @Override
    public AmmoColor getColor() {
        return color;
    }
}
