package it.polimi.deib.newdem.adrenaline.common.model.items;

import org.junit.Test;

import static org.junit.Assert.fail;

public class TestAmmoSet {


    @Test
    public void testGetRedAmmos() {
        AmmoSet ammoSet = new AmmoSet(1,2,3);

        int redAmmos = ammoSet.getRedAmmos();

        if(redAmmos != 1){
            fail();
        }

    }

    @Test
    public void testGetYellowAmmos() {
        AmmoSet ammoSet = new AmmoSet(1,2,3);

        int yellowAmmos = ammoSet.getYellowAmmos();

        if(yellowAmmos != 2){
            fail();
        }

    }

    @Test
    public void testGetBlueAmmos() {
        AmmoSet ammoSet = new AmmoSet(1,2,3);

        int blueAmmos = ammoSet.getBlueAmmos();

        if(blueAmmos != 3){
            fail();
        }

    }

    @Test
    public void TestGetTotalAmmos() {
        AmmoSet ammoSet = new AmmoSet(1,2,3);

        int totalAmmos = ammoSet.getTotalAmmos();

        if(totalAmmos != 6){
            fail();
        }

    }

    @Test
    public void testConstructorPositive() {
        new AmmoSet(1, 2,3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegative() {
        new AmmoSet(-1, -1,-1);
        new AmmoSet(4,4, 4);
    }
}
