package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.view.inet.events.TurnFragmentRequest;

/**
 *  A turn in a game of Adrenaline
 */
public interface Turn {

    /**
     * Retrieves the player playing during this turn (the active player)
     * @return active player
     */
    Player getActivePlayer();

    /**
     * Runs this turn
     */
    void execute();

    /**
     * Assign to the turn the source of user input.
     * @param turnDataSource source of user input.
     */
    void bindDataSource(TurnDataSource turnDataSource);

    /**
     * Retrieves the source of additional information for any {@code Effect}, {@code Action} or {@code Interaction}
     * during this turn
     *
     * @return source of new data
     */
    TurnDataSource getDataSource();

    /**
     * Sets whether or not to run the closing actions for a turn,
     * that is powerup usage and reload
     *
     * @param flag should closing actions be tun
     */
    void setRunClosingActions(boolean flag);

    /**
     * Sets whether or not to run the closing actions of powerup usage
     *
     * @param flag should closing powerup usage action be tun
     */
    void setAllowClosingPowerUps(boolean flag);
}
