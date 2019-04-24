package it.polimi.deib.newdem.adrenaline.common.model.map;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestRoomTileSeeds {
    RoomTileSeeds roomTileSeeds;

    TilePosition topLeft;
    TilePosition topRight;
    TilePosition bottomRight;
    TilePosition bottomLeft;
    TilePosition spawnPointTilePosition;

    @Before
    public void initTest(){

        topLeft = new TilePosition(0,9);
        topRight = new TilePosition(6,9);
        bottomRight = new TilePosition(6,0);
        bottomLeft = new TilePosition(0,0);
        spawnPointTilePosition = new TilePosition(0,2);

        roomTileSeeds = new RoomTileSeeds(topLeft,topRight,bottomRight,bottomLeft, spawnPointTilePosition);
    }

    @Test
    public void testGetSpawnPointTilePosition() {
        assertEquals(roomTileSeeds.getSpawnPointTilePosition(),spawnPointTilePosition);
    }

    @Test
    public void testGetCornerBottomLeft() {
        assertEquals(roomTileSeeds.getCornerBottomLeft(),bottomLeft);
    }

    @Test
    public void testGetCornerTopLeft() {
        assertEquals(roomTileSeeds.getCornerBottomRight(),bottomRight);
    }

    @Test
    public void testGetCornerTopRight() {
        assertEquals(roomTileSeeds.getCornerTopLeft(),topLeft);
    }

    @Test
    public void testGetCornerBottomRight() {
        assertEquals(roomTileSeeds.getCornerTopRight(),topRight);
    }

    @Test
    public void testContainsTilePosition() {
        TilePosition tilePosition = new TilePosition(2,3);

        assertTrue(roomTileSeeds.containsTilePosition(tilePosition));
    }

    @Test
    public void testGetLeftX() {
        assertEquals(0, roomTileSeeds.getLeftX());
    }

    @Test
    public void testGetRightX() {
        assertEquals(6, roomTileSeeds.getRightX());
    }

    @Test
    public void testGetTopY() {
        assertEquals(9, roomTileSeeds.getTopY());
    }

    @Test
    public void testGetBottomY() {
        assertEquals(0, roomTileSeeds.getBottomY());
    }
}