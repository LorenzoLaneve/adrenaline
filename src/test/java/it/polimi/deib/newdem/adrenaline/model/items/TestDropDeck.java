package it.polimi.deib.newdem.adrenaline.model.items;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestDropDeck {

    /**
     * Testing correct loading from json of the deck of drops.
     */
    @Test
    public void createNewDeck() {
        DropDeck dDeck = null;

        try{
            dDeck = DropDeck.fromJson("cards/drops.json");
        }catch (InvalidJSONException e){
            fail();
        }

        dDeck.createNewDeck();
    }

    @Test
    public void fromJson() {

        try{
            DropDeck dDeck = DropDeck.fromJson("cards/drops.json");
        }catch (InvalidJSONException e){
            fail();
        }
    }
}