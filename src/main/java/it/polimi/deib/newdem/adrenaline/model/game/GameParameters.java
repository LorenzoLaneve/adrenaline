package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.controller.Config;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
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
    private int turnTimeSeconds;
    private List<PlayerColor> playerOrder;
    private int minPlayers;
    private int maxPlayers;

    public static final int KILLTRACK_STARTING_SIZE_DEFAULT = 5;
    public static final int TURN_TIME_S_DEFAULT = 120;
    public static final int MAX_PLAYERS_DEFAULT = 5;
    public static final int MIN_PLAYERS_DEFAULT = 3;


    public static GameParameters fromConfig(Config config) {
        GameParameters gp = new GameParameters();
        gp.turnTimeSeconds = config.getTurnTime();
        gp.minPlayers = config.getMinPlayers();
        gp.maxPlayers = config.getMaxPlayers();

        return gp;
    }

    public GameParameters() {
        colorUserOrder = new ArrayList<>(MAX_PLAYERS_PER_GAME);

        gameMap = null; // needs to read from disk, hard to justify a default gameMap
        // will be set explicitly

        killTrackInitialLength = KILLTRACK_STARTING_SIZE_DEFAULT;
        turnTimeSeconds = TURN_TIME_S_DEFAULT;
        minPlayers = MIN_PLAYERS_DEFAULT;
        maxPlayers = MAX_PLAYERS_DEFAULT;

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
        if(length < MIN_KILLTRACK_SIZE || length > MAX_KILLTRACK_SIZE) {
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

    /**
     * Returns the turn time in seconds
     * @return turn time in seconds
     */
    public int getTurnTime() {
        return turnTimeSeconds;
    }

    public void setTurnTime(int turnTime) {
        this.turnTimeSeconds = turnTime;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
}
