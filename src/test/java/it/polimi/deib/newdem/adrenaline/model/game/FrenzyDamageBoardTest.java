package it.polimi.deib.newdem.adrenaline.model.game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FrenzyDamageBoardTest {

    private Player p;
    private FrenzyDamageBoard dmgb;

    @Before
    public void setUp(){
        p = new MockPlayer(PlayerColor.YELLOW);
        dmgb = new FrenzyDamageBoard(p);
        p.registerDamageBoard(dmgb);
    }

    @Test
    public void testGetScoreForPlayer() {
        Player p1 = new MockPlayer(PlayerColor.GRAY);
        Player p2 = new MockPlayer(PlayerColor.CYAN);
        Player p3 = new MockPlayer(PlayerColor.GREEN);

        dmgb.takeDamage(2, p3);
        dmgb.takeDamage(5, p1);
        dmgb.takeDamage(4, p2);

        assertEquals(2, dmgb.getScoreForPlayer(p1));
        assertEquals(1,dmgb.getScoreForPlayer(p2));
        assertEquals(1, dmgb.getScoreForPlayer(p3));
        assertEquals(0,dmgb.getScoreForPlayer(p));
        assertEquals(0,dmgb.getScoreForPlayer(null));

        ((MockPlayer) p).die();
        ((MockPlayer) p).die();
        ((MockPlayer) p).die();
        ((MockPlayer) p).die();
        ((MockPlayer) p).die();
        ((MockPlayer) p).die();
        dmgb = new FrenzyDamageBoard(p);
        p.registerDamageBoard(dmgb);

        dmgb.takeDamage(5, p1);
        assertEquals(1,dmgb.getScoreForPlayer(p1));
    }

    @Test
    public void testGetAdditionalActions() {
        Player p1 = new MockPlayer();
        assertEquals(0, dmgb.getAdditionalActions().size());
        dmgb.takeDamage(7,p1);
        assertEquals(0, dmgb.getAdditionalActions().size());
    }
}