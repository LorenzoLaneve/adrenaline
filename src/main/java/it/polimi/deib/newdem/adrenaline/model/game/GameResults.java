package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Final results of an Adrenaline game
 *
 * Simple serializable object for network communication
 */
public class GameResults implements Serializable {

    /**
     * Element of {@code GameResult}
     */
    public class PlayerScoreRecord implements Serializable {

        private PlayerColor player;
        private Integer score;

        /**
         * Creates a new record for the given {@code Player} and score
         * @param player to record result of
         * @param score to record
         */
        public PlayerScoreRecord(PlayerColor player, int score) {
            this.player = player;
            this.score = score;
        }

        /**
         * Retrieves the recorded player
         * @return recorded player
         */
        public PlayerColor getPlayer() {
            return player;
        }

        /**
         * Retrieve recorded score
         * @return recorded score
         */
        public Integer getScore() {
            return score;
        }

    }

    private ArrayList<PlayerScoreRecord> records;

    /**
     * Creates a new empty {@code GameResult} object
     */
    public GameResults() {
        this.records = new ArrayList<>();
    }

    /**
     * Adds a new entry to this {@code GameResult}
     * @param player color to record
     * @param score to record
     */
    public void addPlayer(PlayerColor player, int score) {
        this.records.add(new PlayerScoreRecord(player, score));
    }

    /**
     * Retrieves the stored records
     * @return stored records
     */
    public List<PlayerScoreRecord> getRecords() {
        return new ArrayList<>(records);
    }

}
