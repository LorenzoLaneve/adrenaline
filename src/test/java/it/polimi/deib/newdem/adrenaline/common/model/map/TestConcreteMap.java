package it.polimi.deib.newdem.adrenaline.common.model.map;

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

}