package it.polimi.deib.newdem.adrenaline.view;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;

public interface PlayerView {

    /**
     * Notifies the name of the player associated to the view.
     */
    void setName(String name);

    /**
     * Notifies that the player acquired the control of the game (i.e. it is their turn).
     */
    void takeControl();

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

    void takeDamage(int dmgAmount, PlayerColor playerColor);

    void takeMark(int markAmount, PlayerColor playerColor );

    void addAmmoSet(int yellowAmount, int redAmount, int blueAmount);

    void removeAmmoSet(int yellowAmount, int redAmount, int blueAmount);

}
