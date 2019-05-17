package it.polimi.deib.newdem.adrenaline.model.map;

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
}
