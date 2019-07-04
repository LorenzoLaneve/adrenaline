package it.polimi.deib.newdem.adrenaline.model.game.killtrack;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

/**
 * Object that represents a game kill track, keeping all the information
 * about kills made by the players in an Adrenaline game.
 */
public interface KillTrack {

    /**
     * Returns the player that made the {@code killIndex}th kill.
     */
    Player getKiller(int killIndex);

    /**
     * @return number of cell on the killtrack.
     */
    int getTrackLength();

    /**
     * Registers a kill made by the given player to the kill track.
     * @param amount 1 for normal kill, 2 for overkill, as stated by Adrenaline's rules.
     */
    void addKill(Player player, int amount);

    /**
     * Returns the number of total kills registered in the kill track.
     */
    int getTotalKills();

    void setListener(KillTrackListener listener);

    /**
     * Returns the score assigned to a player with kills.
     */
    int getScoreForPlayer(Player player);

    /**
     * @return initial player state to be sent to the listener.
     */
    KillTrackData generateKillTrackData();
}
