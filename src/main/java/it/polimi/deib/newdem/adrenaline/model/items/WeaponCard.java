package it.polimi.deib.newdem.adrenaline.model.items;

import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;

public interface WeaponCard extends Card{

    PaymentInvoice getPickupPrice();

    PaymentInvoice getReloadPrice();

    Effect getEffect();

    Weapon makeWeapon();
}
