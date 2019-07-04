package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameResults implements Serializable {

    public class PlayerScoreRecord implements Serializable {

        private PlayerColor player;
        private Integer score;

        public PlayerScoreRecord(PlayerColor player, int score) {
            this.player = player;
            this.score = score;
        }

        public PlayerColor getPlayer() {
            return player;
        }

        public Integer getScore() {
            return score;
        }

    }

    private ArrayList<PlayerScoreRecord> records;

    public GameResults() {
        this.records = new ArrayList<>();
    }

    public void addPlayer(PlayerColor player, int score) {
        this.records.add(new PlayerScoreRecord(player, score));
    }

    public List<PlayerScoreRecord> getRecords() {
        return new ArrayList<>(records);
    }

}
