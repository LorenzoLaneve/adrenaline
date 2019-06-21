package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;

import java.io.Serializable;
import java.util.ArrayList;

public class GameData implements Serializable {

    public enum DropType {
        RED_AMMO,
        BLUE_AMMO,
        YELLOW_AMMO,
        POWER_UP
    }

    private class UserColorPair implements Serializable {

        public String userName;
        public PlayerColor color;

        public UserColorPair(String userName, PlayerColor color) {
            this.userName = userName;
            this.color = color;
        }

    }

    private class TileDropPair implements Serializable {

        public TilePosition tile;
        public DropType drop1;
        public DropType drop2;
        public DropType drop3;

        public TileDropPair(TilePosition tile, DropType drop1, DropType drop2, DropType drop3) {
            this.tile = tile;
            this.drop1 = drop1;
            this.drop2 = drop2;
            this.drop3 = drop3;
        }

    }

    private class PlayerTilePair implements Serializable {

        public TilePosition tile;
        public PlayerColor player;

        public PlayerTilePair(TilePosition tile, PlayerColor player) {
            this.tile = tile;
            this.player = player;
        }

    }

    private ArrayList<UserColorPair> users;

    private ArrayList<TileDropPair> drops;

    private ArrayList<PlayerTilePair> playerLocations;

    private PlayerColor nextTurnPlayer;

    private String mapID;


    GameData() {
        this.users = new ArrayList<>();
        this.drops = new ArrayList<>();
        this.playerLocations = new ArrayList<>();

        this.mapID = null;

        this.nextTurnPlayer = null;
    }

    public void addPlayer(String name, PlayerColor color) {
        this.users.add(new UserColorPair(name, color));
    }

    public void setMapID(String mapID) {
        this.mapID = mapID;
    }

    public void addDrop(TilePosition tile, DropType drop1, DropType drop2, DropType drop3) {
        this.drops.add(new TileDropPair(tile, drop1, drop2, drop3));
    }

    public void setPlayerLocation(PlayerColor player, TilePosition tile) {
        this.playerLocations.add(new PlayerTilePair(tile, player));
    }

    public void setNextTurnPlayer(PlayerColor player) {
        this.nextTurnPlayer = player;
    }



}
