package it.polimi.deib.newdem.adrenaline.common.model.map;

import java.util.List;

public class ConcreteMap implements Map {
    // TODO figure out data structure

    private List<Room> rooms;

    public ConcreteMap(List<Room> rooms) {
        // TODO implement
        this.rooms = rooms;
    }

    @Override
    public List<Room> getRooms() {
        // TODO implement
        return null;
    }

    @Override
    public Tile getTile(TilePosition p) {
        // TODO implement
        return null;
    }
}
