package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PlayerSelectionRequestTest {

    PlayerSelectionRequest e;
    PlayerColor[] targets = {PlayerColor.GRAY, PlayerColor.MAGENTA};

    @Before
    public void setUp() throws Exception {
        e = new PlayerSelectionRequest(Arrays.asList(targets), true );
    }

    @Test
    public void testGetSelectablePlayers() throws Exception {
        assertArrayEquals(targets, e.getSelectablePlayers().toArray());
    }

    @Test
    public void testIsMandatory() throws Exception {
        assertTrue(e.isMandatory());
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.publish(new MockConnectionSender());
    }
}