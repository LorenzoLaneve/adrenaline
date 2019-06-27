package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.game.killtrack.KillTrack;
import it.polimi.deib.newdem.adrenaline.model.game.killtrack.KillTrackData;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerData;
import it.polimi.deib.newdem.adrenaline.model.items.Deck;
import it.polimi.deib.newdem.adrenaline.model.items.DeckData;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.MapData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameData implements Serializable {
    /**
     * Data representation of the current game state. Intended for communication with the views,
     * this does not include non-visible information (e.g. who is the active player)
     */

    public enum DropType {
        RED_AMMO,
        BLUE_AMMO,
        YELLOW_AMMO,
        POWER_UP
    }

    public class UserColorPair implements Serializable {

        private String username;
        private PlayerColor color;
        private boolean isOnline;

        UserColorPair(String username, PlayerColor color, boolean isOnline) {
            this.username = username;
            this.color = color;
            this.isOnline = isOnline;
        }

        public String getUsername() {
            return username;
        }

        public PlayerColor getColor() {
            return color;
        }

        public boolean isOnline() { return isOnline; }

    }

    private static final String EMPTY_USERS_MSG = "No users in this GameData";
    private static final String NOT_FINALIZED_MSG = "GameData is not finalized yet";

    private ArrayList<UserColorPair> users;
    private MapData mapData;
    private KillTrackData killTrackData;
    private List<PlayerData> playerDataList;
    private DeckData powerUpDeckData;
    private DeckData dropDeckData;
    private DeckData weaponDeckData;
    private boolean finalized;

    GameData() {
        finalized = false;
    }

    public void setFinalized() {
        if(finalized) throw new IllegalStateException();
        finalized = true;
    }

    void addUser(String name, PlayerColor color, boolean isOnline) {
        this.users = new ArrayList<>();
        this.users.add(new UserColorPair(name, color, isOnline));
    }

    void addPlayer(Player player){
        playerDataList = new ArrayList<>();
        playerDataList.add(player.generatePlayerData());
    }

    void addMap(Map map) {
        this.mapData = new MapData(map.getMapID());
        mapData = map.generateMapData();
    }

    void addKillTrack(KillTrack killTrack) {
        this.killTrackData = killTrack.generateKillTrackData();
    }

    void addPowerUpDeck(Deck deck) {
        powerUpDeckData = deck.generateDeckData();
    }

    void addWeaponCardDeck(Deck deck) {
        weaponDeckData = deck.generateDeckData();
    }

    void addDropDeck(Deck deck) {
        dropDeckData = deck.generateDeckData();
    }


    /**
     * Returns a list of players, in the order they should play and starting with the first player.
     */
    public List<UserColorPair> getPlayers() {
        if(null == users) throw new IllegalStateException(EMPTY_USERS_MSG);
        return new ArrayList<>(users);
    }

    public MapData getMapData() {
        if(!finalized) throw new IllegalStateException();
        return mapData;
    }

    public KillTrackData getKillTrackData() {
        if(!finalized) throw new IllegalStateException(NOT_FINALIZED_MSG);
        return killTrackData;
    }

    public List<PlayerData> getPlayersData() {
        if(!finalized) throw new IllegalStateException(NOT_FINALIZED_MSG);
        return playerDataList;
    }

    public DeckData getPowerUpDeckData() {
        if(!finalized) throw new IllegalStateException(NOT_FINALIZED_MSG);
        return powerUpDeckData;
    }

    public DeckData getDropDeckData() {
        if(!finalized) throw new IllegalStateException(NOT_FINALIZED_MSG);
        return dropDeckData;
    }

    public DeckData getWeaponDeckData() {
        if(!finalized) throw new IllegalStateException(NOT_FINALIZED_MSG);
        return weaponDeckData;
    }

    public boolean isFinalized() {
        return finalized;
    }
}
