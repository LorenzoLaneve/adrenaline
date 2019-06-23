package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;

import java.util.*;

public class ConcreteMap implements Map {

    private List<Room> rooms;
    private Tile[][] matrixMap;
    private MapListener mapListener;

    /**Creates a new {@code ConcreteMap} using the matrixMap and list of rooms provided by the MapBuilder.
     *
     * @param matrixMap array of arrays of tiles.
     * @param rooms list of rooms.
     */
    public ConcreteMap(Tile[][] matrixMap, List<Room> rooms) {
        this.matrixMap = matrixMap;
        this.rooms = rooms;
    }

    /**Returns the rooms in the map.
     *
     * @return the list of rooms belonging to the map.
     */
    @Override
    public List<Room> getRooms() {

        return new ArrayList<>(rooms);
    }

    /**Returns the tile in tilePosition in Map.
     *
     * @param tilePosition the position of the tile to return.
     * @return the tile in tilePosition in Map.
     */
    @Override
    public Tile getTile(TilePosition tilePosition) {
        Tile foundTile = null;

        int x = tilePosition.getX();
        int y = tilePosition.getY();

        foundTile = matrixMap[x][y];

        return foundTile;
    }

    /**Used to set the map of the rooms in rooms since they are created
     * before the Map to which they belong is initialised. It should always be called right after the constructor.
     *
     */
    @Override
    public void bindRooms() {
        for(Room room : rooms){
            room.setMap(this);
        }
    }

    @Override
    public void movePlayer(Player player, Tile destination) {
        Tile source = player.getTile();

        if(source == null){
            destination.addPlayer(player);
            player.setTile(destination);
            if (mapListener != null) {
                mapListener.playerDidSpawn(player, destination);
            }
        }
        else{
            source.removePlayer(player);
            destination.addPlayer(player);
            player.setTile(destination);
            if (mapListener != null) {
                mapListener.playerDidMove(player, source, destination);
            }
        }
    }

    @Override
    public void setListener(MapListener listener) {
        this.mapListener = listener;
        if(mapListener != null){

            /*
            List<Tile> tileList = new ArrayList<>();
            List<Tile> spawnPointTileList = new ArrayList<>();

            for(Tile[] tileRow: matrixMap){
                for (Tile tile : tileRow){
                    if(tile!= null){
                        tileList.add(tile);
                        if(tile.hasSpawnPoint()){
                            spawnPointTileList.add(tile);
                        }
                    }
                }
            }*/

            // TODO data creation
            MapData data = new MapData("map ID here");

            mapListener.mapDidRestoreData(data);

        }
    }

    @Override
    public MapListener getListener() {
        return mapListener;
    }

    @Override
    public void removePlayer(Player player) {
        Tile tile = player.getTile();

        tile.removePlayer(player);
        mapListener.playerDidLeaveMap(player);
    }


    @Override
    public List<Tile> selectTiles(TileSelector selector) {
        List<Tile> selectedTiles = new ArrayList<>();

        for (int i = 0; i < matrixMap.length && matrixMap[i] != null; i++) {

            for (int j = 0; j < matrixMap.length && matrixMap[i][j] != null; j++) {
                Tile tile = matrixMap[i][j];
                if (selector.isSelectable(this, tile)) {
                    selectedTiles.add(tile);
                }
            }
        }

        return selectedTiles;
    }

    @Override
    public Tile getSpawnPointFromColor(AmmoColor ammoColor) {

        Tile returnTile = null;

        for (Tile tile: getAllTiles()){
            if(tile.hasSpawnPoint()){
                SpawnPointTile spawnPointTile = (SpawnPointTile) tile;
                if (ammoColor == spawnPointTile.getSpawnPointColor()){
                    returnTile = spawnPointTile;
                }
            }
        }
        return returnTile;
    }

    @Override
    public List<Tile> getAllTiles() {

        List<Tile> tiles = new ArrayList<>();

        for (int i = 0; i < matrixMap.length && matrixMap[i] != null; i++) {

            for (int j = 0; j < matrixMap.length && matrixMap[i][j] != null; j++) {
                Tile tile = matrixMap[i][j];
                tiles.add(tile);
            }
        }

        return tiles;
    }

    @Override
    public int getDistance(Tile source, Tile destination) {

        List<Tile> toVisit;
        List<Tile> visiting = new ArrayList<>();
        List<Tile> visited = new ArrayList<>();
        Queue<Tile> queue = new LinkedList<>();

        HashMap<Tile, Integer> distDict = new HashMap<>();

        toVisit = getAllTiles();

        visiting.add(source);
        distDict.put(source,0);

        queue.add(source);

        while (queue.peek()!= null){
            Tile curr = queue.remove();
            for (Tile adTile:curr.getAdjacentTiles()){
                if(!(visited.contains(adTile)) && !(visiting.contains(adTile))){
                    visiting.add(adTile);
                    toVisit.remove(adTile);

                    distDict.put(adTile,distDict.get(curr) + 1);
                    queue.add(adTile);
                }
            }
            visiting.remove(curr);
            visited.add(curr);
        }
        return distDict.get(destination);
    }
}
