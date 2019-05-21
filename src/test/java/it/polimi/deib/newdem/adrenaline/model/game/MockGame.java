package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.map.Map;

public class MockGame implements Game {
    @Override
    public boolean shouldGoFrenzy() {
        return false;
    }

    @Override
    public Turn getNextTurn() {
        return null;
    }

    @Override
    public Map getMap() {
        return null;
    }

    @Override
    public Player getPlayerFromColor(PlayerColor color) {
        return null;
    }

    @Override
    public boolean isInFrenzy() {
        return false;
    }

    @Override
    public void concludeTurn(Turn turn) {
    }

}
