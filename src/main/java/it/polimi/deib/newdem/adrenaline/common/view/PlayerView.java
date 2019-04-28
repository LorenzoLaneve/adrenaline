package it.polimi.deib.newdem.adrenaline.common.view;

public interface PlayerView {

    /**
     * Notifies the name of the player associated to the view.
     */
    void setName(String name);

    /**
     * Notifies whether the player has the control of the game (i.e. it is their turn).
     */
    void setActive(boolean isActive);

    /**
     * Notifies an update of the score of the player associated to this view.
     */
    void setScore(int score);

    /**
     * Notifies that the player associated to this view acquired a new card.
     * @param cardID The ID of the power up card, according to the loaded deck.
     */
    void addPowerUpCard(int cardID);

    /**
     * Notifies that the player associated to this view acquired a new power up card, without giving further information about it.
     */
    void addHiddenPowerUpCard();

    /**
     * Notifies that the player associated to this view acquired a new weapon card.
     * @param cardID The ID of the power up card, according to the loaded deck.
     */
    void addWeaponCard(int cardID);


}
