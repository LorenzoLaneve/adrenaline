package it.polimi.deib.newdem.adrenaline.model.game;

public interface KillTrack {

    Player getKiller(int killIndex);

    int getTrackLength();

    void registerKill(Player player);

    int getTotalKills();
}
