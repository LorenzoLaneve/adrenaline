package it.polimi.deib.newdem.adrenaline.model.game.player;

import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;

/**
 * Interface for objects that listen
 */
public interface PlayerListener {

    /**
     * Notify when a player is initialized by Game.
     * @param data initial state of the player.
     */
    void playerDidInit(PlayerData data);

    void playerDidReceivePowerUpCard(Player player, PowerUpCard powerUpCard);

    void playerDidDiscardPowerUpCard(Player player, PowerUpCard powerUpCard);

    void playerDidReceiveWeaponCard(Player player, WeaponCard weaponCard);

    void playerDidDiscardWeaponCard(Player player, WeaponCard weaponCard);

    void playerDidUpdateScore(Player player, int newScore);

    void playerDidReceiveAmmos(Player player, AmmoSet ammos);

    void playerDidDiscardAmmos(Player player, AmmoSet ammos);

    void playerDidUnloadWeaponCard(Player player, WeaponCard weaponCard);

    void playerDidReloadWeaponCard(Player player, WeaponCard weaponCard);

}
