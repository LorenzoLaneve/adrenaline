package it.polimi.deib.newdem.adrenaline.model.map;

public class TestingMapBuilder {
    public static Map getNewMap(Class c) {
        return Map.createMap("TestMap.json");
    }

    public static TilePosition getDropTilePos() {
        return new TilePosition(1,0);
    }
}
