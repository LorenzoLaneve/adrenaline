package it.polimi.deib.newdem.adrenaline.model.game.killtrack;

import it.polimi.deib.newdem.adrenaline.model.game.Player;

public interface KillTrack {

    Player getKiller(int killIndex);

    int getTrackLength();

    void addKill(Player player, int amount);

    int getTotalKills();

    void setListener(KillTrackListener listener);

    int getScoreForPlayer(Player player);
}
