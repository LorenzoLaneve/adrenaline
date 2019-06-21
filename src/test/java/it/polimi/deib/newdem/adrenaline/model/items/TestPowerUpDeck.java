package it.polimi.deib.newdem.adrenaline.model.items;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestPowerUpDeck {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void createNewDeck() {

        PowerUpDeck powerUpDeck = null;

        try{
            powerUpDeck = PowerUpDeck.fromJson(this.getClass().getClassLoader().getResource("powerupdeck.json").getFile().replace("%20", " "));
        }catch (InvalidJSONException e){
            fail();
        }

        Deck<PowerUpCard> actualDeck = powerUpDeck.createNewDeck();
    }

    @Test
    public void fromJson() {

        try{
            PowerUpDeck powerUpDeck = PowerUpDeck.fromJson(this.getClass().getClassLoader().getResource("powerupdeck.json").getFile().replace("%20", " "));
        }catch (InvalidJSONException e){
            fail();
        }

    }
}