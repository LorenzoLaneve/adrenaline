package it.polimi.deib.newdem.adrenaline.view;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;

public interface DamageBoardView {

    /**
     * Notifies that the player associated to this damage board view has taken damage.
     * @param damageAmount the amount of damage taken by the player.
     * @param markAmount the number of marks taken by the player.
     * @param dealer the player that dealt this damage.
     */
    void registerDamage(int damageAmount, int markAmount, PlayerColor dealer);

    /**
     * Notifies that the marks associated to the player with the given color has been converted to damage.
     */
    void convertMarks(PlayerColor dealer);

}
