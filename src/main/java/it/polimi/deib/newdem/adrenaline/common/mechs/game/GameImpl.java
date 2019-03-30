package it.polimi.deib.newdem.adrenaline.common.mechs.game;

import it.polimi.deib.newdem.adrenaline.common.mechs.map.Map;

public class GameImpl implements Game {

    private Map map;

    public GameImpl(Map map) {
        this.map = map;
    }

    @Override
    public Map getMap() {
        return null;
        // TODO implement

    }

    @Override
    public Player getPlayer(PlayerColor color) {
        return null;
        // TODO implement

    }

    @Override
    public boolean isInFrenzy() {
        return false;
        // TODO implement
    }
}
