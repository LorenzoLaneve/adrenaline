package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.map.Map;

public class MockGame implements Game {
    @Override
    public Map getMap() {
        return null;
    }

    @Override
    public Player getPlayer(PlayerColor color) {
        return null;
    }

    @Override
    public boolean isInFrenzy() {
        return false;
    }

    @Override
    public void makeTurn() {

    }
}
