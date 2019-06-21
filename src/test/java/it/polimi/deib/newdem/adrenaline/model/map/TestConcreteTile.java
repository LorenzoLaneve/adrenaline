package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.MockGame;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestConcreteTile {

    Room room;
    Tile[][] matrixMap;
    Map map;
    PlayerImpl player1;
    PlayerImpl player2;
    PlayerImpl player3;
    Tile tile;
    Tile tileRight;
    Tile tileRightRight;
    TilePosition tilePosition;
    TilePosition tilePositionRight;
    TilePosition tilePositionRightRight;
    Game game;

    @Before
    public void initTest(){
        game = new MockGame();
        player1 = new PlayerImpl(PlayerColor.MAGENTA, game);
        player2 = new PlayerImpl(PlayerColor.GRAY, game);
        List<int[]> roomInt = new ArrayList<>();
        roomInt.add(new int[]{0,0});

        map = Map.createMap(this.getClass().getClassLoader().getResource("TestMap.json").getFile().replace("%20", " "));

        tilePosition = new TilePosition(0,0);
        tilePositionRight = new TilePosition(1,0);
        tilePositionRightRight = new TilePosition(1,1);

        tile = map.getTile(tilePosition);
        tileRight = map.getTile(tilePositionRight);
        tileRightRight = map.getTile(tilePositionRightRight);

        room = map.getRooms().get(0);


        tile.addPlayer(player1);
        tile.addPlayer(player2);
    }

    @Test
    public void testConstructor(){
        Tile ordinaryTile = new OrdinaryTile(new TilePosition(0,0));

    }

    @Test
    public void testSetRoom() {
        tile.setRoom(null);
        tile.setRoom(room);

        assertEquals(room, tile.getRoom());
    }

    @Test
    public void testGetMap() {
        assertEquals(map,tile.getMap());
    }

    @Test
    public void testGetRoom() {
        assertEquals(room,tile.getRoom());
    }

    @Test
    public void testGetPosition() {
        assertTrue(tile.getPosition().getX() ==  tilePosition.getX() &&
                tile.getPosition().getY() == tilePosition.getY());
    }

    @Test
    public void testDistanceFrom() {
        assertEquals(1,tile.distanceFrom(tileRight));
        assertEquals(0,tile.distanceFrom(tile));
        assertEquals(2, tile.distanceFrom(tileRightRight));
    }

    @Test
    public void testGetPlayers() {
        assertTrue(tile.getPlayers().contains(player1));
        assertTrue(tile.getPlayers().contains(player2));
    }

    @Test
    public void testGetAdjacentTiles() {
        tile.addAdjacentTiles(tileRight);
        tileRight.addAdjacentTiles(tile);

        assertTrue(tile.getAdjacentTiles().stream().anyMatch(matchTile ->(matchTile.getPosition().getX() == tileRight.getPosition().getX() &&
                matchTile.getPosition().getY() == tileRight.getPosition().getY()) ));

    }

    @Test
    public void testAddPlayer() {
        player3 = new PlayerImpl(PlayerColor.YELLOW, game);

        tile.addPlayer(player3);

        assertTrue(tile.getPlayers().contains(player3));
    }

    @Test
    public void testRemovePlayer() {
        tile.removePlayer(player1);

        assertFalse(tile.getPlayers().contains(player1));
    }

    @Test
    public void testAddAdjacentTiles() {
        tile.addAdjacentTiles(tileRight);
        tileRight.addAdjacentTiles(tile);

        assertTrue(tile.getAdjacentTiles().stream().anyMatch(matchTile ->(matchTile.getPosition().getX() == tileRight.getPosition().getX() &&
                matchTile.getPosition().getY() == tileRight.getPosition().getY()) ));
    }

    @Test
    public void testGetTiles(){

        boolean  test = tile.getTiles(Direction.EAST, true).contains(tileRight);

        assertTrue(test);
    }

    @Test
    public void testGetDirection(){
        assertEquals(Direction.EAST, tile.getDirection(tileRight));
    }
}