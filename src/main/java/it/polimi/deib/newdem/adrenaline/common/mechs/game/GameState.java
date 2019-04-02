package it.polimi.deib.newdem.adrenaline.common.mechs.game;

public interface GameState {

    Turn makeTurn();

    boolean isInFrenzy();

    public void gameWillEnterGameState(Game game);
}
