package it.polimi.deib.newdem.adrenaline.model.items;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestDropCardImpl {

    DropCard card;
    AmmoSet ammoSet;
    boolean hasPowerUp;
    int id;

    @Before
    public void setUp() throws Exception {
        ammoSet =new AmmoSet(0,1,2);
        hasPowerUp = false;
        id = 1;

    }

    @Test
    public void getAmmoSet() {
        DropCard card = new DropCardImpl(id, ammoSet, hasPowerUp);
        AmmoSet returnedAmmoSet = card.getAmmoSet();

        assertEquals(ammoSet.getRedAmmos(), returnedAmmoSet.getRedAmmos());
        assertEquals(ammoSet.getBlueAmmos(), returnedAmmoSet.getBlueAmmos());
        assertEquals(ammoSet.getYellowAmmos(), returnedAmmoSet.getYellowAmmos());
    }

    @Test
    public void hasPowerUp() {
        DropCard card = new DropCardImpl(id, ammoSet, hasPowerUp);
        assertFalse(card.hasPowerUp());
    }

    @Test
    public void getID() {
        DropCard card = new DropCardImpl(id, ammoSet, hasPowerUp);

        assertEquals(id, card.getID());
    }
}