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

    /**
     * Notify when a player receives a powerup card
     * @param player receiving the card
     * @param powerUpCard card received
     */
    void playerDidReceivePowerUpCard(Player player, PowerUpCard powerUpCard);

    /**
     * Notify when a player discards a powerup card
     * @param player discarding a poerup card
     * @param powerUpCard discarded powerup card
     */
    void playerDidDiscardPowerUpCard(Player player, PowerUpCard powerUpCard);

    /**
     * Notify when a player receives a weapon card
     * @param player receiving a weapon card
     * @param weaponCard received card
     */
    void playerDidReceiveWeaponCard(Player player, WeaponCard weaponCard);

    /**
     * Notify when a player discards a weapon card
     * @param player discarding a weapon card
     * @param weaponCard discarded card
     */
    void playerDidDiscardWeaponCard(Player player, WeaponCard weaponCard);

    /**
     * Notify when a player's score is updated
     * @param player whose score has changed
     * @param newScore new score of the given player
     */
    void playerDidUpdateScore(Player player, int newScore);

    /**
     * Notify when a player receives some ammos
     * @param player receiving ammos
     * @param ammos received
     */
    void playerDidReceiveAmmos(Player player, AmmoSet ammos);

    /**
     * Notify when a player discards some ammos
     * @param player discarding ammos
     * @param ammos discarded
     */
    void playerDidDiscardAmmos(Player player, AmmoSet ammos);

    /**
     * Notify when a player unloads (discharges) a weapon
     * @param player discharging a weapon
     * @param weaponCard discharged
     */
    void playerDidUnloadWeaponCard(Player player, WeaponCard weaponCard);

    /**
     * Notify when a player reloads a weapon
     * @param player reloading a weapon
     * @param weaponCard reloaded card
     */
    void playerDidReloadWeaponCard(Player player, WeaponCard weaponCard);

}
