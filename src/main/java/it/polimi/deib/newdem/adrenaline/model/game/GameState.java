package it.polimi.deib.newdem.adrenaline.model.game;

public interface GameState {

    Turn makeTurn();

    boolean isInFrenzy();

    void gameWillEnterGameState(Game game);
}
