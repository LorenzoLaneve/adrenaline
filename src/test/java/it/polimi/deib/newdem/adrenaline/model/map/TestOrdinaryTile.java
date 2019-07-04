package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TestOrdinaryTile {

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
        public Weapon makeWeapon(Player player) {
            return null;
        }

        @Override
        public int getCardID() {
            return 0;
        }
    }


    @Before
    public void initTest(){

        MapBuilder mapBuilder = new MapBuilder("TestMap.json");

        Map map = mapBuilder.buildMap();

        AmmoSet ammoSet = new AmmoSet(1,1,0);

        tilePosition = new TilePosition(1,0);

        tile = map.getTile(tilePosition);

        d1 = new DropInstance(ammoSet, true);

        set = new WeaponSet();

        w1 = new MockWeapon("Max Aitken Beaverbrook");

    }

    @Test
    public void testConstructor(){
        Tile ordinaryTile = new OrdinaryTile(new TilePosition(0,0));
    }

    @Test
    public void testHasSpawnPoint() {
        assertFalse(tile.hasSpawnPoint());
    }

    @Test
    public void testInspectDrop() throws DropAlreadyPresentException, NotOrdinaryTileException {
        DropInstance currDrop;

        tile.addDrop(d1);

        currDrop = tile.inspectDrop();

        assertEquals(currDrop,d1);
        assertFalse(tile.missingDrop());
    }

    @Test
    public void testInspectWeaponSet() {
        List<WeaponCard> weaponSet;

        weaponSet = tile.inspectWeaponSet().getWeapons();

        assertEquals(0,weaponSet.size());
    }

    @Test
    public void testAddDrop() throws NotOrdinaryTileException, DropAlreadyPresentException {
        DropInstance currDrop;

        tile.addDrop(d1);

        currDrop = tile.inspectDrop();

        assertEquals(d1, currDrop);
    }

    @Test(expected = NotSpawnPointTileException.class)
    public void testAddWeapon() throws NotSpawnPointTileException, WeaponAlreadyPresentException, OutOfSlotsException {
        tile.addWeapon(w1);
    }

    @Test
    public void testGrabDrop() throws NotOrdinaryTileException, DropAlreadyPresentException {
        DropInstance currDrop;

        tile.addDrop(d1);

        currDrop = tile.grabDrop();

        assertEquals(currDrop,d1);
        assertTrue(tile.missingDrop());
    }

    @Test (expected = NotSpawnPointTileException.class)
    public void testGrabWeapon() throws NotSpawnPointTileException {
        tile.grabWeapon(w1);
    }

    @Test
    public void testMissingDropPositive() throws DropAlreadyPresentException, NotOrdinaryTileException {

        tile.addDrop(d1);
        tile.grabDrop();

        assertTrue(tile.missingDrop());

    }
}