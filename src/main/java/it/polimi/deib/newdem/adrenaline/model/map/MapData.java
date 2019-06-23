package it.polimi.deib.newdem.adrenaline.model.map;

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


    public MapData(String mapID) {
        this.mapID = mapID;

        this.drops = new HashMap<>();
    }

    public void setTileData(List<TilePosition> tileData) {
        this.tiles = new ArrayList<>(tileData);
    }

    public void setSpawnPoints(TilePosition red, TilePosition blue, TilePosition yellow) {
        this.redSpawnPoint = red;
        this.blueSpawnPoint = blue;
        this.yellowSpawnPoint = yellow;
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

}