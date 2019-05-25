package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.model.items.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestSpawnPointTile {

    Tile tile;
    TilePosition tilePosition;
    DropInstance d1;
    WeaponSet set;
    WeaponCard w1;

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

        @Override
        public int getCardID() {
            return 0;
        }
    }

    @Before
    public void initTest(){
        AmmoSet ammoSet = new AmmoSet(1,1,0);

        MapBuilder mapBuilder = new MapBuilder(this.getClass().getClassLoader().getResource("JsonData.json").getFile().replace("%20", " "));

        Map map = mapBuilder.buildMap();

        tilePosition = new TilePosition(0,0);

        tile = map.getTile(tilePosition);

        d1 = new DropInstance(ammoSet, true);

        set = new WeaponSet();

        w1 = new MockWeapon("Max Aitken Beaverbrook");

    }

    @Test
    public void testConstructor(){

    }

    @Test
    public void testHasSpawnPoint() {
        assertTrue(tile.hasSpawnPoint() );
    }

    @Test
    public void inspectDrop() {
    }

    @Test
    public void testInspectWeaponSetPositive() throws OutOfSlotsException, WeaponAlreadyPresentException, NotSpawnPointTileException {
        tile.addWeapon(w1);

        if(!(tile.inspectWeaponSet().getWeapons().contains(w1))){
            fail();
        }
    }

    @Test(expected = NotOrdinaryTileException.class)
    public void testAddDropPositive() throws NotOrdinaryTileException, DropAlreadyPresentException {
        tile.addDrop(d1);
    }

    @Test
    public void testAddWeaponPositive() throws OutOfSlotsException, WeaponAlreadyPresentException, NotSpawnPointTileException {
        if(!(tile.inspectWeaponSet().getWeapons().contains(w1))) {
            tile.addWeapon(w1);

            if (!(tile.inspectWeaponSet().getWeapons().contains(w1))) {
                fail();
            }
        }
    }

    @Test (expected = WeaponAlreadyPresentException.class)
    public void testAddWeaponNegative() throws OutOfSlotsException, WeaponAlreadyPresentException, NotSpawnPointTileException {
        tile.addWeapon(w1);
        tile.addWeapon(w1);
    }


    @Test (expected = NotOrdinaryTileException.class)
    public void testGrabDrop() throws NotOrdinaryTileException{
        tile.grabDrop();
    }

    @Test
    public void testGrabWeaponPositive() throws OutOfSlotsException, WeaponAlreadyPresentException, NotSpawnPointTileException {
        tile.addWeapon(w1);
        assertEquals(w1, tile.grabWeapon(w1));

    }

    @Test
    public void testMissingDrop() {
        assertTrue(tile.missingDrop());
    }
}