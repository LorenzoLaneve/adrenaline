package it.polimi.deib.newdem.adrenaline.common.mechs.game;

import it.polimi.deib.newdem.adrenaline.common.mechs.map.Map;

import java.util.List;

public class GameImpl implements Game {

    private Map map;

    private RoundRobin turnQueue;

    private KillTrack killTrack;

    private List<Player> players;

    private GameState gameState;


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
