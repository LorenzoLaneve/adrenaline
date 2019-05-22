package it.polimi.deib.newdem.adrenaline.controller.effects;

public interface Effect {

    int getEffectId();

    PaymentInvoice getPrice();

    void apply(EffectVisitor visitor) throws UndoException;

}
