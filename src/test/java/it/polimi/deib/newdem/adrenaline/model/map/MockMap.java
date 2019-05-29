package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.List;

public class MockMap implements Map {

    @Override
    public List<Room> getRooms() {
        return null;
    }

    @Override
    public Tile getTile(TilePosition p) {
        if(p.equals(new TilePosition(0,0))) return new OrdinaryTile(p);
        if(p.equals(new TilePosition(1,1))) return new SpawnPointTile(p);
        return null;
    }

    @Override
    public void bindRooms() {

    }

    @Override
    public void movePlayer(Player player, Tile destination) {
        
    }

    @Override
    public void setListener(MapListener listener) {

    }

    @Override
    public MapListener getListener() {
        return null;
    }

    @Override
    public void removePlayer(Player player) {

    }
}
