package it.polimi.deib.newdem.adrenaline.view.inet.events;

import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class MapTileDataEventTest {

    MapTileDataEvent e;
    TilePosition[] pos = {new TilePosition(0,0), new TilePosition(0,1)};

    @Before
    public void setUp() throws Exception {
        e = new MapTileDataEvent(Arrays.asList(pos));
    }

    @Test
    public void testGetTileDataPosition() throws Exception {
        assertArrayEquals(e.getTileDataPosition().toArray(), pos);
    }

    @Test
    public void testNotifyEvent() throws Exception {
        e.publish(new MockConnectionSender());
    }
}