package it.polimi.deib.newdem.adrenaline.common.model.game;

public interface Turn {

    Player getActivePlayer();

    void start();

    void turnWillStart();

}
