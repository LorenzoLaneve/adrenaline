package it.polimi.deib.newdem.adrenaline.model.game;

public interface GameState {

    /**
     * Returns whether the game state is frenzy mode.
     */
    boolean isInFrenzy();

    /**
     * Method that is called when the given game switches to this game state.
     */
    void gameWillEnterGameState(Game game);
}
