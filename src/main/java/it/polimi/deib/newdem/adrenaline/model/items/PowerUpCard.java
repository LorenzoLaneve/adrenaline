package it.polimi.deib.newdem.adrenaline.model.items;

import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;

public interface PowerUpCard {

    PaymentInvoice getUsePrice();

    Effect getEffect();
}
