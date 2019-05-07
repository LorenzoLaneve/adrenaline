package it.polimi.deib.newdem.adrenaline.model.map;

import java.util.ArrayList;
import java.util.List;

public class MapBuilder {


    private String mapFile;
    private List<ArrayList> spawnPointTileIntList;
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

        ArrayList<Integer> tileInt1 = new ArrayList<>(2);
        ArrayList<Integer> tileInt2 = new ArrayList<>(2);

        tileInt1.set(0,-1);
        tileInt1.set(1,-1);
        tileInt2.set(0,-1);
        tileInt2.set(1,-1);

        //TODO json

        if(matrixMap[tileInt1.get(0)][tileInt1.get(1)] == null){
            if(spawnPointTileIntList.contains(tileInt1)){
                matrixMap[tileInt1.get(0)][tileInt1.get(1)] = new SpawnPointTile(new TilePosition(tileInt1.get(0), tileInt1.get(1)));

            }
            else{
                matrixMap[tileInt1.get(0)][tileInt1.get(1)] = new OrdinaryTile(new TilePosition(tileInt1.get(0), tileInt1.get(1)));
            }
        }

        if(matrixMap[tileInt2.get(0)][tileInt2.get(1)] == null){
            if(spawnPointTileIntList.contains(tileInt2)){
                matrixMap[tileInt2.get(0)][tileInt2.get(1)] = new SpawnPointTile(new TilePosition(tileInt2.get(0), tileInt2.get(1)));

            }
            else{
                matrixMap[tileInt2.get(0)][tileInt2.get(1)] = new OrdinaryTile(new TilePosition(tileInt2.get(0), tileInt2.get(1)));
            }
        }

        matrixMap[tileInt2.get(0)][tileInt2.get(1)].addAdjacentTiles(matrixMap[tileInt1.get(0)][tileInt1.get(1)]);
        matrixMap[tileInt1.get(0)][tileInt1.get(1)].addAdjacentTiles(matrixMap[tileInt2.get(0)][tileInt2.get(1)]);

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

        List<ArrayList> room = new ArrayList<>();

        roomListInt.add(room);

        return roomListInt;
    }

    /**Extracts rooms' spawn points for the json file given in the constructor.
     *
     * @return the list of rooms' spawn points to be used in buildMatrixMap.
     */
    public List<ArrayList> extractSpawnPointList(){
        List<ArrayList> spIntList;
        spIntList = new ArrayList<>();

        ArrayList<Integer> spawnPointTile = new ArrayList<>(2);

        //TODO json

        spIntList.add(spawnPointTile);
        return spIntList;
    }
    /**Used to bind rooms and tiles together in buildMatrixMap.
     *
     */
    public void bindTiles(){
        for(List<ArrayList> roomInt : roomListInt){
            Room room = new ConcreteRoom();
            rooms.add(room);
            for(ArrayList<Integer> intPos : roomInt){
                matrixMap[intPos.get(0)][intPos.get(1)].setRoom(room);
                room.addTiles(matrixMap[intPos.get(0)][intPos.get(1)]);
            }
        }

    }



}
