package it.polimi.deib.newdem.adrenaline.model.map;

import com.google.gson.Gson;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MapBuilder {

    private JsonData.SpawnPointTileDict spawnPointTileDict;
    private int[][][] roomListInt;
    private int[][][] adjacencyList;
    private final List<Room> rooms;
    private Tile[][] matrixMap;


    /**Creates a new {@code MapBuilder} instance taking a json encoded version of the map as input.
     * 
     * @param mapJsonData json encoded version of the map as input.
     */
    public MapBuilder(String mapJsonData){

        try (FileReader reader = new FileReader(mapJsonData)) {

            Gson gson = new Gson();
            JsonData jsonData = gson.fromJson(reader,JsonData.class );

            adjacencyList = jsonData.getAdjacencyList();
            roomListInt = jsonData.getRoomListInt();
            spawnPointTileDict = jsonData.getSpawnPointTileDict();

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.matrixMap = new Tile[99][99];

        this.matrixMap = buildMatrixMap();

        this.rooms = buildRooms();

    }

    /**Method used to create the  first input to the Map's constructor: matrixMap.
     *
     * @return matrixMap an array of arrays of tiles.
     */
    private Tile[][] buildMatrixMap(){

        for(int[][] adjacencyCouple : adjacencyList){


            int[] tileInt1 = adjacencyCouple[0];
            int[] tileInt2 = adjacencyCouple[1];

            if(matrixMap[tileInt1[0]][tileInt1[1]] == null){
                if(isSpawnPointTileInt(tileInt1)){
                    matrixMap[tileInt1[0]][tileInt1[1]] = new SpawnPointTile(new TilePosition(tileInt1[0], tileInt1[1]));

                }
                else{
                    matrixMap[tileInt1[0]][tileInt1[1]] = new OrdinaryTile(new TilePosition(tileInt1[0], tileInt1[1]));
                }
            }

            if(matrixMap[tileInt2[0]][tileInt2[1]] == null){
                if(isSpawnPointTileInt(tileInt2)){
                    matrixMap[tileInt2[0]][tileInt2[1]] = new SpawnPointTile(new TilePosition(tileInt2[0], tileInt2[1]));

                }
                else{
                    matrixMap[tileInt2[0]][tileInt2[1]] = new OrdinaryTile(new TilePosition(tileInt2[0], tileInt2[1]));
                }
            }

            matrixMap[tileInt2[0]][tileInt2[1]].addAdjacentTiles(matrixMap[tileInt1[0]][tileInt1[1]]);
            matrixMap[tileInt1[0]][tileInt1[1]].addAdjacentTiles(matrixMap[tileInt2[0]][tileInt2[1]]);

        }

        return matrixMap;
    }
    /**Used to bind rooms and tiles together in buildMatrixMap.
     *
     */
    private List<Room> buildRooms() {
        List<Room> tempRooms = new ArrayList<Room>();

        for (int[][] roomInt : roomListInt) {
            Room room = new ConcreteRoom();
            tempRooms.add(room);
            for (int[] intPos : roomInt) {
                matrixMap[intPos[0]][intPos[1]].setRoom(room);
                room.addTiles(matrixMap[intPos[0]][intPos[1]]);
            }
        }

        return tempRooms;
    }

    private boolean isSpawnPointTileInt(int[] tileInt){
            boolean result = false;

            int[] red = spawnPointTileDict.getRed();
            int[] blue = spawnPointTileDict.getBlue();
            int[] yellow = spawnPointTileDict.getYellow();

            if(tileInt.equals(red) || tileInt.equals(blue) || tileInt.equals(yellow)){
                result = true;
            }

            return result;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Tile[][] getMatrixMap() {
        return matrixMap;
    }
}

