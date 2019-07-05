package it.polimi.deib.newdem.adrenaline.model.items;

import it.polimi.deib.newdem.adrenaline.model.map.Map;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestAmmoSet {

    /**
     * Testing straightforward fumcionality of AmmoSet
     */
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
    public void testGetTotalAmmos() {
        AmmoSet ammoSet = new AmmoSet(1,2,3);

        int totalAmmos = ammoSet.getTotalAmmos();

        if(totalAmmos != 6){
            fail();
        }

    }

    @Test
    public void TestConstructorPositive() {
        new AmmoSet(1, 2,3);
    }

    /**
     * Failing on negative inputs
     */
    @Test(expected = IllegalArgumentException.class)
    public void TestConstructorNegative() {
        new AmmoSet(-1, -1,-1);
        new AmmoSet(4,4, 4);
    }

    @Test
    public void testEquals(){
        Map map = null;

        AmmoSet ammoSet = new AmmoSet(1,1,1);

        assertNotEquals(ammoSet,map);
    }
}
