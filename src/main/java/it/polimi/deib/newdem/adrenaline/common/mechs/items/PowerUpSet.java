package it.polimi.deib.newdem.adrenaline.common.mechs.items;

import it.polimi.deib.newdem.adrenaline.common.mechs.actions.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.common.mechs.effects.Effect;

public interface PowerUpSet {

    PaymentInvoice getUsePrice();

    Effect getEffect();

}
