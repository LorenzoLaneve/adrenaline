package it.polimi.deib.newdem.adrenaline.model.map;

import java.util.List;

public class JsonData {
    private SpawnPointTileDict spawnPointTileDict;
    private int[][][] roomListInt;
    private int[][][] adjacencyList;
    private String mapID;

    public JsonData(SpawnPointTileDict spawnPointTileDict, int[][][] roomListInt,  int[][][] adjacencyList, String mapID){
        this.adjacencyList = adjacencyList;
        this.roomListInt = roomListInt;
        this.spawnPointTileDict = spawnPointTileDict;
        this.mapID = mapID;
    }

    public int[][][] getRoomListInt() {
        return roomListInt;
    }

    public SpawnPointTileDict getSpawnPointTileDict() {
        return spawnPointTileDict;
    }

    public int[][][] getAdjacencyList() {
        return adjacencyList;
    }

    public String getMapID() {
        return mapID;
    }
}
