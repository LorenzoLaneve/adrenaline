package it.polimi.deib.newdem.adrenaline.model.game.killtrack;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Serializable object containing a snapshot of the state of a KillTrack object.
 *
 */
public class KillTrackData implements Serializable {

    /**
     * Serializable record used in the outer class to represent kills.
     */
    public static class KillData implements Serializable {

        private final PlayerColor killer;
        private final int amount;

        KillData(PlayerColor killer, int amount) {
            this.killer = killer;
            this.amount = amount;
        }

        /**
         * Retrieves the killer of this cell
         * @return killer
         */
        public PlayerColor getKiller() {
            return killer;
        }

        /**
         * Retrieves the amount of counters on this cell
         * @return counters
         */
        public int getAmount() {
            return amount;
        }
    }

    private ArrayList<KillData> kills;
    private final int initialLength;

    /**
     * Creates a new empty {@code KillTrackData} of the same length as the given {@code KillTrack}.
     *
     * This does not fill in the data about kills.
     *
     * @param killTrack to take a snapshot of
     */
    KillTrackData(KillTrack killTrack) {
        initialLength = killTrack.getTrackLength();
        kills = new ArrayList<>();
    }

    /**
     * Adds a {@code KillData} cell to this {@code KillTrackData}
     *
     * @param kill cell to add
     */
    void addKill(KillData kill) {
        kills.add(kill);
    }

    /**
     * Retrieves a copy of the kills recorded on this {@code KilltrackData}
     *
     * @return kills recorded
     */
    public List<KillData> getKills() {
        return new ArrayList<>(kills);
    }

    /**
     * Retrieves this {@code KillTrackData}'s initial lenght
     *
     * @return initial length
     */
    public int getInitialLength() {
        return initialLength;
    }

}
