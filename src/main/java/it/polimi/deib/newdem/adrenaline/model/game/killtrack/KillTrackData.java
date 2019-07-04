package it.polimi.deib.newdem.adrenaline.model.game.killtrack;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class KillTrackData implements Serializable {

    public static class KillData implements Serializable {

        private final PlayerColor killer;
        private final int amount;

        KillData(PlayerColor killer, int amount) {
            this.killer = killer;
            this.amount = amount;
        }

        public PlayerColor getKiller() {
            return killer;
        }

        public int getAmount() {
            return amount;
        }
    }

    private ArrayList<KillData> kills;
    private final int initialLength;

    KillTrackData(KillTrack killTrack) {
        initialLength = killTrack.getTrackLength();
        kills = new ArrayList<>();
    }

    void addKill(KillData kill){
        kills.add(kill);
    }

    public List<KillData> getKills() {
        return new ArrayList<>(kills);
    }

    public int getInitialLength() {
        return initialLength;
    }

}
