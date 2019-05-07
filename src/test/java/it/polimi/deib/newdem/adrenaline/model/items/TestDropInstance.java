package it.polimi.deib.newdem.adrenaline.model.items;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestDropInstance {

    @Test
    public void testGetAmmos() {
        AmmoSet ammoSet;
        ammoSet = new AmmoSet(1,2,0);

        boolean hasPowerUp = false;

        DropInstance dropInstance;
        dropInstance = new DropInstance(ammoSet, hasPowerUp );

        AmmoSet returnedAmmoSet;
        returnedAmmoSet = dropInstance.getAmmos();

        if(!ammoSet.equals(returnedAmmoSet)){
            fail();
        }
    }

    @Test
    public void testHasPowerUp() {
        AmmoSet ammoSet;
        ammoSet = new AmmoSet(2,1,0);

        boolean hasPowerUp = false;

        DropInstance dropInstance;
        dropInstance = new DropInstance(ammoSet, hasPowerUp );

        boolean returnedHasPowerUp = dropInstance.hasPowerUp();

        if(returnedHasPowerUp != hasPowerUp){
            fail();
        }
    }

    @Test
    public void testConstructorPositive(){
        AmmoSet ammoSet;
        ammoSet = new AmmoSet(1,1,0);

        boolean hasPowerUp = true;

        new DropInstance(ammoSet, hasPowerUp);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegative(){
        AmmoSet ammoSet1;
        ammoSet1 = new AmmoSet(1,1,1);
        boolean hasPowerUp1 = true;

        AmmoSet ammoSet2;
        ammoSet2 = new AmmoSet(2,2,2);
        boolean hasPowerUp2 = false;

        new DropInstance(ammoSet1, hasPowerUp1);
        new DropInstance(ammoSet2, hasPowerUp2);
    }

    @Test
    public void testEqualDropPositive(){
        AmmoSet ammoSet1;
        ammoSet1 = new AmmoSet(1,1,0);
        boolean hasPowerUp1 = true;

        AmmoSet ammoSet2;
        ammoSet2 = new AmmoSet(1,1,0);
        boolean hasPowerUp2 = true;

        DropInstance dropInstance1 = new DropInstance(ammoSet1, hasPowerUp1);
        DropInstance dropInstance2 = new DropInstance(ammoSet2, hasPowerUp2);

        assertEquals(dropInstance1,dropInstance2);
    }

    @Test
    public void testEqualDropNegative(){
        AmmoSet ammoSet1;
        ammoSet1 = new AmmoSet(1,1,0);
        boolean hasPowerUp1 = true;

        AmmoSet ammoSet2;
        ammoSet2 = new AmmoSet(1,0,1);
        boolean hasPowerUp2 = true;

        DropInstance dropInstance1 = new DropInstance(ammoSet1, hasPowerUp1);
        DropInstance dropInstance2 = new DropInstance(ammoSet2, hasPowerUp2);

        assertNotEquals(dropInstance1, dropInstance2);
    }

    @Test
    public void testCopyDrop(){
        AmmoSet ammoSet1;
        ammoSet1 = new AmmoSet(1,1,0);
        boolean hasPowerUp1 = true;

        DropInstance dropInstance1 = new DropInstance(ammoSet1, hasPowerUp1);

        DropInstance dropInstance2 = dropInstance1.copyDrop();

        assertEquals(dropInstance1, dropInstance2);
    }
}