package it.polimi.deib.newdem.adrenaline.common.model.map;

import it.polimi.deib.newdem.adrenaline.common.model.game.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class ConcreteTile implements Tile {

    private TilePosition tilePosition;

    private Room room;

    private List<Player> players;

    /**Creates a new {@code ConcreteTile} belonging to room and in position tilePosition.
     *
     * @param room the room to which the tile belongs.
     * @param tilePosition the position the tile occupies.
     */
    public ConcreteTile(Room room, TilePosition tilePosition ){
        this.room = room;
        this.tilePosition = tilePosition;
        this.players = new ArrayList<>();
    }

    @Override
    public Map getMap() {
        return room.getMap();
    }

    @Override
    public Room getRoom() {
        return room;
    }

    @Override
    public TilePosition getPosition() {
        return new TilePosition(tilePosition.getX(), tilePosition.getY());
    }

    @Override
    public int distanceFrom(Tile t) {
        // TODO implement
        return 0;
    }

    @Override
    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    @Override
    public List<Tile> getAdjacentTiles() {
        List<Tile> adjacentTiles = new ArrayList<>();

        Tile leftTile = this.getMap().getTile(new TilePosition(this.getPosition().getX()-1,this.getPosition().getY()));
        Tile rightTile = this.getMap().getTile(new TilePosition(this.getPosition().getX()+1,this.getPosition().getY()));
        Tile topTile = this.getMap().getTile(new TilePosition(this.getPosition().getX(),this.getPosition().getY()+1));
        Tile bottomTile = this.getMap().getTile(new TilePosition(this.getPosition().getX(),this.getPosition().getY()-1));

        if (leftTile != null){
            if(leftTile.getRoom()== room){
                adjacentTiles.add(leftTile);
            }
        }
        if ( rightTile != null){
            if(rightTile.getRoom()== room){
                adjacentTiles.add(rightTile);
            }
        }
        if(topTile != null){
            if(topTile.getRoom()== room){
                adjacentTiles.add(topTile);
            }
        }
        if(bottomTile != null){
            if(bottomTile.getRoom()== room){
                adjacentTiles.add(bottomTile);
            }
        }
        return adjacentTiles;
    }

    @Override
    public void addPlayer(Player player){

        if(!players.contains(player)){
            players.add(player);
        }
        else {
            throw new IllegalArgumentException("Player not found");
        }
    }

    @Override
    public  void removePlayer(Player player){
        if(players.contains(player)){
            players.remove(player);
        }
        else{
            throw new IllegalArgumentException("Player not found");
        }

    }
}
