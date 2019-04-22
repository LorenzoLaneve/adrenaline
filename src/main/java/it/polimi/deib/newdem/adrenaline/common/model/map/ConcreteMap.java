package it.polimi.deib.newdem.adrenaline.common.model.map;

import java.util.ArrayList;
import java.util.List;

public class ConcreteMap implements Map {
    // TODO figure out data structure

    private ArrayList<ConcreteRoom> rooms;
    List<RoomTileSeeds> roomTileSeedsList;

    public ConcreteMap(List<RoomTileSeeds> roomTileSeedsList) {
        this.rooms = new ArrayList<>();
        this.roomTileSeedsList = roomTileSeedsList;

    }

    @Override
    public List<Room> getRooms() {

        return new ArrayList<>(rooms);
    }

    @Override
    public Tile getTile(TilePosition tilePosition) {
        boolean found = false;
        Tile foundTile = null;

        for (ConcreteRoom room : rooms){
            if(room.getRoomTileSeeds().containsTilePosition(tilePosition)){
                for(Tile tile : room.getTiles()){
                    if(tile.getPosition().getY() == tilePosition.getY() &&
                        tile.getPosition().getX() == tilePosition.getY()){
                        foundTile = tile;
                        found = true;
                    }
                }
            }
        }

        return foundTile;
    }

    @Override
    public void generateRooms() {
       for(RoomTileSeeds roomTileSeeds : roomTileSeedsList){
           ConcreteRoom newRoom = new ConcreteRoom(this,roomTileSeeds);
           newRoom.generateTiles();
           rooms.add(newRoom);
        }
    }
}
