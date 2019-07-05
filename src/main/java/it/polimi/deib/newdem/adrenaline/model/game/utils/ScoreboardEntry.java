package it.polimi.deib.newdem.adrenaline.model.game.utils;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

/**
 * Element of a {@code Scoreboard}
 *
 * @see Scoreboard
 */
public class ScoreboardEntry {
    private final Player p;
    private final int totalScore;
    private final int earliestShot;

    /**
     * Creates a new scoreboard entry for the given player with the relevant gameplay information
     * @param p player to rank
     * @param totalScore total amount of counters by this
     * @param earliestShot index of this player's earliest shot
     */
    public ScoreboardEntry(Player p, int totalScore, int earliestShot) {
        this.p = p;
        this.totalScore = totalScore;
        this.earliestShot = earliestShot;
    }

    /**
     * Retrieves this entry's total score
     * @return total score
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * Retrieves this entry's earliest shot
     * @return earliest shot
     */
    public int getEarliestShot() {
        return earliestShot;
    }

    /**
     * Retrieves this entry's player
     * @return player
     */
    public Player getPlayer() {
        return p;
    }
}
