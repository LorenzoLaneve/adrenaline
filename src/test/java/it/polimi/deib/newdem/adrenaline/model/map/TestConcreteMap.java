package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerImpl;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualMapView;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestConcreteMap {

    ConcreteMap map;
    List<Room> rooms;
    Room room;
    Tile[][] matrixMap;
    PlayerImpl player;

    @Before
    public void initTest(){

        matrixMap = new Tile[99][99];
        rooms = new ArrayList<>();

        List<int[]> roomInt = new ArrayList<>();

        for(int x=0; x < 4; x++){
            for(int y=0; y<4; y++){
                roomInt.add(new int[]{x,y});
                if(x != 1 && y != 1){
                    matrixMap[x][y] = new OrdinaryTile(new TilePosition(x,y));
                }
                else{
                    matrixMap[x][y] = new SpawnPointTile(new TilePosition(x,y));
                }
            }
        }
        room = new ConcreteRoom();

        rooms.add(room);

        map = new ConcreteMap(matrixMap, rooms);

        map.bindRooms();

        MockMapListener mockMapListener = new MockMapListener();

        map.setListener(mockMapListener);

        Game game = new MockGame(map);

        player = new PlayerImpl(PlayerColor.YELLOW, game, "dude");

        TilePosition tilePosition = new TilePosition(0,0);

        Tile destination = map.getTile(tilePosition);

        map.movePlayer(player, destination);
    }

    @Test
    public void testConcreteMap(){
        map = new ConcreteMap(matrixMap, rooms);
    }

    @Test
    public void testGetRooms() {
        assertTrue(map.getRooms().contains(room));
    }

    @Test
    public void testGetTile() {
        for(int x=0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (x != 1 && y != 1) {
                    if (!(map.getTile(new TilePosition(x, y)).getPosition().equals(matrixMap[x][y].getPosition()) &&
                            !map.getTile(new TilePosition(x, y)).hasSpawnPoint())) {
                        fail();
                    }
                } else {
                    if (!(map.getTile(new TilePosition(x, y)).getPosition().equals(matrixMap[x][y].getPosition()) &&
                            map.getTile(new TilePosition(x, y)).hasSpawnPoint())) {
                        fail();
                    }
                }
            }
        }
    }

    @Test
    public void testBindRooms(){
        assertEquals(room.getMap(), map);
    }

    @Test
    public void testMovePlayer(){

        TilePosition tilePosition = new TilePosition(1,1);

        Tile destination = map.getTile(tilePosition);


        map.movePlayer(player, destination);

        assertEquals(destination, player.getTile());
    }

    @Test
    public void removePlayer(){
        Tile currPos = player.getTile();

        map.removePlayer(player);


        assertFalse(currPos.getPlayers().contains(player));

    }

    @Test
    public void selectTiles(){
        MockSelector selector = new MockSelector();

        assertTrue(map.selectTiles(selector).contains(map.getTile(new TilePosition(0,0))));
    }

    @Test
    public void setListener(){
        MockMapListener newMockMapListener = new MockMapListener();

        map.setListener(newMockMapListener);

        assertEquals(newMockMapListener, map.getListener());
    }

    @Test
    public void getListener(){
        MockMapListener newMockMapListener = new MockMapListener();

        map.setListener(newMockMapListener);

        assertEquals(newMockMapListener, map.getListener());
    }

}