package it.polimi.deib.newdem.adrenaline.model.items;

import org.junit.Test;

public class NoDrawableCardExceptionTest {

    @Test(expected = NoDrawableCardException.class)
    public void testDefaultConstructor() throws NoDrawableCardException {
        throw new NoDrawableCardException();
    }

    @Test(expected = NoDrawableCardException.class)
    public void testStringConstructor() throws NoDrawableCardException {
        throw new NoDrawableCardException("Hello!");
    }
}