package it.polimi.deib.newdem.adrenaline.model.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class DamageTrackEmptyExceptionTest {

    @Test(expected = DamageTrackEmptyException.class)
    public void testTrivialConstructor() throws Exception {
        throw new DamageTrackEmptyException();
    }

    @Test(expected = DamageTrackEmptyException.class)
    public void testStringCosntructor() throws Exception {
        throw new DamageTrackEmptyException("Hello!");
    }
}