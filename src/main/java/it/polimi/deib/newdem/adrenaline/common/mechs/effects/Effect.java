package it.polimi.deib.newdem.adrenaline.common.mechs.effects;

import it.polimi.deib.newdem.adrenaline.common.mechs.actions.Action;
import it.polimi.deib.newdem.adrenaline.common.mechs.actions.PaymentInvoice;

interface Effect {

    int getEffectId();

    PaymentInvoice getPrice();

    void apply(Action action);

}
