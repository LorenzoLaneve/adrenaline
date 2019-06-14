package it.polimi.deib.newdem.adrenaline.model.items;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestWeaponDeck {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void createNewDeck() {

    }

    @Test
    public void fromJson() {
        try{
            WeaponDeck wDeck = WeaponDeck.fromJson(
                    this.getClass().getClassLoader().getResource("basedeck.json").getFile().replace("%20", " ")
            );
        }catch (Exception e){
            fail();
        }
    }
}