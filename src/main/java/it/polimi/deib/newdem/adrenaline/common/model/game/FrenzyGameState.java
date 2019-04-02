package it.polimi.deib.newdem.adrenaline.common.model.game;

public class FrenzyGameState implements GameState {
    @Override
    public Turn makeTurn() {
        return null;
    }

    @Override
    public boolean isInFrenzy() {
        return true;
        // TODO implement

    }

    @Override
    public void gameWillEnterGameState(Game game) {
        // TODO implement
    }
}
