package it.polimi.deib.newdem.adrenaline.view;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;

public interface DamageBoardView {

    /**
     * Notifies that the player associated to this damage board view has taken damage.
     * @param damageAmount the amount of damage taken by the player (positive only).
     * @param markAmount the number of marks taken by the player (positive or negative).
     * @param dealer the player that dealt this damage.
     */
    void registerDamage(int damageAmount, int markAmount, PlayerColor dealer);

    /**
     * Notifies that the damage board undid the last damage.
     */
    void popDamage();

    /**
     * Notifies that the damage board switched to frenzy mode.
     */
    void goFrenzy();

    /**
     * Notifies that the damage board was cleared.
     */
    void clearBoard();

}
