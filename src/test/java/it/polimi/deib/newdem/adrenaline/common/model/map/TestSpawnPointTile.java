package it.polimi.deib.newdem.adrenaline.common.model.map;

import it.polimi.deib.newdem.adrenaline.common.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.common.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.common.model.items.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestSpawnPointTile {

    SpawnPointTile tile;
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
    }

    @Before
    public void testInit(){
        AmmoSet ammoSet = new AmmoSet(1,1,0);

        tile = new SpawnPointTile();

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