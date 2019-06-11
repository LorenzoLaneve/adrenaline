package it.polimi.deib.newdem.adrenaline.model.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class DamageTrackFullExceptionTest {

    @Test(expected = DamageTrackFullException.class)
    public void testTrivialConstructor() throws Exception {
        throw new DamageTrackFullException();
    }

    @Test(expected = DamageTrackFullException.class)
    public void testStringConstructor() throws Exception {
        throw new DamageTrackFullException("Hello!");
    }
}