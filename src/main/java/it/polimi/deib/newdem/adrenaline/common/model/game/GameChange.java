package it.polimi.deib.newdem.adrenaline.common.model.game;


public interface GameChange {

    void update(Game game);

    void revert(Game game);
}
