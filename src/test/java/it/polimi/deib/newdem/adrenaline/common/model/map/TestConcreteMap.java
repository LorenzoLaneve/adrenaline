package it.polimi.deib.newdem.adrenaline.common.model.map;

import it.polimi.deib.newdem.adrenaline.common.model.game.Player;
import it.polimi.deib.newdem.adrenaline.common.model.game.PlayerImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class TestConcreteMap {

    ConcreteMap map;
    List<RoomTileSeeds> roomTileSeedsList;

    @Before
    public void initTest(){

        TilePosition topLeft;
        TilePosition topRight;
        TilePosition bottomRight;
        TilePosition bottomLeft;
        TilePosition spawnPointTilePosition;

        topLeft = new TilePosition(1,1);
        topRight = new TilePosition(1,1);
        bottomRight = new TilePosition(1,1);
        bottomLeft = new TilePosition(1,1);
        spawnPointTilePosition = new TilePosition(1,1);

        RoomTileSeeds roomTileSeeds = new RoomTileSeeds(topLeft,topRight,bottomRight,bottomLeft, spawnPointTilePosition);

        roomTileSeedsList = new ArrayList<>();
        roomTileSeedsList.add(roomTileSeeds);

        map = new ConcreteMap(roomTileSeedsList);

        map.generateRooms();

        boolean dunno = false;

        int hhh =3;
    }

    @Test
    public void testConstructorPositive(){
        map = new ConcreteMap(roomTileSeedsList);
        map.generateRooms();
    }

    @Test
    public void testGetRooms() {
        List<Room> roomList = map.getRooms();

        for(RoomTileSeeds currTileSeeds : roomTileSeedsList){

            if(roomList.stream().noneMatch(
               room-> (room.getRoomTileSeeds().getCornerTopLeft() == currTileSeeds.getCornerTopLeft() &&
                       room.getRoomTileSeeds().getCornerTopRight() == currTileSeeds.getCornerTopRight() &&
                       room.getRoomTileSeeds().getCornerBottomRight() == currTileSeeds.getCornerBottomRight() &&
                       room.getRoomTileSeeds().getCornerBottomLeft() == currTileSeeds.getCornerBottomLeft())))
            {
                    fail();
            }
        }
    }

    @Test
    public void testGetTile() {
        Player player = new PlayerImpl();

        map = new ConcreteMap(roomTileSeedsList);
        map.generateRooms();

        List<Room> roomList = map.getRooms();

        Room anyRoom = roomList.get(0);

        List<Tile> tileList = anyRoom.getTiles();

        Tile anyTile = tileList.get(0);

        anyTile.addPlayer(player);

        TilePosition anyTilePosition = anyTile.getPosition();

        Tile returnedTilePosition = map.getTile(anyTilePosition);

        assertTrue(returnedTilePosition.getPosition().getX() == anyTilePosition.getY() &&
                returnedTilePosition.getPosition().getY() == anyTilePosition.getY());
        assertTrue(returnedTilePosition.getPlayers().containsAll(anyTile.getPlayers()));

    }
}