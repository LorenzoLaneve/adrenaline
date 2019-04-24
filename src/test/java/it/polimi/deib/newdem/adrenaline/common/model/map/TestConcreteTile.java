package it.polimi.deib.newdem.adrenaline.common.model.map;

import it.polimi.deib.newdem.adrenaline.common.model.game.Player;
import it.polimi.deib.newdem.adrenaline.common.model.game.PlayerImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;

public class TestConcreteTile {

    ConcreteMap map;
    List<RoomTileSeeds> roomTileSeedsList;
    RoomTileSeeds roomTileSeeds;
    ConcreteRoom room;
    TilePosition tilePosition;
    ConcreteTile concreteTile;
    Player player1;
    Player player2;


    @Before
    public void testInit(){
        TilePosition topLeft;
        TilePosition topRight;
        TilePosition bottomRight;
        TilePosition bottomLeft;
        TilePosition spawnPointTilePosition;

        topLeft = new TilePosition(0,9);
        topRight = new TilePosition(6,9);
        bottomRight = new TilePosition(6,0);
        bottomLeft = new TilePosition(0,0);
        spawnPointTilePosition = new TilePosition(0,2);

        roomTileSeeds = new RoomTileSeeds(topLeft,topRight,bottomRight,bottomLeft, spawnPointTilePosition);

        roomTileSeedsList = new ArrayList<>();
        roomTileSeedsList.add(roomTileSeeds);

        map = new ConcreteMap(roomTileSeedsList);
        map.generateRooms();

        room = new ConcreteRoom(map, roomTileSeeds);
        room.generateTiles();

        tilePosition = spawnPointTilePosition;

        concreteTile = new OrdinaryTile(room, tilePosition);

        player1 = new PlayerImpl();
        player2 = new PlayerImpl();

        concreteTile.addPlayer(player1);
        concreteTile.addPlayer(player2);


    }

    @Test
    public void testConstructorPositive(){
        concreteTile = new OrdinaryTile(room, tilePosition);
    }


    @Test
    public void testGetMap() {
        assertEquals(map, concreteTile.getMap());
    }

    @Test
    public void testGetRoom() {
        assertEquals(room, concreteTile.getRoom());
    }

    @Test
    public void testGetPosition() {
        assertEquals(tilePosition.getX(), concreteTile.getPosition().getX());
        assertEquals(tilePosition.getY(), concreteTile.getPosition().getY());
    }

    @Test
    public void testDistanceFrom() {
        //TODO
    }

    @Test
    public void testGetPlayers() {
        assertTrue(concreteTile.getPlayers().contains(player1));
        assertTrue(concreteTile.getPlayers().contains(player2));
    }

    @Test
    public void testGetAdjacentTiles() {
        Tile anyTile = room.getTiles().get(4);

        TilePosition anyTilePosition = anyTile.getPosition();

        Tile leftTile = anyTile.getMap().getTile(new TilePosition(anyTilePosition.getX()-1,anyTilePosition.getY()));
        Tile rightTile = anyTile.getMap().getTile(new TilePosition(anyTilePosition.getX()+1,anyTilePosition.getY()));
        Tile topTile = anyTile.getMap().getTile(new TilePosition(anyTilePosition.getX(),anyTilePosition.getY()+1));
        Tile bottomTile = anyTile.getMap().getTile(new TilePosition(anyTilePosition.getX(),anyTilePosition.getY()-1));


        if (leftTile != null){
            if(leftTile.getRoom()== room && !anyTile.getAdjacentTiles().contains(leftTile)){
                fail();
            }
        }
        if ( rightTile != null){
            if(rightTile.getRoom()== room && !anyTile.getAdjacentTiles().contains(rightTile)){
                fail();
            }
        }
        if(topTile != null){
            if(topTile.getRoom()== room && !anyTile.getAdjacentTiles().contains(topTile)){
                fail();
            }
        }
        if(bottomTile != null){
            if(bottomTile.getRoom()== room && !anyTile.getAdjacentTiles().contains(bottomTile)){
                fail();
            }
        }

    }

    @Test
    public void testAddPlayerPositive() {
        Player player3 = new PlayerImpl();

        concreteTile.addPlayer(player3);

        assertTrue(concreteTile.getPlayers().contains(player3));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testAddPlayerNegative() {
        concreteTile.addPlayer(player2);
    }

    @Test
    public void testRemovePlayerPositive() {
        concreteTile.removePlayer(player1);

        assertFalse(concreteTile.getPlayers().contains(player1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemovePlayerNegative() {
        concreteTile.removePlayer(player1);
        concreteTile.removePlayer(player1);
    }
}