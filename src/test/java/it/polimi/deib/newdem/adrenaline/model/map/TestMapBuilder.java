package it.polimi.deib.newdem.adrenaline.model.map;

import org.junit.Test;

import java.util.List;

public class TestMapBuilder {

    @Test
    public void testConstructor(){



        MapBuilder mapBuilder = new MapBuilder(this.getClass().getClassLoader().getResource("JsonData.json").getFile());

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