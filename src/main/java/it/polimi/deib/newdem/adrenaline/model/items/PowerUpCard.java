package it.polimi.deib.newdem.adrenaline.model.items;

import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;

public interface PowerUpCard {

    int HIDDEN = -1;


    PaymentInvoice getUsePrice();

    Effect getEffect();
}
