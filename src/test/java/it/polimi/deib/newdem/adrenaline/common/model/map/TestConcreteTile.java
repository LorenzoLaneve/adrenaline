package it.polimi.deib.newdem.adrenaline.common.model.map;

import it.polimi.deib.newdem.adrenaline.common.model.game.Game;
import it.polimi.deib.newdem.adrenaline.common.model.game.MockGame;
import it.polimi.deib.newdem.adrenaline.common.model.game.PlayerColor;
import it.polimi.deib.newdem.adrenaline.common.model.game.PlayerImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestConcreteTile {

    Room room;
    Tile[][] matrixMap;
    ConcreteMap map;
    PlayerImpl player1;
    PlayerImpl player2;
    PlayerImpl player3;
    Tile tile;
    Tile tileRight;
    TilePosition tilePosition;
    TilePosition tilePositionRight;
    Game game;

    @Before
    public void initTest(){
        game = new MockGame();
        player1 = new PlayerImpl(PlayerColor.MAGENTA, game, "Larry");
        player2 = new PlayerImpl(PlayerColor.GRAY, game, "Carl");

        matrixMap = new Tile[2][2];
        List<int[]> roomInt = new ArrayList<>();
        roomInt.add(new int[]{0,0});

        tilePosition = new TilePosition(0,0);
        tilePositionRight = new TilePosition(1,0);

        tile = new OrdinaryTile(tilePosition);
        tileRight = new OrdinaryTile(tilePositionRight);

        matrixMap[0][0] = tile;
        matrixMap[1][0] = tileRight;

        room = new ConcreteRoom();

        room.setMap(map);

        tile.setRoom(room);
        tileRight.setRoom(room);

        tile.addPlayer(player1);
        tile.addPlayer(player2);
    }

    @Test
    public void testConstructor(){

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
        //TODO
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
        player3 = new PlayerImpl(PlayerColor.YELLOW, game, "Steve");

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
}