package it.polimi.deib.newdem.adrenaline.common.controller.effects;

public interface Effect {

    int getEffectId();

    PaymentInvoice getPrice();

    void apply(EffectVisitor visitor);

}
