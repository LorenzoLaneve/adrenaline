package it.polimi.deib.newdem.adrenaline.common.mechs.game;

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
