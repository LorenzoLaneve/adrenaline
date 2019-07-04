package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import org.junit.Before;
import org.junit.Test;

public class NullGameListenerTest {

    NullGameListener l;

    @Before
    public void setUp() throws Exception {
        l = new NullGameListener();
    }

    @Test
    public void testGameWillEnd() throws Exception {
        l.gameWillEnd(new MockGame(), new GameResults());
    }

    @Test
    public void testUserDidEnterGame() throws Exception {
        l.userDidEnterGame(new User(), new MockPlayer());
    }

    @Test
    public void testUserDidExitGame() throws Exception {
        l.userDidExitGame(new User(), new MockPlayer());
    }
}