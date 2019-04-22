package it.polimi.deib.newdem.adrenaline.common.model.map;

import it.polimi.deib.newdem.adrenaline.common.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.common.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.common.model.items.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestOrdinaryTile {

    OrdinaryTile tile;
    DropInstance d1;
    WeaponSet set;
    WeaponCard w1;
    TilePosition tilePosition;
    Map map;
    List<RoomTileSeeds> roomTileSeedsList;
    RoomTileSeeds roomTileSeeds;
    Room room;

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
        room.generateTiles();

        tile = new OrdinaryTile(room, tilePosition);

        d1 = new DropInstance(ammoSet, true);

        set = new WeaponSet();

        w1 = new MockWeapon("Max Aitken Beaverbrook");

    }

    @Test
    public void testConstructor() {
        //this method cannot fail
    }


    @Test
    public void testHasSpawnPoint() {
        assertFalse(tile.hasSpawnPoint());
    }

    @Test
    public void testGrabDrop() throws DropAlreadyPresentException {
        DropInstance currDrop;

        tile.addDrop(d1);

        currDrop = tile.grabDrop();

        assertTrue(currDrop.equalDrop(d1));
        assertTrue(tile.missingDrop());
    }

    @Test
    public void testInspectDrop() throws DropAlreadyPresentException {
        DropInstance currDrop;

        tile.addDrop(d1);

        currDrop = tile.inspectDrop();

        assertTrue(currDrop.equalDrop(d1));
        assertFalse(tile.missingDrop());
    }

    @Test (expected = NotSpawnPointTileException.class)
    public void testGrabWeapon() throws NotSpawnPointTileException {
        tile.grabWeapon(w1);
    }

    @Test
    public void testInspectWeaponSet(){
        List<WeaponCard> weaponSet;

        weaponSet = tile.inspectWeaponSet().getWeapons();

        assertEquals(weaponSet.size(), 0);
    }

    @Test
    public void testAddDropPositive() throws DropAlreadyPresentException{
        DropInstance currDrop;

        tile.addDrop(d1);

        currDrop = tile.inspectDrop();

        assertTrue(currDrop.equalDrop(d1));

    }

    @Test(expected = DropAlreadyPresentException.class)
    public void testAddDropNegativeOnAlreadyPresent() throws DropAlreadyPresentException{

        tile.addDrop(d1);
        tile.addDrop(d1);

    }

    @Test(expected = NotSpawnPointTileException.class)
    public void testAddWeapon() throws NotSpawnPointTileException{
        tile.addWeapon(w1);
    }


    @Test
    public void testMissingDropPositive() throws DropAlreadyPresentException{

        tile.addDrop(d1);
        tile.grabDrop();

        assertTrue(tile.missingDrop());

    }

    @Test
    public void testMissingDropNegative() throws  DropAlreadyPresentException{

        tile.addDrop(d1);

        assertFalse(tile.missingDrop());

    }


}