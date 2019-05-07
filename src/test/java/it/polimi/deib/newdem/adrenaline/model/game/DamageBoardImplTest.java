package it.polimi.deib.newdem.adrenaline.model.game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DamageBoardImplTest {

    private Player p1;
    private DamageBoard dmgb;

    @Before
    public void setUp() {
        p1 = new MockPlayer(PlayerColor.YELLOW, "Gionni");
        dmgb = new OrdinaryDamageBoard(p1);
    }

    @Test
    public void testGetPlayerPositive() {
        assertEquals(p1, dmgb.getPlayer());
    }

    @Test
    public void testTakeDamagePositive() {
        Player p2 = new MockPlayer(PlayerColor.GRAY);

        dmgb.takeDamage(2,p2);
        dmgb.getTotalDamageFromPlayer(p2);
    }

    @Test
    public void testTakeMarkPositive() {
        dmgb.takeMark(2,p1);
        assertEquals(2, dmgb.getTotalMarksFromPlayer(p1));

        dmgb.takeMark(2,p1);
        assertEquals(3, dmgb.getTotalMarksFromPlayer(p1));

        dmgb.takeMark(2,p1);
        assertEquals(3, dmgb.getTotalMarksFromPlayer(p1));
    }


    @Test
    public void testGetDamagerPositiveDamaged() {
        dmgb.takeDamage(3,p1);
        assertEquals(p1, dmgb.getDamager(1));
    }

    @Test
    public void testGetDamagerPositiveUnhurt() {
        dmgb.takeDamage(3,p1);
        assertNull("p did not receive damage in slot 5", dmgb.getDamager(5));
    }


    @Test
    public void testGetTotalDamageFromPlayerPositive() {
        Player p2 = new MockPlayer(PlayerColor.GRAY);

        dmgb.takeDamage(1,p1);
        dmgb.takeDamage(2,p2);
        dmgb.takeDamage(3,p1);

        assertEquals(2,dmgb.getTotalDamageFromPlayer(p2));
        assertEquals(4,dmgb.getTotalDamageFromPlayer(p1));

        dmgb.takeDamage(100,p1);
        assertEquals(12-2, dmgb.getTotalDamageFromPlayer(p1));
    }

    @Test
    public void testGetTotalMarksFromPlayerPositive(){
        Player p2 = new MockPlayer(PlayerColor.GRAY);

        dmgb.takeMark(1,p1);
        dmgb.takeMark(2,p2);
        dmgb.takeMark(3,p1);

        assertEquals(2,dmgb.getTotalMarksFromPlayer(p2));
        assertEquals(3,dmgb.getTotalMarksFromPlayer(p1));
    }

    @Test
    public void testComparator()  {
        Player p2 = new MockPlayer(PlayerColor.GRAY);
        Player p3 = new MockPlayer(PlayerColor.MAGENTA);

        dmgb.takeDamage(1,p1);
        dmgb.takeDamage(1,p2);
        dmgb.takeDamage(1,p3);


        assertEquals(9, dmgb.getScoreForPlayer(p1));
        assertEquals(6, dmgb.getScoreForPlayer(p2));
        assertEquals(4, dmgb.getScoreForPlayer(p3));


        ((MockPlayer) p1).die();
        dmgb = new OrdinaryDamageBoard(p1);
        p1.registerDamageBoard(dmgb);

        dmgb.takeDamage(1,p2);
        dmgb.takeDamage(1,p1);
        dmgb.takeDamage(1,p3);

        assertEquals(4, dmgb.getScoreForPlayer(p1));
        assertEquals(7, dmgb.getScoreForPlayer(p2));
        assertEquals(2, dmgb.getScoreForPlayer(p3));

        ((MockPlayer) p1).die();
        dmgb = new OrdinaryDamageBoard(p1);
        p1.registerDamageBoard(dmgb);

        dmgb.takeDamage(2,p1);
        dmgb.takeDamage(1,p2);

        assertEquals(5, dmgb.getScoreForPlayer(p1));
        assertEquals(2, dmgb.getScoreForPlayer(p2));

        ((MockPlayer) p1).die();
        dmgb = new OrdinaryDamageBoard(p1);
        p1.registerDamageBoard(dmgb);

        dmgb.takeDamage(1,p1);
        dmgb.takeDamage(2,p2);

        assertEquals(2, dmgb.getScoreForPlayer(p1));
        assertEquals(2, dmgb.getScoreForPlayer(p2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegative() {
        DamageBoard dmgb = new OrdinaryDamageBoard(null);
    }

    // getPlayer can't fail, no point in testGetPlayerNegative

    @Test(expected = IllegalArgumentException.class)
    public void testTakeDamageNegativeLessThanZero() {
        dmgb.takeDamage(-1, p1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTakeDamageNegativeNull() {
        dmgb.takeDamage(2,null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTakeMarkNegativeNull() {
        dmgb.takeMark(1,null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testTakeMarkNegativeBelow() {
        dmgb.takeMark(-1,p1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetDamagerNegativeOutOfBoundLow() {
        dmgb.takeDamage(3,p1);
        assertEquals(p1, dmgb.getDamager(-1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetDamagerNegativeOutOfBoundHigh() {
        dmgb.takeDamage(3,p1);
        assertEquals(p1, dmgb.getDamager(20));
    }

    @Test
    public void testGetTotalDamageFromPlayerNegative() {
        // this method can't fail
    }

    @Test
    public void testGetTotalMarksFromPlayerNegative(){
        // this method can't fail
    }

}