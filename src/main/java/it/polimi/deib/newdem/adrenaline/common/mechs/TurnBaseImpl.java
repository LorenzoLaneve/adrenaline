package it.polimi.deib.newdem.adrenaline.common.mechs;

public abstract class TurnBaseImpl implements Turn {

    private Player activePlayer;

    protected abstract void turnDidStart();

    protected abstract void turnDidFinish();

    @Override
    public Player getActivePlayer() {
        // TODO implement
        return null;
    }

    @Override
    public void start() {
        // TODO implement
        // or leave as abstract
    }
}
