package it.polimi.deib.newdem.adrenaline.model.game.player;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerNotInitializedException;
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