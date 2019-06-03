package it.polimi.deib.newdem.adrenaline.model.items;

import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;

public class WeaponCardImpl implements WeaponCard {

    private int cardID;

    private PaymentInvoice pickupPrice;

    private PaymentInvoice reloadPrice;

    private Effect effect;


    public WeaponCardImpl(int cardID, PaymentInvoice pickupPrice, PaymentInvoice reloadPrice, Effect effect) {
        this.cardID = cardID;
        this.pickupPrice = pickupPrice;
        this.reloadPrice = reloadPrice;

        this.effect = effect;
    }

    @Override
    public int getCardID() {
        return cardID;
    }

    @Override
    public PaymentInvoice getPickupPrice() {
        return pickupPrice;
    }

    @Override
    public PaymentInvoice getReloadPrice() {
        return reloadPrice;
    }

    @Override
    public Effect getEffect() {
        return effect;
    }

    @Override
    public Weapon makeWeapon() {
        // TODO
        return null;
    }


}
