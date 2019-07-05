package it.polimi.deib.newdem.adrenaline.model.items;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestPowerUpDeck {
    /**
     * Testing correct loading of deck from json.
     */
    @Test
    public void createNewDeck() {

        PowerUpDeck powerUpDeck = null;

        try{
            powerUpDeck = PowerUpDeck.fromJson("cards/powerups.json");
        }catch (InvalidJSONException e){
            fail();
        }

        powerUpDeck.createNewDeck();
    }

    @Test
    public void fromJson() {

        try{
            PowerUpDeck powerUpDeck = PowerUpDeck.fromJson("cards/powerups.json");
        }catch (InvalidJSONException e){
            fail();
        }

    }
}