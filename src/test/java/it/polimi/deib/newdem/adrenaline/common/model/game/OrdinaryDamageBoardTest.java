package it.polimi.deib.newdem.adrenaline.common.model.game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrdinaryDamageBoardTest {

    private Player p;
    private OrdinaryDamageBoard dmgb;

    @Before
    public void setUp(){
        p = new MockPlayer(PlayerColor.YELLOW);
        dmgb = new OrdinaryDamageBoard(p);
        p.registerDamageBoard(dmgb);
    }

    @Test
    public void testGetScoreForPlayer() {
        Player p1 = new MockPlayer(PlayerColor.GRAY);
        Player p2 = new MockPlayer(PlayerColor.CYAN);
        Player p3 = new MockPlayer(PlayerColor.GREEN);

        dmgb.takeDamage(2, p3);
        dmgb.takeDamage(6, p1);
        dmgb.takeDamage(4, p2);

        assertEquals(8, dmgb.getScoreForPlayer(p1));
        assertEquals(6, dmgb.getScoreForPlayer(p2));
        assertEquals(5, dmgb.getScoreForPlayer(p3));
        assertEquals(0, dmgb.getScoreForPlayer(p));
        assertEquals(0, dmgb.getScoreForPlayer(null));

        ((MockPlayer) p).die();
        dmgb = new OrdinaryDamageBoard(p);
        p.registerDamageBoard(dmgb);

        dmgb.takeDamage(5, p1);
        dmgb.takeDamage(4, p2);

        assertEquals(7, dmgb.getScoreForPlayer(p1));
        assertEquals(4,dmgb.getScoreForPlayer(p2));
        assertEquals(0, dmgb.getScoreForPlayer(p3));
        assertEquals(0,dmgb.getScoreForPlayer(p));
        assertEquals(0,dmgb.getScoreForPlayer(null));
        assertEquals(0,dmgb.getScoreForPlayer(p));

        ((MockPlayer) p).die();
        dmgb = new OrdinaryDamageBoard(p);
        p.registerDamageBoard(dmgb);

        dmgb.takeDamage(5, p1);
        dmgb.takeDamage(5, p2);

        assertEquals(5, dmgb.getScoreForPlayer(p1));
        assertEquals(2,dmgb.getScoreForPlayer(p2));

        ((MockPlayer) p).die();
        dmgb = new OrdinaryDamageBoard(p);
        p.registerDamageBoard(dmgb);

        dmgb.takeDamage(5, p2);
        dmgb.takeDamage(5, p1);

        assertEquals(3, dmgb.getScoreForPlayer(p2));
        assertEquals(1,dmgb.getScoreForPlayer(p1));

        ((MockPlayer) p).die();
        ((MockPlayer) p).die();
        ((MockPlayer) p).die();
        dmgb = new OrdinaryDamageBoard(p);
        p.registerDamageBoard(dmgb);

        dmgb.takeDamage(5, p2);
        dmgb.takeDamage(5, p1);

        assertEquals(1, dmgb.getScoreForPlayer(p2));
        assertEquals(1,dmgb.getScoreForPlayer(p1));
    }
}