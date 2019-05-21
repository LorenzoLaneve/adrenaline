package it.polimi.deib.newdem.adrenaline.model.game;

import java.util.*;

import static it.polimi.deib.newdem.adrenaline.model.game.KillTrackImpl.MAX_KILLTRACK_SIZE;
import static it.polimi.deib.newdem.adrenaline.model.game.KillTrackImpl.MIN_KILLTRACK_SIZE;

public class GameParameters {

    // eeeeh
    // If Player object are passed to game, not created by it,
    // what does create them?
    // GM? Sure, let's go with that one.

    private EnumMap<PlayerColor, Player> colorPlayerMap;
    private it.polimi.deib.newdem.adrenaline.model.map.Map map;
    private int killTrackInitialLength;
    private List<Player> playerOrder;

    public static final int KILLTRACK_STARTING_SIZE_DEFAULT = 5;


    public GameParameters() {
        colorPlayerMap = new EnumMap<>(PlayerColor.class);

        map = null; // needs to read from disk, hard to justify a default map
        // will be set explicitly

        killTrackInitialLength = KILLTRACK_STARTING_SIZE_DEFAULT;

        playerOrder = null; // if not assigned with its setter,
        // will be randomized and stored on the first get.
    }

    public Map<PlayerColor, Player> getColorPlayerMap() {
        if (colorPlayerMap.entrySet().isEmpty())
        {
            throw new IllegalStateException();
        }
        return colorPlayerMap;
    }

    public boolean isColorPlayerMapSet() {
        return colorPlayerMap.entrySet().isEmpty();
    }

    public void setColorPlayerMap(Map<PlayerColor, Player> colorPlayerMap) {
        this.colorPlayerMap = (EnumMap<PlayerColor, Player>) colorPlayerMap;
    }

    public it.polimi.deib.newdem.adrenaline.model.map.Map getMap() {
        return map;
    }

    public void setMap(it.polimi.deib.newdem.adrenaline.model.map.Map map) {
        this.map = map;
    }

    public int getKillTrackInitialLength() {
        return killTrackInitialLength;
    }

    public void setKillTrackInitialLength(int length) {
        if(length <= MIN_KILLTRACK_SIZE || length > MAX_KILLTRACK_SIZE) {
            throw new IllegalArgumentException();
        }
        this.killTrackInitialLength = length;
    }

    public List<Player> getPlayerOrder() {
        if(null != playerOrder) return playerOrder;
        playerOrder = new ArrayList<>();
        for(Map.Entry<PlayerColor, Player> e: colorPlayerMap.entrySet()) {
            playerOrder.add(e.getValue());
        }
        Collections.shuffle(playerOrder);
        return new ArrayList<>(playerOrder);
    }

    public void setPlayerOrder(List<Player> playerOrder) {
        this.playerOrder = playerOrder;
    }
}
