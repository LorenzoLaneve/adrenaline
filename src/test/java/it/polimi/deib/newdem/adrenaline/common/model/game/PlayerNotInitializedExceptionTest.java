package it.polimi.deib.newdem.adrenaline.common.model.game;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerNotInitializedExceptionTest {

    @Test(expected = PlayerNotInitializedException.class)
    public void testDefaultConstructor() {
        throw new PlayerNotInitializedException();
    }

    @Test(expected = PlayerNotInitializedException.class)
    public void testConstructorString() {
        throw new PlayerNotInitializedException("Hello!");
    }

}