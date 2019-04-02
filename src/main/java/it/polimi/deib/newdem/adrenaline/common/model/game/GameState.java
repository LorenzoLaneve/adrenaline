package it.polimi.deib.newdem.adrenaline.common.model.game;

public interface GameState {

    Turn makeTurn();

    boolean isInFrenzy();

    public void gameWillEnterGameState(Game game);
}
