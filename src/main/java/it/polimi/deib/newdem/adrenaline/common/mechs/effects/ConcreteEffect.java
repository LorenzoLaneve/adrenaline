package it.polimi.deib.newdem.adrenaline.common.mechs.effects;

import it.polimi.deib.newdem.adrenaline.common.mechs.actions.PaymentInvoice;

abstract class ConcreteEffect implements Effect {

    private int effectId;

    public ConcreteEffect(int id) {
        //TODO
    }

    public int getEffectId() {
        //TODO

        return 0;
    }

    public PaymentInvoice getPrice() {
        //TODO
        return null;
    }
}
