package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TestMapBuilder {

    /**
     * Testing the soundness of MapBuilder by calling the static Method in map that makes use of it.
     */
    @Test
    public void testConstructor(){
        MapBuilder mapBuilder = new MapBuilder("Map1_0.json");
        Map  map =  Map.createMap("Map1_0.json");
        assertEquals(new TilePosition(0,1),map.getSpawnPointFromColor(AmmoColor.RED).getPosition());
        assertEquals(new TilePosition(3,0),map.getSpawnPointFromColor(AmmoColor.YELLOW).getPosition());
        assertEquals(new TilePosition(2,2),map.getSpawnPointFromColor(AmmoColor.BLUE).getPosition());
    }

}