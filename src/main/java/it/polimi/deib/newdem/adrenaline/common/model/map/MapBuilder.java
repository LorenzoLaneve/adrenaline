package it.polimi.deib.newdem.adrenaline.common.model.map;

import java.util.ArrayList;
import java.util.List;

public class MapBuilder {


    private String mapFile;
    private List<int[]> spawnPointTileIntList;
    private List<List> roomListInt;
    private List<Room> rooms;
    private Tile[][] matrixMap;

    /**Creates a new {@code MapBuilder} instance taking a json encoded version of the map as input.
     * 
     * @param mapFile json encoded version of the map as input.
     */
    public MapBuilder(String mapFile){
        this.mapFile = mapFile;
        this.matrixMap = new Tile[99][99];
        rooms = new ArrayList<>();
    }

    /**Method used to create the  first input to the Map's constructor: matrixMap.
     *
     * @return matrixMap an array of arrays of tiles.
     */
    public Tile[][] buildMatrixMap(){

        this.roomListInt = extractRoomList();
        this.spawnPointTileIntList = extractSpawnPointList();

        int[] tileInt1 = new int[2];
        int[] tileInt2 = new int[2];

        //TODO json

        if(matrixMap[tileInt1[0]][tileInt1[1]] == null){
            if(spawnPointTileIntList.contains(tileInt1)){
                matrixMap[tileInt1[0]][tileInt1[1]] = new SpawnPointTile(new TilePosition(tileInt1[0],tileInt1[1]));

            }
            else{
                matrixMap[tileInt1[0]][tileInt1[1]] = new OrdinaryTile(new TilePosition(tileInt1[0],tileInt1[1]));
            }
        }

        if(matrixMap[tileInt2[0]][tileInt2[1]] == null){
            if(spawnPointTileIntList.contains(tileInt2)){
                matrixMap[tileInt2[0]][tileInt2[1]] = new SpawnPointTile(new TilePosition(tileInt2[0],tileInt2[1]));

            }
            else{
                matrixMap[tileInt2[0]][tileInt2[1]] = new OrdinaryTile(new TilePosition(tileInt2[0],tileInt2[1]));
            }
        }

        matrixMap[tileInt2[0]][tileInt2[1]].addAdjacentTiles(matrixMap[tileInt1[0]][tileInt1[1]]);
        matrixMap[tileInt1[0]][tileInt1[1]].addAdjacentTiles(matrixMap[tileInt2[0]][tileInt2[1]]);

        bindTiles();

        return matrixMap;
    }

    /**Extracts room's information for the json file given in the constructor.
     *
     * @return the list of rooms to be given to the map constructor.
     */
    public List<List> extractRoomList(){
        roomListInt = new ArrayList<>();

        //TODO json

        List<int[]> room = new ArrayList<>();

        roomListInt.add(room);

        return roomListInt;
    }

    /**Extracts rooms' spawn points for the json file given in the constructor.
     *
     * @return the list of rooms' spawn points to be used in buildMatrixMap.
     */
    public List<int[]> extractSpawnPointList(){
        List<int[]> spIntList;
        spIntList = new ArrayList<>();

        int[] spawnPointTile = new int[2];

        //TODO json

        spIntList.add(spawnPointTile);
        return spIntList;
    }
    /**Used to bind rooms and tiles together in buildMatrixMap.
     *
     */
    public void bindTiles(){
        for(List<int[]> roomInt : roomListInt){
            Room room = new ConcreteRoom();
            rooms.add(room);
            for(int[] intPos : roomInt){
                matrixMap[intPos[0]][intPos[1]].setRoom(room);
                room.addTiles(matrixMap[intPos[0]][intPos[1]]);
            }
        }

    }



}
