package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.game.killtrack.KillTrack;
import it.polimi.deib.newdem.adrenaline.model.game.killtrack.KillTrackImpl;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import org.junit.Test;

import static org.junit.Assert.*;

public class KillTrackImplTest {

    /**
     * Testing full implementation of KillTrack especially its registerKill and scoring funcionalities.
     */

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeLowerBound() {
        new KillTrackImpl(KillTrackImpl.MIN_KILLTRACK_SIZE - 1);
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

        kt.addKill(p,1);
        assertEquals(kt.getKiller(0),p);
        kt.addKill(p,1);
        kt.addKill(p,1);
        kt.addKill(p,2);
        kt.addKill(p,1);
        kt.addKill(p,1);
        kt.addKill(p2,2);
        assertEquals(kt.getKiller(6),p2);
    }

    @Test
    public void testGetKiller() {
        MockPlayer p1 = new MockPlayer(PlayerColor.YELLOW);
        MockPlayer p2 = new MockPlayer(PlayerColor.CYAN);
        MockPlayer p3 = new MockPlayer(PlayerColor.GRAY);

        KillTrack kt = new KillTrackImpl(5);

        kt.addKill(p1, 1);
        kt.addKill(p2, 1);
        kt.addKill(p3, 1);

        assertEquals(kt.getKiller(1),p2);
    }

    @Test
    public void testGetTrackLengthPositive() {
        MockPlayer p1 = new MockPlayer(PlayerColor.YELLOW);

        KillTrack kt = new KillTrackImpl(5);

        assertEquals(5, kt.getTrackLength());

        kt.addKill(p1,1);
        assertEquals(5, kt.getTrackLength());
        kt.addKill(p1,1);
        assertEquals(5, kt.getTrackLength());
        kt.addKill(p1,1);
        assertEquals(5, kt.getTrackLength());
        kt.addKill(p1,1);
        assertEquals(5, kt.getTrackLength());
        kt.addKill(p1,1);
        assertEquals(5, kt.getTrackLength());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRegisterKillNegative() {
        KillTrack kt = new KillTrackImpl(5);

        kt.addKill(null, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRegisterKillNegativeBelow() {
        KillTrack kt = new KillTrackImpl(5);

        kt.addKill(new MockPlayer(), -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRegisterKillNegativeAbove() {
        KillTrack kt = new KillTrackImpl(5);

        kt.addKill(new MockPlayer(), 7);
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
        kt.addKill(p, 1);
        assertEquals(1,kt.getTotalKills());
        kt.addKill(p, 1);
        assertEquals(2,kt.getTotalKills());
        kt.addKill(p, 1);
        assertEquals(3,kt.getTotalKills());
        kt.addKill(p, 1);
        assertEquals(4,kt.getTotalKills());
        kt.addKill(p, 1);
        assertEquals(5,kt.getTotalKills());
        kt.addKill(p, 1);
        assertEquals(6,kt.getTotalKills());
    }

    @Test
    public void testGetScoreForPlayerPositiveBase() throws Exception {
        MockPlayer p1 = new MockPlayer(PlayerColor.YELLOW);
        MockPlayer p2 = new MockPlayer(PlayerColor.GREEN);
        MockPlayer p3 = new MockPlayer(PlayerColor.GRAY);

        KillTrackImpl kt = new KillTrackImpl(KillTrackImpl.MAX_KILLTRACK_SIZE);
        kt.addKill(p1,1);

        assertEquals(8, kt.getScoreForPlayer(p1));

        kt = new KillTrackImpl(KillTrackImpl.MAX_KILLTRACK_SIZE);
        kt.addKill(p1, 1);
        kt.addKill(p2, 2);
        kt.addKill(p3, 1);
        kt.addKill(p2, 1);

        assertEquals(8, kt.getScoreForPlayer(p2));
        assertEquals(6, kt.getScoreForPlayer(p1));
        assertEquals(4, kt.getScoreForPlayer(p3));
    }

    @Test
    public void testGetScoreForPlayerNegative() throws Exception {
        KillTrack kt = new KillTrackImpl(KillTrackImpl.MAX_KILLTRACK_SIZE);
        assertEquals(0, kt.getScoreForPlayer(new MockPlayer()));
        assertEquals(0,kt.getScoreForPlayer(null));
    }
}