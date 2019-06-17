package it.polimi.deib.newdem.adrenaline.model.items;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestDropDeck {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void createNewDeck() {
        DropDeck dDeck = null;

        try{
            dDeck = DropDeck.fromJson(
                    this.getClass().getClassLoader().getResource("droptiledeck.json").getFile().replace("%20", " ")
            );
        }catch (InvalidJSONException e){
            fail();
        }

        Deck<DropCard> actualDeck = dDeck.createNewDeck();
    }

    @Test
    public void fromJson() {

        try{
            DropDeck dDeck = DropDeck.fromJson(
                    this.getClass().getClassLoader().getResource("droptiledeck.json").getFile().replace("%20", " ")
            );
        }catch (InvalidJSONException e){
            fail();
        }
    }
}