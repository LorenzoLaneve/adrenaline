package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameState;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameStartEventTest {

    GameStartEvent e;

    @Before
    public void setUp() throws Exception {
        e = new GameStartEvent();
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.publish(new MockConnectionSender());
    }
}