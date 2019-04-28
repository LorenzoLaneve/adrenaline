package it.polimi.deib.newdem.adrenaline.common.model.game;

import it.polimi.deib.newdem.adrenaline.common.controller.actions.ActionFactory;
import it.polimi.deib.newdem.adrenaline.common.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.common.controller.actions.AtomicActionType;
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

    @Test
    public void testGetAdditionalActions() {
        Player p1 = new MockPlayer(PlayerColor.GREEN);
        ActionType t2 = new ActionType(AtomicActionType.MOVE1, AtomicActionType.SHOOT);
        ActionType t1 = new ActionType(AtomicActionType.MOVE2, AtomicActionType.GRAB);
        assertEquals(0, dmgb.getAdditionalActions().size());

        dmgb.takeDamage(1, p1);

        assertEquals(0, dmgb.getAdditionalActions().size());

        dmgb.takeDamage(2, p1);

        assertEquals(1, dmgb.getAdditionalActions().size());
        assertEquals(t1, dmgb.getAdditionalActions().get(0).getType());

        dmgb.takeDamage(5, p1);

        assertEquals(2, dmgb.getAdditionalActions().size());

        assertEquals(t1, dmgb.getAdditionalActions().get(0).getType());
        assertEquals(t2, dmgb.getAdditionalActions().get(1).getType());



    }

    @Test
    public void testGetTotalDamage() {
        Player p1 = new MockPlayer(PlayerColor.GREEN);
        Player p2 = new MockPlayer(PlayerColor.GRAY);

        assertEquals(0, dmgb.getTotalDamage());

        dmgb.takeDamage(3, p1);

        assertEquals(3, dmgb.getTotalDamage());

        dmgb.takeDamage(2,p2);
        dmgb.takeMark(2,p2);

        assertEquals(5, dmgb.getTotalDamage());
    }
}