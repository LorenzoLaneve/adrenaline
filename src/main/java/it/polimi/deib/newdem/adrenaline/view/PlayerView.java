package it.polimi.deib.newdem.adrenaline.view;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerData;

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
     * Notifies that the player associated to this view acquired a new card.
     * @param cardID The ID of the power up card, according to the loaded deck.
     * @see it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard for HIDDEN constant.
     */
    void addPowerUpCard(int cardID);

    /**
     * Notifies that the player associated to this view acquired a new weapon card.
     * @param cardID The ID of the power up card, according to the loaded deck.
     */
    void removePowerUpCard(int cardID);

    void addWeaponCard(int cardID);

    void removeWeaponCard(int cardID);

    void addAmmoSet(int yellowAmount, int redAmount, int blueAmount);

    void removeAmmoSet(int yellowAmount, int redAmount, int blueAmount);

    void reloadWeaponCard(int cardID);

    void unloadWeaponCard(int cardID);

}
