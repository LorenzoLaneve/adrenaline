package it.polimi.deib.newdem.adrenaline.common.controller.effects;

import it.polimi.deib.newdem.adrenaline.common.controller.actions.PaymentInvoice;

public interface Effect {

    int getEffectId();

    PaymentInvoice getPrice();

    void apply(EffectVisitor visitor);

}
