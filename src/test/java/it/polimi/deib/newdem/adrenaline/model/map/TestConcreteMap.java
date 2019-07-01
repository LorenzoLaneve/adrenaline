package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.TestingUtils;
import it.polimi.deib.newdem.adrenaline.controller.Config;
import it.polimi.deib.newdem.adrenaline.model.game.ColorUserPair;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameImpl;
import it.polimi.deib.newdem.adrenaline.model.game.GameParameters;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerImpl;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.server.NullVirtualGameView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualDamageBoardView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualGameView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualKillTrackView;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestConcreteMap {

    Map mapOld;
    List<Room> rooms;
    Room room;
    Tile[][] matrixMap;
    PlayerImpl player;
    String mapID;
    Map map;
    Player player1;
    Player player2;
    Player player3;
    Player player4;

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

        mapOld =  Map.createMap(this.getClass().getClassLoader().getResource("TestMap.json").getFile().replace("%20", " "));

        room = mapOld.getRooms().get(0);

        MockMapListener mockMapListener = new MockMapListener();

        mapOld.setListener(mockMapListener);

        Game game = new MockGame(mapOld);

        player = new PlayerImpl(PlayerColor.YELLOW, game);

        TilePosition tilePosition = new TilePosition(0,0);

        Tile destination = mapOld.getTile(tilePosition);

        mapOld.movePlayer(player, destination);

        map =  Map.createMap(this.getClass().getClassLoader().getResource("Map0_1.json").getFile().replace("%20", " "));

        GameParameters gp = GameParameters.fromConfig(Config.getDefaultConfig());

        ColorUserPair colorUserPair1 = new ColorUserPair(PlayerColor.YELLOW, new User());
        ColorUserPair colorUserPair2 = new ColorUserPair(PlayerColor.GREEN, new User());
        ColorUserPair colorUserPair3 = new ColorUserPair(PlayerColor.GRAY, new User());
        ColorUserPair colorUserPair4 = new ColorUserPair(PlayerColor.MAGENTA, new User());
        List<ColorUserPair> listPairs = new ArrayList<>();
        listPairs.add(colorUserPair1);
        listPairs.add(colorUserPair2);
        listPairs.add(colorUserPair3);
        listPairs.add(colorUserPair4);


        gp.setColorUserOrder(listPairs);

        gp.setGameMap(map);
        TestingUtils.loadSingleton();

        game = new GameImpl(gp);

        VirtualGameView vgv = new NullVirtualGameView();
        game.setGameListener(vgv);
        game.setKillTrackListener(new VirtualKillTrackView(vgv)); //???

        game.init();

        player1 = game.getPlayerFromColor(PlayerColor.YELLOW);
        player2 = game.getPlayerFromColor(PlayerColor.GREEN);
        player3 = game.getPlayerFromColor(PlayerColor.GRAY);
        player4 = game.getPlayerFromColor(PlayerColor.MAGENTA);

        player1.getDamageBoard().setListener(new VirtualDamageBoardView(player1, vgv));
        player2.getDamageBoard().setListener(new VirtualDamageBoardView(player2, vgv));
        player3.getDamageBoard().setListener(new VirtualDamageBoardView(player3, vgv));
        player4.getDamageBoard().setListener(new VirtualDamageBoardView(player4, vgv));

        player1.getInventory().addAmmo(AmmoColor.RED, 1);
        player2.getInventory().addAmmo(AmmoColor.BLUE, 1);
        player3.getInventory().addAmmo(AmmoColor.BLUE, 1);
        player4.getInventory().addAmmo(AmmoColor.BLUE,1);


        TilePosition tilePosition1 = new TilePosition(3,0);
        TilePosition tilePosition2 = new TilePosition(2,1);
        TilePosition tilePosition3 = new TilePosition(1,2);
        TilePosition tilePosition4 = new TilePosition(1,1);

        Tile destination1 = map.getTile(tilePosition1);
        Tile destination2 = map.getTile(tilePosition2);
        Tile destination3 = map.getTile(tilePosition3);
        Tile destination4 = map.getTile(tilePosition4);


        map.movePlayer(player1, destination1);
        map.movePlayer(player2, destination2);
        map.movePlayer(player3, destination3);
        map.movePlayer(player4,destination4);
    }

    @Test
    public void testConcreteMap(){
        mapOld = new ConcreteMap(matrixMap, rooms, mapID);
    }

    @Test
    public void testGetRooms() {
        assertTrue(mapOld.getRooms().contains(room));
    }

    @Test
    public void testGetTile() {
        TilePosition redSpawnPointPos = new TilePosition(0,0);
        TilePosition blueSpawnPointPos = new TilePosition(1,1);
        TilePosition yellowSpawnPointPos = new TilePosition(0,1);
        TilePosition noSpawnPointPos = new TilePosition(1,0);

        boolean red = mapOld.getTile(redSpawnPointPos).hasSpawnPoint();
        boolean blue = mapOld.getTile(blueSpawnPointPos).hasSpawnPoint();
        boolean yellow = mapOld.getTile(yellowSpawnPointPos).hasSpawnPoint();
        boolean nope = mapOld.getTile(noSpawnPointPos).hasSpawnPoint();

        SpawnPointTile redSpawnPoint = (SpawnPointTile) mapOld.getTile(redSpawnPointPos);
        SpawnPointTile blueSpawnPoint = (SpawnPointTile) mapOld.getTile(blueSpawnPointPos);
        SpawnPointTile yellowSpawnPoint = (SpawnPointTile) mapOld.getTile(yellowSpawnPointPos);


        assertEquals(AmmoColor.RED, redSpawnPoint.getSpawnPointColor());
        assertEquals(AmmoColor.BLUE, blueSpawnPoint.getSpawnPointColor());
        assertEquals(AmmoColor.YELLOW, yellowSpawnPoint.getSpawnPointColor());
    }

    @Test
    public void testBindRooms(){
        assertEquals(room.getMap(), mapOld);
    }

    @Test
    public void testMovePlayer(){

        TilePosition tilePosition = new TilePosition(1,1);

        Tile destination = mapOld.getTile(tilePosition);


        mapOld.movePlayer(player, destination);

        assertEquals(destination, player.getTile());
    }

    @Test
    public void removePlayer(){
        Tile currPos = player.getTile();

        mapOld.removePlayer(player);


        assertFalse(currPos.getPlayers().contains(player));

    }

    @Test
    public void selectTiles(){
        MockSelector selector = new MockSelector();

        assertTrue(mapOld.selectTiles(selector).contains(mapOld.getTile(new TilePosition(0,0))));
    }

    @Test
    public void setListener(){
        MockMapListener newMockMapListener = new MockMapListener();

        mapOld.setListener(newMockMapListener);

        assertEquals(newMockMapListener, mapOld.getListener());
    }

    @Test
    public void getListener(){
        MockMapListener newMockMapListener = new MockMapListener();

        mapOld.setListener(newMockMapListener);

        assertEquals(newMockMapListener, mapOld.getListener());
    }

    @Test
    public void testGetSpawnPoint(){
        Tile redSpawnPoint = mapOld.getTile(new TilePosition(0,0));
        assertEquals(redSpawnPoint, mapOld.getSpawnPointFromColor(AmmoColor.RED));
    }

    @Test
    public void testSendMapData(){
        MockMapListener newMockMapListener = new MockMapListener();

        mapOld.setListener(newMockMapListener);

        mapOld.sendMapData();
    }

    @Test
    public void testGenerateMapData(){
        MapData mapData = mapOld.generateMapData();

        //assertEquals(mapID, mapData.getMapID());
        assertEquals(mapOld.getSpawnPointFromColor(AmmoColor.RED).getPosition(), mapData.getRedSpawnPoint());
        assertEquals(mapOld.getSpawnPointFromColor(AmmoColor.BLUE).getPosition(), mapData.getBlueSpawnPoint());
        assertEquals(mapOld.getSpawnPointFromColor(AmmoColor.YELLOW).getPosition(), mapData.getYellowSpawnPoint());
        assertEquals(new PlayerTilePair(new TilePosition(0,0),player.getColor()).getPlayer(),
                mapData.getPlayerLocations().get(0).getPlayer());
        assertEquals(new PlayerTilePair(new TilePosition(0,0),player.getColor()).getTile(),
                mapData.getPlayerLocations().get(0).getTile());
        for (Tile tile: mapOld.getAllTiles()){
            assertTrue(mapData.getTiles().contains(tile.getPosition()));
        }
    }

    @Test
    public void testGetDistance(){
        assertEquals(2, map.getDistance(player1.getTile(), player2.getTile()));
        assertEquals(4, map.getDistance(player1.getTile(), player3.getTile()));
    }
}