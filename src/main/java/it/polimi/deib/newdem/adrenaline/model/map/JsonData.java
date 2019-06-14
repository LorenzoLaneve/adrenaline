package it.polimi.deib.newdem.adrenaline.model.map;

import java.util.List;

public class JsonData {
    private SpawnPointTileDict spawnPointTileDict;
    private int[][][] roomListInt;
    private int[][][] adjacencyList;

    public JsonData(SpawnPointTileDict spawnPointTileDict, int[][][] roomListInt,  int[][][] adjacencyList){
        this.adjacencyList = adjacencyList;
        this.roomListInt = roomListInt;
        this.spawnPointTileDict = spawnPointTileDict;
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

}
