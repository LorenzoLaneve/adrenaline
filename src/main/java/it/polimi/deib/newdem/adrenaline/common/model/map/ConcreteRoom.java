package it.polimi.deib.newdem.adrenaline.common.model.map;

import it.polimi.deib.newdem.adrenaline.common.model.game.Player;

import java.util.ArrayList;
import java.util.List;

public class ConcreteRoom implements Room {

    private Map map;

    private ArrayList<Tile> tiles;

    /**Creates a new {@code ConcreteRoom} instance.
     *
     */
    public ConcreteRoom() {
        this.tiles = new ArrayList<>();
    }

    /**Returns the list of tiles belonging to the room.
     *
     * @return the list of tiles belonging to the room.
     */
    @Override
    public List<Tile> getTiles() {
        return new ArrayList<>(tiles);
    }

    @Override

    /**Returns the map to which the room belongs.
     *
     * @return the map to which the room belongs.
     */
    public Map getMap() {
        return map;
    }

    /**Returns the players in the room.
     *
     * @return a list of the players in the room.
     */
    @Override
    public List<Player> getPlayers() {
        ArrayList<Player> playerList = new ArrayList<>();

        for (Tile x : this.getTiles()){
            playerList.addAll(x.getPlayers());
        }
        return playerList;
    }

    /**Called after the Room is initialized to bind the already created tiles to the room.
     *
     * @param tile the tile to bind to the room.
     */

    @Override
    public void addTiles(Tile tile){
        tiles.add(tile);
    }

    /**Called after the Map is initialized to bind the already created room to the map.
     *
     * @param map the map to which to bind the room.
     */
    @Override
    public void setMap(Map map) {
        this.map = map;
    }

}
