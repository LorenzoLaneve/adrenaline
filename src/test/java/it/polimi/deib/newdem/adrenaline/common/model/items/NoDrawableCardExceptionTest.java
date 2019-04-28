package it.polimi.deib.newdem.adrenaline.common.model.items;

import org.junit.Test;

import static org.junit.Assert.*;

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