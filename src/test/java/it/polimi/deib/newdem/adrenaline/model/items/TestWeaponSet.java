package it.polimi.deib.newdem.adrenaline.model.items;

import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.WeaponAlreadyPresentException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TestWeaponSet {

    private List<WeaponCard> set;

    MockWeapon w1;
    MockWeapon w2;
    MockWeapon w3;
    MockWeapon w4;

    WeaponSet weaponSet;

    /**
     * Testing basic functionality of weaponSet especially behavior when full, when trying to add a weapon already present or removing
     */
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
        w1 = new MockWeapon("Bindon Blood");
        w2 = new MockWeapon("Louis Mountbatten");
        w3 = new MockWeapon("Jennie Jerome");
        w4 = new MockWeapon("Thomas Edward Lawrence");

        weaponSet = new WeaponSet();
    }

    @Test
    public void testConstructor() {
        //this method cannot fail
    }

    @Test
    public void testGetWeapons() throws OutOfSlotsException, WeaponAlreadyPresentException {

        weaponSet.addWeapon(w1);
        weaponSet.addWeapon(w2);

        set = weaponSet.getWeapons();

        if(!set.contains(w1) || !set.contains(w2)){
            fail();
        }
    }

    @Test
    public void testAddWeaponPositive() throws OutOfSlotsException, WeaponAlreadyPresentException {

        weaponSet.addWeapon(w1);

        set = weaponSet.getWeapons();

        if(!set.contains(w1)){
            fail();
        }

    }

    @Test(expected = WeaponAlreadyPresentException.class)
    public void testAddWeaponNegativeOnIllegalArgument() throws OutOfSlotsException, WeaponAlreadyPresentException {

        weaponSet.addWeapon(w1);
        weaponSet.addWeapon(w1);

    }

    @Test(expected = OutOfSlotsException.class)
    public void testAddWeaponNegativeOnFullSet() throws OutOfSlotsException, WeaponAlreadyPresentException {

        weaponSet.addWeapon(w1);
        weaponSet.addWeapon(w2);
        weaponSet.addWeapon(w3);
        weaponSet.addWeapon(w4);

        set = weaponSet.getWeapons();

    }

    @Test
    public void testRemoveWeaponPositive() throws OutOfSlotsException, WeaponAlreadyPresentException {

        weaponSet.addWeapon(w1);
        weaponSet.addWeapon(w2);

        weaponSet.removeWeapon(w1);

        set = weaponSet.getWeapons();

        if(set.contains(w1)){
            fail();
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void testRemoveWeaponNegative() throws OutOfSlotsException, WeaponAlreadyPresentException {

        weaponSet.addWeapon(w1);

        weaponSet.removeWeapon(w2);
    }

    @Test
    public void testEqualWeaponSet() throws OutOfSlotsException, WeaponAlreadyPresentException{
        WeaponSet weaponSet1 = new WeaponSet();

        weaponSet1.addWeapon(w1);
        weaponSet1.addWeapon(w2);

        weaponSet.addWeapon(w1);
        weaponSet.addWeapon(w2);

        assertTrue(weaponSet.equalWeaponSet(weaponSet1));
    }

    @Test
    public void testCopySet(){
        assertTrue(weaponSet.equalWeaponSet(weaponSet.copyWeaponSet()));
    }
}