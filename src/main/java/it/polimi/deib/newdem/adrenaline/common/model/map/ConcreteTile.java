package it.polimi.deib.newdem.adrenaline.common.model.map;

import it.polimi.deib.newdem.adrenaline.common.model.game.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class ConcreteTile implements Tile {

    private TilePosition tilePosition;

    private Room room;

    private List<Player> players;

    private List<Tile> adjacentTiles;

    /**Creates a new {@code ConcreteTile} belonging to room and in position tilePosition.
     *
     * @param tilePosition the position the tile occupies.
     */
    public ConcreteTile(TilePosition tilePosition ){
        this.tilePosition = tilePosition;
        this.players = new ArrayList<>();
        this.adjacentTiles = new ArrayList<>();
    }

    /**Called after the Room is initialized to bind the already created tile to the room.
     *
     * @param room the room to which to bind the tile.
     */
    @Override
    public void setRoom(Room room){
        this.room = room;
    }

    /**Returns the map to which the tile belongs.
     *
     * @return the map to which the tile belongs.
     */
    @Override
    public Map getMap() {
        return room.getMap();
    }

    /**Returns the room to which the tile belongs.
     *
     * @return the room to which the tile belongs.
     */
    @Override
    public Room getRoom() {
        return room;
    }

    /**Returns the tile position of the tile.
     *
     * @return a tile position object.
     */
    @Override
    public TilePosition getPosition() {
        return new TilePosition(tilePosition.getX(), tilePosition.getY());
    }

    @Override
    public int distanceFrom(Tile t) {
        // TODO implement
        return 0;
    }

    /**Returns the players in the room.
     *
     * @return a list of the players in the room.
     */
    @Override
    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    /**Returns the tiles adjacent to the tile.
     *
     * @return a list of the tiles adjacent to the tile..
     */
    @Override
    public List<Tile> getAdjacentTiles() {
        return new ArrayList<>(adjacentTiles);
    }

    /**Used to position a player on the tile.
     *
     * @param player the player to add.
     */
    @Override
    public void addPlayer(Player player){

        if(!players.contains(player)){
            players.add(player);
        }
        else {
            throw new IllegalArgumentException("Player not found");
        }
    }

    /**Used to remove a player to players.
     *
     * @param player the player to remove.
     */
    @Override
    public  void removePlayer(Player player){
        if(players.contains(player)){
            players.remove(player);
        }
        else{
            throw new IllegalArgumentException("Player not found");
        }

    }
    /**Used to add a tile to the adjacent tiles of the tile.
     *
     * @param tile the tile to add.
     */
    @Override
    public void addAdjacentTiles(Tile tile){
        adjacentTiles.add(tile);
    }

    @Override
    public boolean equalTile(Tile tile){
        return (this.getPosition().getY() == tile.getPosition().getY() &&
                this.getPosition().getX() == tile.getPosition().getX() &&
                this.getRoom() == tile.getRoom() &&
                this.hasSpawnPoint() == tile.hasSpawnPoint());
    }
}
