package it.polimi.deib.newdem.adrenaline.common.model.game;

import it.polimi.deib.newdem.adrenaline.common.model.map.Map;

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

    @Override
    public void makeTurn() {
        // TODO implement
        gameState.makeTurn();
    }

    public void changeState(GameState state) {
        // TODO implement
    }

}
