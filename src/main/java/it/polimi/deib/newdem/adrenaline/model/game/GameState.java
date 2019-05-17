package it.polimi.deib.newdem.adrenaline.model.game;

public interface GameState {

    boolean isInFrenzy();

    void gameWillEnterGameState(Game game);
}
