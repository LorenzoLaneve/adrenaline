package it.polimi.deib.newdem.adrenaline.model.items;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

/**
 * Weapon currently in a {@code Player}'s inventory.
 *
 */
public interface Weapon {

    /**
     * Retrieves this card's id
     * @return this card's id
     */
    WeaponCard getCard();

    /**
     * Checks that this weapon is loaded
     * @return is this weapon loaded
     */
    boolean isLoaded();

    /**
     * Changes the state of the weapon after it has been used.
     */
    void discharge();

    /**
     * Reloads this weapon
     */
    void reload();

    /**
     * Retrieves this weapon's owner
     * @return owner
     */
    Player getOwner();
}
