package it.polimi.deib.newdem.adrenaline.model.items;

import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public interface WeaponCard extends Card{

    PaymentInvoice getPickupPrice();

    PaymentInvoice getReloadPrice();

    Effect getEffect();

    /**
     * Contructs Weapon from WeaponCards info and assign it to the player
     * @param player the player to which the weapon will belong.
     * @return the Weapon object corresponding to this WeaponCard.
     */
    Weapon makeWeapon(Player player);
}
