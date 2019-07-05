package it.polimi.deib.newdem.adrenaline.model.map;

public class TestingMapBuilder {
    /**
     * Utils class for testing purposes.
     * @return a map for testing.
     */
    public static Map getNewMap() {
        return Map.createMap("TestMap.json");
    }

    public static TilePosition getDropTilePos() {
        return new TilePosition(1,0);
    }
}
