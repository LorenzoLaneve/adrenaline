package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.model.game.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
            mapListener.playerDidSpawn(player, destination);
        }
        else{
            source.removePlayer(player);
            destination.addPlayer(player);
            player.setTile(destination);
            mapListener.playerDidMove(player, source, destination);
        }
    }

    @Override
    public void setListener(MapListener listener) {
        this.mapListener = listener;
        if(mapListener != null){

            List<Tile> tileList = new ArrayList<Tile>();
            List<Tile> spawnPointTileList = new ArrayList<Tile>();

            for(Tile[] tileRow: matrixMap){
                for (Tile tile : tileRow){
                    if(tile!= null){
                        tileList.add(tile);
                        if(tile.hasSpawnPoint()){
                            spawnPointTileList.add(tile);
                        }
                    }
                }
            }

            mapListener.mapDidSendTileData(tileList);
            mapListener.mapDidSendSpawnPointData(spawnPointTileList);

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
}
