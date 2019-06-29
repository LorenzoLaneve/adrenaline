package it.polimi.deib.newdem.adrenaline.model.game.killtrack;

import it.polimi.deib.newdem.adrenaline.model.game.*;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class KillTrackDataTest {

    KillTrack killTrack;

    @Before
    public void setUp() throws Exception {
        killTrack = new KillTrackImpl(KillTrackImpl.MAX_KILLTRACK_SIZE);
    }

    @Test
    public void testGetCells() throws Exception {
        Player p = new MockPlayer();
        killTrack.addKill(new MockPlayer(), 1);
        assertEquals(p.getColor(), killTrack.generateKillTrackData().getKills().get(0).getKiller());
    }
}