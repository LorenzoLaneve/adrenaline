package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpawnPlayerEventTest {

    SpawnPlayerEvent e;

    @Before
    public void setUp() throws Exception {
        e = new SpawnPlayerEvent(PlayerColor.GREEN, new TilePosition(0,0));
    }

    @Test
    public void testGetPlayerColor() throws Exception {
        assertEquals(PlayerColor.GREEN, e.getPlayerColor());
    }

    @Test
    public void testGetSpawnPoint() throws Exception {
        assertEquals(new TilePosition(0,0), e.getSpawnPoint());
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.notifyEvent(new MockConnectionSender(), new MockConnectionReceiver());
    }
}