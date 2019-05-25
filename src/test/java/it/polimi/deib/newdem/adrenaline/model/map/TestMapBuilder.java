package it.polimi.deib.newdem.adrenaline.model.map;

import org.junit.Test;


public class TestMapBuilder {

    @Test
    public void testConstructor(){
        MapBuilder mapBuilder = new MapBuilder(this.getClass().getClassLoader().getResource("Map0_0.json").getFile());
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