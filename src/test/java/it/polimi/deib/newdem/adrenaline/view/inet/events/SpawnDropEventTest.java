package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.game.GameData;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpawnDropEventTest {

    SpawnDropEvent e;

    @Before
    public void setUp() throws Exception {
        e = new SpawnDropEvent(
                GameData.DropType.POWER_UP,
                GameData.DropType.YELLOW_AMMO,
                GameData.DropType.BLUE_AMMO,
                new TilePosition(0,0)
        );
    }

    @Test
    public void testGetDrop1() throws Exception {
        assertEquals(GameData.DropType.POWER_UP, e.getDrop1());
    }

    @Test
    public void testGetDrop2() throws Exception {
        assertEquals(GameData.DropType.YELLOW_AMMO, e.getDrop2());
    }

    @Test
    public void testGetDrop3() throws Exception {
        assertEquals(GameData.DropType.BLUE_AMMO, e.getDrop3());
    }

    @Test
    public void testGetTilePosition() throws Exception {
        assertEquals(new TilePosition(0,0), e.getTilePosition());
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.publish(new MockConnectionSender());
    }
}