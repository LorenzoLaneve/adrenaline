package it.polimi.deib.newdem.adrenaline.model.items;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public interface Weapon {

    WeaponCard getCard();

    boolean isLoaded();

    /**
     * Used to change the state of the weapon after it has been used.
     */
    void discharge();

    void reload();

    Player getOwner();
}
