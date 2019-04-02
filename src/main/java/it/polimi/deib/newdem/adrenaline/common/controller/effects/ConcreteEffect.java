package it.polimi.deib.newdem.adrenaline.common.controller.effects;

import it.polimi.deib.newdem.adrenaline.common.controller.actions.PaymentInvoice;

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
