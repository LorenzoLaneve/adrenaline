package it.polimi.deib.newdem.adrenaline.common.model.items;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestDeck {

    @Test
    public void testConstructorPositive() {
        List<String> ls = new ArrayList<String>(Arrays.asList("Str1", "Str2"));

        Deck<String> d = new Deck(ls);

        String s;

        while(!d.isEmpty()){
            if(!ls.contains(d.extract())){
                fail();
            }
        }
    }

    @Test
    public void testConstructorNegative()
    {
        List<String> ls = new ArrayList<String>();

        try{
            Deck<String> d = new Deck(ls);
            fail();
        }
        catch (IllegalArgumentException e){
            assertTrue(true);
        }
    }


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeException()
    {
        List<String> ls = new ArrayList<String>();
        Deck<String> d = new Deck(ls);
        fail();
    }
}
