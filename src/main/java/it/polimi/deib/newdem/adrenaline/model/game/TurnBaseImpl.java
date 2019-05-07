package it.polimi.deib.newdem.adrenaline.model.game;

public abstract class TurnBaseImpl implements Turn {

    private Player activePlayer;

    public TurnBaseImpl(Player activePlayer) {
        this.activePlayer = activePlayer;
        // TODO implement
    }

    @Override
    public Player getActivePlayer() {
        // TODO implement
        return activePlayer;
    }

    @Override
    public void start() {
        // TODO implement
    }
}
