package it.polimi.deib.newdem.adrenaline.model.map;

import org.junit.Test;

import java.util.List;

public class TestMapBuilder {

    @Test
    public void testConstructor(){

        MapBuilder mapBuilder = new MapBuilder("C:\\Users\\Lopardo-PC\\Desktop\\Java\\Adrenaline\\repo\\src\\main\\java\\it\\polimi\\deib\\newdem\\adrenaline\\model\\map\\JsonData.json");

        List<Room> rooms = mapBuilder.getRooms();
        Tile[][] matrixMap = mapBuilder.getMatrixMap();
    }


    @Test
    public void testBuildMatrixMap() {
    }

    @Test
    public void testExtractRoomList() {
    }

    @Test
    public void testExtractSpawnPointList() {
    }

    @Test
    public void testBindTiles() {
    }
}