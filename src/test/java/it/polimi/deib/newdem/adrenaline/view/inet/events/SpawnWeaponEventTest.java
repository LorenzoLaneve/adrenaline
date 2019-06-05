package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpawnWeaponEventTest {

    SpawnWeaponEvent e;

    @Before
    public void setUp() throws Exception {
        e = new SpawnWeaponEvent(new TilePosition(0,0), 7);
    }

    @Test
    public void testGetWeaponCardID() throws Exception {
        assertEquals(7, e.getWeaponCardID());
    }

    @Test
    public void testGetTilePosition() throws Exception {
        assertEquals(new TilePosition(0,0),e.getTilePosition());
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.notifyEvent(new MockConnectionSender(), new MockConnectionReceiver());
    }
}