package it.polimi.deib.newdem.adrenaline.common.model.map;

import it.polimi.deib.newdem.adrenaline.common.model.game.Player;
import it.polimi.deib.newdem.adrenaline.common.model.game.PlayerImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static junit.framework.TestCase.*;

public class TestConcreteRoom {

    ConcreteMap map;
    List<RoomTileSeeds> roomTileSeedsList;
    RoomTileSeeds roomTileSeeds;
    ConcreteRoom room;
    PlayerImpl player1;
    PlayerImpl player2;

    @Before
    public void testInit(){
        TilePosition topLeft;
        TilePosition topRight;
        TilePosition bottomRight;
        TilePosition bottomLeft;
        TilePosition spawnPointTilePosition;

        topLeft = new TilePosition(0,3);
        topRight = new TilePosition(2,3);
        bottomRight = new TilePosition(2,0);
        bottomLeft = new TilePosition(0,0);
        spawnPointTilePosition = new TilePosition(1,1);

        roomTileSeeds = new RoomTileSeeds(topLeft,topRight,bottomRight,bottomLeft, spawnPointTilePosition);

        roomTileSeedsList = new ArrayList<>();
        roomTileSeedsList.add(roomTileSeeds);

        map = new ConcreteMap(roomTileSeedsList);
        map.generateRooms();

        room = new ConcreteRoom(map, roomTileSeeds);
        room.generateTiles();

        player1 = new PlayerImpl();
        player2 = new PlayerImpl();
    }

    @Test
    public void testConstructorPositive() {
        room = new ConcreteRoom(map, roomTileSeeds);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegative(){
        TilePosition topLeft = new TilePosition(0,9);
        TilePosition topRight = new TilePosition(6,9);
        TilePosition bottomRight = new TilePosition(6,0);
        TilePosition bottomLeft = new TilePosition(0,0);
        TilePosition spawnPointTilePosition = new TilePosition(0,2);

        RoomTileSeeds wrongRoomTileSeeds = new RoomTileSeeds(topLeft,topRight,topRight,topLeft, spawnPointTilePosition);

        room = new ConcreteRoom(map, wrongRoomTileSeeds);
    }

    @Test
    public void testGetTiles() {

        List<Tile> tiles = room.getTiles();

        int leftX = roomTileSeeds.getLeftX();
        int rightX = roomTileSeeds.getRightX();
        int topY = roomTileSeeds.getTopY();
        int bottomY = roomTileSeeds.getBottomY();

        for(int y = bottomY; y <= topY; y++){
            for (int x = leftX; x <= rightX; x++){
                int finalY = y;
                int finalX = x;
                if(tiles.stream().noneMatch(tile -> (tile.getPosition().getX() == finalX &&
                        tile.getPosition().getY() == finalY))){
                    fail();
                }
            }

        }
    }

    @Test
    public void testGetMap() {
        assertEquals(map, room.getMap());
    }

    @Test
    public void testGetPlayers() {

        List<Player> playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);

        room.getTiles().get(0).addPlayer(player1);
        room.getTiles().get(0).addPlayer(player2);

        assertTrue(room.getPlayers().containsAll(playerList));
    }
}