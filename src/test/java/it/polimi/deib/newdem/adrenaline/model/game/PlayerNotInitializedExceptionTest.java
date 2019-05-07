package it.polimi.deib.newdem.adrenaline.model.game;

import org.junit.Test;

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