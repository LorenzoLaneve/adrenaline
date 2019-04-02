package it.polimi.deib.newdem.adrenaline.common.model.items;

import it.polimi.deib.newdem.adrenaline.common.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.common.controller.effects.Effect;

public interface PowerUpCard {

    PaymentInvoice getUsePrice();

    Effect getEffect();
}
