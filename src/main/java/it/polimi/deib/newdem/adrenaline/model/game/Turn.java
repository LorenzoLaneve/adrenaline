package it.polimi.deib.newdem.adrenaline.model.game;

public interface Turn {

    Player getActivePlayer();

    void start();

    void turnWillStart();

}
