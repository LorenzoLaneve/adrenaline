package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;

import java.util.*;

import static it.polimi.deib.newdem.adrenaline.model.game.GameImpl.MAX_PLAYERS_PER_GAME;
import static it.polimi.deib.newdem.adrenaline.model.game.killtrack.KillTrackImpl.MAX_KILLTRACK_SIZE;
import static it.polimi.deib.newdem.adrenaline.model.game.killtrack.KillTrackImpl.MIN_KILLTRACK_SIZE;

public class GameParameters {

    // colorUserOrder to create players from within GameImpl
    private List<ColorUserPair> colorUserOrder;
    private it.polimi.deib.newdem.adrenaline.model.map.Map gameMap;
    private int killTrackInitialLength;
    private int turnTime;
    private List<PlayerColor> playerOrder;

    public static final int KILLTRACK_STARTING_SIZE_DEFAULT = 5;
    public static final int TURN_TIME_DEFAULT_MS = 60000;


    public GameParameters() {
        colorUserOrder = new ArrayList<>(MAX_PLAYERS_PER_GAME);

        gameMap = null; // needs to read from disk, hard to justify a default gameMap
        // will be set explicitly

        killTrackInitialLength = KILLTRACK_STARTING_SIZE_DEFAULT;
        turnTime = TURN_TIME_DEFAULT_MS;

        playerOrder = null; // if not assigned with its setter,
        // will be randomized and stored on the first get.
    }

    public List<ColorUserPair> getColorUserOrder() {
        if (colorUserOrder.isEmpty())
        {
            throw new IllegalStateException();
        }
        return colorUserOrder;
    }

    public Map<PlayerColor, User> getColorUserMap() {
        if(colorUserOrder.isEmpty()) throw new IllegalStateException();
        EnumMap<PlayerColor, User> newMap = new EnumMap<>(PlayerColor.class);
        for(ColorUserPair p : colorUserOrder) {
            newMap.put(p.getColor(),p.getUser());
        }
        return newMap;
    }

    public boolean isColorUserOrderSet() {
        return !colorUserOrder.isEmpty();
    }

    public void setColorUserOrder(List<ColorUserPair> colorUserOrder) {
        this.colorUserOrder = colorUserOrder;

    }

    public it.polimi.deib.newdem.adrenaline.model.map.Map getGameMap() {
        return gameMap;
    }

    public void setGameMap(it.polimi.deib.newdem.adrenaline.model.map.Map gameMap) {
        this.gameMap = gameMap;
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

    public List<PlayerColor> getPlayerOrder() {
        if(null != playerOrder) return playerOrder;
        if(!isColorUserOrderSet()) throw new IllegalStateException();

        playerOrder = new ArrayList<>();

        for(ColorUserPair p : colorUserOrder) {
            playerOrder.add(p.getColor());
        }

        Collections.shuffle(playerOrder);

        return new ArrayList<>(playerOrder);
    }

    public void setPlayerOrder(List<PlayerColor> playerOrder) {
        this.playerOrder = playerOrder;
    }

    public int getTurnTime() {
        return turnTime;
    }

    public void setTurnTime(int turnTime) {
        this.turnTime = turnTime;
    }
}
