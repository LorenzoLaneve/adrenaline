package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;

public class NullInvoice extends PaymentInvoice {

    public NullInvoice() {
        super(0, 0, 0, 0);
    }

    @Override
    public int getRedAmmos() {
        return 0;
    }

    @Override
    public int getBlueAmmos() {
        return 0;
    }

    @Override
    public int getYellowAmmos() {
        return 0;
    }

    @Override
    public int getAnyAmmos() {
        return 0;
    }
}
