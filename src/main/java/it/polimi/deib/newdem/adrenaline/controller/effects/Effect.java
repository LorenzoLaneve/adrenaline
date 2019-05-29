package it.polimi.deib.newdem.adrenaline.controller.effects;

public interface Effect {

    int DYNAMIC = -1;

    int NO_ID = -2;


    int getEffectId();

    PaymentInvoice getPrice();

    void apply(EffectVisitor visitor) throws UndoException;

}
