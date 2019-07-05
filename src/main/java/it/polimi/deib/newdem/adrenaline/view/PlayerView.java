package it.polimi.deib.newdem.adrenaline.view;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerData;

/**
 * View that observes a Player object in the model.
 */
public interface PlayerView {

    /**
     * Restores the view to the given data sent by the model.
     */
    void setPlayerData(PlayerData data);

    /**
     * Notifies the name of the player associated to the view.
     */
    void setName(String name);

    /**
     * Notifies an update of the score of the player associated to this view.
     */
    void setScore(int score);

    /**
     * Notifies that the player associated to this view acquired a new power up card.
     * @param cardID The ID of the power up card, according to the loaded deck, or a negative number
     *               if the power up card is hidden.
     */
    void addPowerUpCard(int cardID);

    /**
     * Notifies that the player associated to this view discarded the given power up card.
     * @param cardID The ID of the power up card, according to the loaded deck, or a negative number
     *               if the power up card is hidden.
     */
    void removePowerUpCard(int cardID);

    /**
     * Notifies that the player associated to thie view acquired a new weapon card.
     * @param cardID The ID of the weapon card, according to the loaded deck.
     */
    void addWeaponCard(int cardID);

    /**
     * Notifies that the player associated to thie view discarded the given weapon card.
     * @param cardID The ID of the weapon card, according to the loaded deck.
     */
    void removeWeaponCard(int cardID);

    /**
     * Notifies that the player associated to this view received the given amounts of ammos.
     */
    void addAmmoSet(int yellowAmount, int redAmount, int blueAmount);

    /**
     * Notifies that the player associated to this view lost the given amounts of ammos.
     */
    void removeAmmoSet(int yellowAmount, int redAmount, int blueAmount);

    /**
     * Notifies that the player associated to this view reloaded the given weapon card.
     */
    void reloadWeaponCard(int cardID);

    /**
     * Notifies that the player associated to this view used the given weapon card.
     */
    void unloadWeaponCard(int cardID);

}
