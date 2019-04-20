package it.polimi.deib.newdem.adrenaline.common.model.map;

import it.polimi.deib.newdem.adrenaline.common.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.common.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.common.model.items.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TestOrdinaryTile {

    OrdinaryTile tile;
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

        tile = new OrdinaryTile();

        d1 = new DropInstance(ammoSet, true);

        set = new WeaponSet();

        w1 = new MockWeapon("Max Aitken Beaverbrook");

        tile = new OrdinaryTile();

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

    @Test
    public void testGrabWeapons() {

        List<WeaponCard> weaponSet;

        weaponSet = tile.inspectWeaponSet().getWeapons();

        assertEquals(weaponSet.size(), 0);
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