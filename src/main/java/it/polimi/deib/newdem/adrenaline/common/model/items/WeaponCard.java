package it.polimi.deib.newdem.adrenaline.common.model.items;

import it.polimi.deib.newdem.adrenaline.common.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.common.controller.effects.Effect;

public interface WeaponCard {

    PaymentInvoice getPickupPrice();

    PaymentInvoice getReloadPrice();

    Effect getEffect();

    Weapon makeWeapon();
}
