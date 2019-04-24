package it.polimi.deib.newdem.adrenaline.common.model.map;

import it.polimi.deib.newdem.adrenaline.common.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.common.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.common.model.items.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestSpawnPointTile {

    SpawnPointTile tile;
    DropInstance d1;
    WeaponSet set;
    WeaponCard w1;
    Room room;
    RoomTileSeeds roomTileSeeds;
    List<RoomTileSeeds> roomTileSeedsList;
    Map map;
    TilePosition tilePosition;


    public class MockWeapon implements WeaponCard{
        private String code;

        MockWeapon(String code){
            this.code = code;
        }

        public String getCode(){
            return code;
        }

        @Override
        public PaymentInvoice getPickupPrice() {
            return null;
        }

        @Override
        public PaymentInvoice getReloadPrice() {
            return null;
        }

        @Override
        public Effect getEffect() {
            return null;
        }

        @Override
        public Weapon makeWeapon() {
            return null;
        }
    }

    @Before
    public void testInit(){
        AmmoSet ammoSet = new AmmoSet(1,1,0);

        TilePosition topLeft;
        TilePosition topRight;
        TilePosition bottomRight;
        TilePosition bottomLeft;
        TilePosition spawnPointTilePosition;

        topLeft = new TilePosition(0,9);
        topRight = new TilePosition(6,9);
        bottomRight = new TilePosition(6,0);
        bottomLeft = new TilePosition(0,0);
        spawnPointTilePosition = new TilePosition(0,2);

        roomTileSeeds = new RoomTileSeeds(topLeft,topRight,bottomRight,bottomLeft, spawnPointTilePosition);

        roomTileSeedsList = new ArrayList<>();
        roomTileSeedsList.add(roomTileSeeds);

        map = new ConcreteMap(roomTileSeedsList);
        map.generateRooms();

        room = new ConcreteRoom(map, roomTileSeeds);
        room.getTiles();

        tile = new SpawnPointTile(room, tilePosition);

        d1 = new DropInstance(ammoSet, true);

        set = new WeaponSet();

        w1 = new MockWeapon("Mad Jack");


    }

    @Test
    public void testHasSpawnPoint() {
        assertTrue(tile.hasSpawnPoint() );
    }

    @Test
    public void testInspectDrop() {
        assertNull(tile.inspectDrop());
    }

    @Test
    public void testInspectWeaponSetPositive() throws OutOfSlotsException, WeaponAlreadyPresentException {
        tile.addWeapon(w1);

        if(!(tile.inspectWeaponSet().getWeapons().contains(w1))){
            fail();
        }
    }

    @Test(expected = NotOrdinaryTileException.class)
    public void testAddDropPositive() throws NotOrdinaryTileException {
        tile.addDrop(d1);
    }

    @Test
    public void testAddWeaponPositive() throws OutOfSlotsException, WeaponAlreadyPresentException {
        if(!(tile.inspectWeaponSet().getWeapons().contains(w1))) {
            tile.addWeapon(w1);

            if (!(tile.inspectWeaponSet().getWeapons().contains(w1))) {
                fail();
            }
        }
    }

    @Test (expected = WeaponAlreadyPresentException.class)
    public void testAddWeaponNegative() throws OutOfSlotsException,WeaponAlreadyPresentException {
        tile.addWeapon(w1);
        tile.addWeapon(w1);
    }

    @Test (expected = NotOrdinaryTileException.class)
    public void testGrabDrop() throws NotOrdinaryTileException{
        tile.grabDrop();
    }

    @Test
    public void testGrabWeaponPositive()  throws OutOfSlotsException, WeaponAlreadyPresentException{
        tile.addWeapon(w1);
        assertEquals(w1, tile.grabWeapon(w1));

    }

    @Test
    public void testGrabWeaponNegative() throws OutOfSlotsException, WeaponAlreadyPresentException{
        tile.addWeapon(w1);
        assertEquals(w1, tile.grabWeapon(w1));

        if (tile.inspectWeaponSet().getWeapons().contains(w1)) {
            fail();
        }
    }

    @Test
    public void testMissingDrop() {
        assertTrue(tile.missingDrop());
    }

}