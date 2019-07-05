package it.polimi.deib.newdem.adrenaline.model.items;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestWeaponDeck {

    /**
     * Testing the correct implementation for deck from json.
     */
    @Test
    public void fromJson() {
        try{
            WeaponDeck wDeck = WeaponDeck.makeFactoryFromJson("cards/weapons.json");
        }catch (Exception e){
            fail();
        }
    }
}