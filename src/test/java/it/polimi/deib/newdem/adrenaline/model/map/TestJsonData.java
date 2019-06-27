package it.polimi.deib.newdem.adrenaline.model.map;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestJsonData {

    private SpawnPointTileDict spawnPointTileDict;
    private int[][][] roomListInt = null;
    private int[][][] adjacencyList = null;
    int[] red = {1,2};
    int[] blue = {2,2};
    int[] yellow = {1,1};
    private String mapID = null;

    @Test
    public void testConstructor(){
        try{
            spawnPointTileDict = new SpawnPointTileDict(red, blue, yellow);
            JsonData jData = new JsonData(spawnPointTileDict, roomListInt, adjacencyList, mapID );
            assertEquals(spawnPointTileDict, jData.getSpawnPointTileDict());
            assertEquals(roomListInt, jData.getRoomListInt());
            assertEquals(adjacencyList, jData.getAdjacencyList());
            assertEquals(red, jData.getSpawnPointTileDict().getRed());
            assertEquals(blue, jData.getSpawnPointTileDict().getBlue());
            assertEquals(yellow, jData.getSpawnPointTileDict().getYellow());
        }catch (Exception e){
            fail();
        }
    }
}