package it.polimi.deib.newdem.adrenaline.common.mechs.game;


public interface GameChange {

    void update(Game game);

    void revert(Game game);
}
