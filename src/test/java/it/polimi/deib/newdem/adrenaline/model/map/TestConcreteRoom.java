package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.MockGame;
import it.polimi.deib.newdem.adrenaline.model.game.PlayerImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static it.polimi.deib.newdem.adrenaline.model.game.PlayerColor.CYAN;
import static it.polimi.deib.newdem.adrenaline.model.game.PlayerColor.MAGENTA;
import static org.junit.Assert.*;

public class TestConcreteRoom {
    Room room;
    Tile[][] matrixMap;
    ConcreteMap map;
    PlayerImpl player1;
    PlayerImpl player2;
    Game game;


    @Before
    public void initTest(){
        List<int[]> roomInt = new ArrayList<>();

        matrixMap = new Tile[99][99];

        game = new MockGame();

        player1 = new PlayerImpl(CYAN, game, "Larry");
        player2 = new PlayerImpl(MAGENTA, game, "Steve");

        room = new ConcreteRoom();

        for(int x=0; x < 4; x++){
            for(int y=0; y<4; y++){
                roomInt.add(new int[]{x,y});
                if(x != 1 && y != 1){
                    matrixMap[x][y] = new OrdinaryTile(new TilePosition(x,y));

                }
                else{
                    matrixMap[x][y] = new SpawnPointTile(new TilePosition(x,y));
                    matrixMap[x][y].addPlayer(player1);
                    matrixMap[x][y].addPlayer(player2);
                }
                room.addTiles(matrixMap[x][y]);
            }
        }


    }

    @Test
    public void testConstructor(){

    }

    @Test
    public void testGetTiles() {
        for(int x=0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                int finalX = x;
                int finalY = y;
                if (x != 1 && y != 1) {
                    if (room.getTiles().stream().noneMatch(tile->(tile.getPosition().equals(matrixMap[finalX][finalY].getPosition()) && !tile.hasSpawnPoint()))) {
                        fail();
                    }
                } else {
                    if (room.getTiles().stream().noneMatch(tile->(tile.getPosition().equals(matrixMap[finalX][finalY].getPosition()) && tile.hasSpawnPoint()))){
                        fail();
                    }
                }
            }
        }
    }

    @Test
    public void testGetMap() {
        assertEquals(map,room.getMap());
    }

    @Test
    public void testGetPlayers() {
        assertTrue(room.getPlayers().contains(player1));
        assertTrue(room.getPlayers().contains(player2));
    }

    @Test
    public void testAddTiles() {
        ConcreteTile tile = new OrdinaryTile(new TilePosition(2,3));
        room.addTiles(tile);

        assertTrue(room.getTiles().contains(tile));
    }

    @Test
    public void testSetMap() {
        room.setMap(null);
        room.setMap(map);

        assertEquals(map, room.getMap());
    }
}