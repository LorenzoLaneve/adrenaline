package it.polimi.deib.newdem.adrenaline.model.game.killtrack;

import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public interface KillTrack {

    Player getKiller(int killIndex);

    /**
     * @return number of cell on the killtrack.
     */
    int getTrackLength();

    void addKill(Player player, int amount);

    int getTotalKills();

    void setListener(KillTrackListener listener);

    int getScoreForPlayer(Player player);

    /**
     * @return initial player state to be sent to the listener.
     */
    KillTrackData generateKillTrackData();
}
