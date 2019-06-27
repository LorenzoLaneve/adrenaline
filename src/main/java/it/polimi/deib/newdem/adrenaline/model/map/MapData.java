package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.items.DropInstance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapData implements Serializable {

    private String mapID;

    private ArrayList<TilePosition> tiles;

    private TilePosition redSpawnPoint;

    private TilePosition blueSpawnPoint;

    private TilePosition yellowSpawnPoint;

    private HashMap<TilePosition, DropInstance> drops;

    private ArrayList<PlayerTilePair> playerLocations;

    private List<Integer> redWeaponSet;
    private List<Integer> blueWeaponSet;
    private List<Integer> yellowWeaponSet;

    private class PlayerTilePair implements Serializable {

        private TilePosition tile;
        private PlayerColor player;

        PlayerTilePair(TilePosition tile, PlayerColor player) {
            this.tile = tile;
            this.player = player;
        }

        public TilePosition getTile() {
            return tile;
        }

        public PlayerColor getPlayer() {
            return player;
        }

    }

    public MapData(String mapID) {
        this.mapID = mapID;

        this.drops = new HashMap<>();

        playerLocations = new ArrayList<>();
    }

    public void setTileData(List<TilePosition> tileData) {
        this.tiles = new ArrayList<>(tileData);
    }

    public void setSpawnPoints(TilePosition red, TilePosition blue, TilePosition yellow) {
        this.redSpawnPoint = red;
        this.blueSpawnPoint = blue;
        this.yellowSpawnPoint = yellow;
    }

    public void setWeaponSets(List<Integer> redWeaponSet, List<Integer> blueWeaponSet, List<Integer> yellowWeaponSet ){
        this.redWeaponSet = new ArrayList<>(redWeaponSet);
        this.blueWeaponSet = new ArrayList<>(blueWeaponSet);
        this.yellowWeaponSet = new ArrayList<>(yellowWeaponSet);
    }


    public void addDrops(TilePosition tile, DropInstance drop) {
        drops.put(tile, drop);
    }


    public String getMapID() {
        return mapID;
    }

    public List<TilePosition> getTiles() {
        return new ArrayList<>(tiles);
    }

    public TilePosition getRedSpawnLocation() {
        return redSpawnPoint;
    }

    public TilePosition getBlueSpawnPoint() {
        return blueSpawnPoint;
    }

    public TilePosition getYellowSpawnPoint() {
        return yellowSpawnPoint;
    }

    public DropInstance getDropInTile(TilePosition tile) {
        return drops.get(tile);
    }

    public List<Integer> getRedWeaponSet() {
        return redWeaponSet;
    }

    public List<Integer> getBlueWeaponSet() {
        return blueWeaponSet;
    }

    public List<Integer> getYellowWeaponSet() {
        return yellowWeaponSet;
    }

    public void setPlayerLocation(PlayerColor player, TilePosition tile) {
        this.playerLocations.add(new PlayerTilePair(tile, player));
    }

}