package it.polimi.deib.newdem.adrenaline.common.model.game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class KillTrackImplTest {

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeLowerBound() {
        new KillTrackImpl(4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeUpperBound() {
        new KillTrackImpl(9);
    }


    @Test
    public void testRegisterKillPositive() {
        MockPlayer p = new MockPlayer(PlayerColor.YELLOW);
        MockPlayer p2 = new MockPlayer(PlayerColor.GRAY);

        KillTrack kt = new KillTrackImpl(5);

        kt.registerKill(p);
        assertEquals(kt.getKiller(0),p);
        kt.registerKill(p);
        kt.registerKill(p);
        kt.registerKill(p);
        kt.registerKill(p);
        kt.registerKill(p);
        kt.registerKill(p2);
        assertEquals(kt.getKiller(6),p2);
    }

    @Test
    public void testGetKiller() {
        MockPlayer p1 = new MockPlayer(PlayerColor.YELLOW);
        MockPlayer p2 = new MockPlayer(PlayerColor.CYAN);
        MockPlayer p3 = new MockPlayer(PlayerColor.GRAY);

        KillTrack kt = new KillTrackImpl(5);

        kt.registerKill(p1);
        kt.registerKill(p2);
        kt.registerKill(p3);

        assertEquals(kt.getKiller(1),p2);
    }

    @Test
    public void testGetTrackLengthPositive() {
        MockPlayer p1 = new MockPlayer(PlayerColor.YELLOW);

        KillTrack kt = new KillTrackImpl(5);

        assertEquals(5, kt.getTrackLength());

        kt.registerKill(p1);
        assertEquals(5, kt.getTrackLength());
        kt.registerKill(p1);
        assertEquals(5, kt.getTrackLength());
        kt.registerKill(p1);
        assertEquals(5, kt.getTrackLength());
        kt.registerKill(p1);
        assertEquals(5, kt.getTrackLength());
        kt.registerKill(p1);
        assertEquals(5, kt.getTrackLength());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRegisterKillNegative() {
        KillTrack kt = new KillTrackImpl(5);

        kt.registerKill(null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetKillerNegativeNegativeIndex() {
        KillTrack kt = new KillTrackImpl(5);

        kt.getKiller(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetKillerNegativeOutOfBoundIndex() {
        KillTrack kt = new KillTrackImpl(5);

        kt.getKiller(6);
    }

    @Test
    public void testGetTotalKills() {
        KillTrack kt = new KillTrackImpl(5);
        MockPlayer p = new MockPlayer(PlayerColor.YELLOW);

        assertEquals(0,kt.getTotalKills());
        kt.registerKill(p);
        assertEquals(1,kt.getTotalKills());
        kt.registerKill(p);
        assertEquals(2,kt.getTotalKills());
        kt.registerKill(p);
        assertEquals(3,kt.getTotalKills());
        kt.registerKill(p);
        assertEquals(4,kt.getTotalKills());
        kt.registerKill(p);
        assertEquals(5,kt.getTotalKills());
        kt.registerKill(p);
        assertEquals(6,kt.getTotalKills());
    }

}