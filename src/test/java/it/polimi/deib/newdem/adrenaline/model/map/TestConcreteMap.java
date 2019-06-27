package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerImpl;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestConcreteMap {

    Map map;
    List<Room> rooms;
    Room room;
    Tile[][] matrixMap;
    PlayerImpl player;
    String mapID;

    @Before
    public void initTest(){

        mapID = "Map0_0";

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
                    matrixMap[x][y] = new SpawnPointTile(new TilePosition(x,y), AmmoColor.RED);
                }
            }
        }

        map =  Map.createMap(this.getClass().getClassLoader().getResource("TestMap.json").getFile().replace("%20", " "));

        room = map.getRooms().get(0);

        MockMapListener mockMapListener = new MockMapListener();

        map.setListener(mockMapListener);

        Game game = new MockGame(map);

        player = new PlayerImpl(PlayerColor.YELLOW, game);

        TilePosition tilePosition = new TilePosition(0,0);

        Tile destination = map.getTile(tilePosition);

        map.movePlayer(player, destination);
    }

    @Test
    public void testConcreteMap(){
        map = new ConcreteMap(matrixMap, rooms, mapID);
    }

    @Test
    public void testGetRooms() {
        assertTrue(map.getRooms().contains(room));
    }

    @Test
    public void testGetTile() {
        TilePosition redSpawnPointPos = new TilePosition(0,0);
        TilePosition blueSpawnPointPos = new TilePosition(1,1);
        TilePosition yellowSpawnPointPos = new TilePosition(0,1);
        TilePosition noSpawnPointPos = new TilePosition(1,0);

        boolean red = map.getTile(redSpawnPointPos).hasSpawnPoint();
        boolean blue = map.getTile(blueSpawnPointPos).hasSpawnPoint();
        boolean yellow = map.getTile(yellowSpawnPointPos).hasSpawnPoint();
        boolean nope = map.getTile(noSpawnPointPos).hasSpawnPoint();

        SpawnPointTile redSpawnPoint = (SpawnPointTile) map.getTile(redSpawnPointPos);
        SpawnPointTile blueSpawnPoint = (SpawnPointTile) map.getTile(blueSpawnPointPos);
        SpawnPointTile yellowSpawnPoint = (SpawnPointTile) map.getTile(yellowSpawnPointPos);


        assertEquals(AmmoColor.RED, redSpawnPoint.getSpawnPointColor());
        assertEquals(AmmoColor.BLUE, blueSpawnPoint.getSpawnPointColor());
        assertEquals(AmmoColor.YELLOW, yellowSpawnPoint.getSpawnPointColor());
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

    @Test
    public void testGetSpawnPoint(){
        Tile redSpawnPoint = map.getTile(new TilePosition(0,0));
        assertEquals(redSpawnPoint, map.getSpawnPointFromColor(AmmoColor.RED));
    }

    @Test
    public void testSendMapData(){
        MockMapListener newMockMapListener = new MockMapListener();

        map.setListener(newMockMapListener);

        map.sendMapData();
    }

    @Test
    public void testGenerateMapData(){
        MapData mapData = map.generateMapData();

        //assertEquals(mapID, mapData.getMapID());
        assertEquals(map.getSpawnPointFromColor(AmmoColor.RED).getPosition(), mapData.getRedSpawnPoint());
        assertEquals(map.getSpawnPointFromColor(AmmoColor.BLUE).getPosition(), mapData.getBlueSpawnPoint());
        assertEquals(map.getSpawnPointFromColor(AmmoColor.YELLOW).getPosition(), mapData.getYellowSpawnPoint());
        assertEquals(new PlayerTilePair(new TilePosition(0,0),player.getColor()).getPlayer(),
                mapData.getPlayerLocations().get(0).getPlayer());
        assertEquals(new PlayerTilePair(new TilePosition(0,0),player.getColor()).getTile(),
                mapData.getPlayerLocations().get(0).getTile());
        for (Tile tile: map.getAllTiles()){
            assertTrue(mapData.getTiles().contains(tile.getPosition()));
        }
    }
}