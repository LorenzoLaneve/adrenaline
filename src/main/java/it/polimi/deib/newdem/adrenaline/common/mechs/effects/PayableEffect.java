package it.polimi.deib.newdem.adrenaline.common.mechs.effects;

import it.polimi.deib.newdem.adrenaline.common.mechs.actions.PaymentInvoice;

public class PayableEffect extends ConcreteEffect {

    private Effect effect;
    private PaymentInvoice price;

    public PayableEffect(int id, PaymentInvoice price, Effect effect){
        super(id);
        //TODO
    }
    @Override
    public void apply(EffectVisitor visitor) {
        //TODO

    }

    @Override
    public PaymentInvoice getPrice(){
        //TODO
        return null;
    }
}
