package it.polimi.deib.newdem.adrenaline.model.game;


public interface GameChange {

    void update(Game game);

    void revert(Game game);
}
