package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TestMapBuilder {

    @Test
    public void testConstructor(){
        MapBuilder mapBuilder = new MapBuilder(this.getClass().getClassLoader().getResource("Map1_0.json").getFile().replace("%20", " "));
        Map  map =  Map.createMap(this.getClass().getClassLoader().getResource("Map1_0.json").getFile().replace("%20", " "));
        assertEquals(new TilePosition(0,1),map.getSpawnPointFromColor(AmmoColor.RED).getPosition());
        assertEquals(new TilePosition(3,0),map.getSpawnPointFromColor(AmmoColor.YELLOW).getPosition());
        assertEquals(new TilePosition(2,2),map.getSpawnPointFromColor(AmmoColor.BLUE).getPosition());
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