package it.polimi.deib.newdem.adrenaline.common.mechs.items;

import it.polimi.deib.newdem.adrenaline.common.mechs.actions.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.common.mechs.effects.Effect;

public interface WeaponCard {

    PaymentInvoice getPickupPrice();

    PaymentInvoice getReloadPrice();

    Effect getEffect();

    Weapon makeWeapon();
}
