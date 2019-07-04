package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public interface DamageBoardListener {

    /**
     * Notifies that the listened damage board has taken damage.
     * @param damageAmount the amount of damage taken
     * @param markAmount the amount of marks assigned
     * @param dealer the player that dealt this damage/assigned these marks.
     */
    void boardDidTakeDamage(int damageAmount, int markAmount, Player dealer);

    /**
     * Notifies that the damage board removed the last damage.
     */
    void boardDidPopDamage();

    /**
     * Notifies that the damage board has switched to frenzy mode.
     */
    void boardDidSwitchToFrenzy();

    /**
     * Notifies that a new damage board was added
     */
    void boardDidClear();

}
