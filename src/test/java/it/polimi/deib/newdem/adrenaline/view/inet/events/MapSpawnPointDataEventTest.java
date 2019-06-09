package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MapSpawnPointDataEventTest {

    MapSpawnPointDataEvent e;
    TilePosition[] pos = {new TilePosition(0,0), new TilePosition(0,1)};

    @Before
    public void setUp() throws Exception {
        e = new MapSpawnPointDataEvent(Arrays.asList(pos));
    }

    @Test
    public void testGetSpawnPointTileDataPosition() throws Exception {
        assertArrayEquals(e.getSpawnPointTileDataPosition().toArray(), pos);
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.publish(new MockConnectionSender());
    }
}