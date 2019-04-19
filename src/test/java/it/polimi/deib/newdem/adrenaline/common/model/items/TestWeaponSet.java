package it.polimi.deib.newdem.adrenaline.common.model.items;

import it.polimi.deib.newdem.adrenaline.common.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.common.controller.effects.PaymentInvoice;
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
    public void initTest(){
        w1 = new MockWeapon("Bindon Blood");
        w2 = new MockWeapon("Louis Mountbatten");
        w3 = new MockWeapon("Jennie Jerome");
        w4 = new MockWeapon("Thomas Edward Lawrence");

        weaponSet = new WeaponSet();
    }

    @Test
    public void TestConstructor() {
        //this method cannot fail
    }

    @Test
    public void TestGetWeapons() throws OutOfSlotsException {

        weaponSet.addWeapon(w1);
        weaponSet.addWeapon(w2);

        set = weaponSet.getWeapons();

        if(!set.contains(w1) || !set.contains(w2)){
            fail();
        }
    }

    @Test
    public void TestAddWeaponPositive() throws OutOfSlotsException {

        weaponSet.addWeapon(w1);

        set = weaponSet.getWeapons();

        if(!set.contains(w1)){
            fail();
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void TestAddWeaponNegativeOnIllegalArgument() throws OutOfSlotsException {

        weaponSet.addWeapon(w1);
        weaponSet.addWeapon(w1);

    }

    @Test(expected = OutOfSlotsException.class)
    public void TestAddWeaponNegativeOnFullSet() throws OutOfSlotsException {

        weaponSet.addWeapon(w1);
        weaponSet.addWeapon(w2);
        weaponSet.addWeapon(w3);
        weaponSet.addWeapon(w4);

        set = weaponSet.getWeapons();

    }

    @Test
    public void TestRemoveWeaponPositive() throws OutOfSlotsException {

        weaponSet.addWeapon(w1);
        weaponSet.addWeapon(w2);

        weaponSet.removeWeapon(w1);

        set = weaponSet.getWeapons();

        if(set.contains(w1)){
            fail();
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void TestRemoveWeaponNegative() throws OutOfSlotsException {

        weaponSet.addWeapon(w1);

        weaponSet.removeWeapon(w2);
    }
}