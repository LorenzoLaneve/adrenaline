package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.TestingUtils;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.server.NullVirtualGameView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualDamageBoardView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualGameView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualKillTrackView;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;
import static org.junit.Assert.*;

public class OrdinaryDamageBoardTest {

    private Player p;
    private LegacyDamageBoardAdapter dmgb;
    VirtualGameView vgv;

    @Before
    public void setUp(){
        TestingUtils.loadSingleton();
        // p = new MockPlayer(PlayerColor.YELLOW);
        GameParameters gp = new GameParameters();
        gp.setColorUserOrder(Arrays.asList(
                new ColorUserPair(PlayerColor.YELLOW, new User()),
                new ColorUserPair(PlayerColor.CYAN, new User()),
                new ColorUserPair(PlayerColor.GRAY, new User()),
                new ColorUserPair(PlayerColor.GREEN, new User())

        ));
        gp.setGameMap(Map.createMap("TestMap.json"));
        Game game = new GameImpl(gp);
        vgv = new NullVirtualGameView();
        game.setGameListener(vgv);
        game.setKillTrackListener(new VirtualKillTrackView(vgv)); //???

        game.init();

        p = game.getPlayerFromColor(PlayerColor.YELLOW);
        p.getDamageBoard().setListener(new VirtualDamageBoardView(p, vgv));
        dmgb = new LegacyDamageBoardAdapter(p.getDamageBoard());
        p.registerDamageBoard(dmgb);
    }

    @Test
    public void testGetScoreForPlayer() {
        Game g = p.getGame();
        Player p1 = g.getPlayerFromColor(PlayerColor.GRAY);
        Player p2 = g.getPlayerFromColor(PlayerColor.CYAN);
        Player p3 = g.getPlayerFromColor(PlayerColor.GREEN);

        p1.getDamageBoard().setListener(new VirtualDamageBoardView(p1, vgv));
        p2.getDamageBoard().setListener(new VirtualDamageBoardView(p2, vgv));
        p3.getDamageBoard().setListener(new VirtualDamageBoardView(p3, vgv));

        dmgb.takeDamage(2, p3);
        dmgb.takeDamage(6, p1);
        dmgb.takeDamage(4, p2);

        assertEquals(8, dmgb.getScoreForPlayer(p1));
        assertEquals(6, dmgb.getScoreForPlayer(p2));
        assertEquals(5, dmgb.getScoreForPlayer(p3));
        assertEquals(0, dmgb.getScoreForPlayer(p));
        assertEquals(0, dmgb.getScoreForPlayer(null));

        Tile spawn = p.getGame().getMap().getSpawnPointFromColor(AmmoColor.RED);
        p.getGame().getMap().movePlayer(p, spawn);
        p.reportDeath(true);
        p.addSkull();

        DamageBoard pDmgb = new OrdinaryDamageBoard(p);
        pDmgb.setListener(new VirtualDamageBoardView(p, vgv));
        dmgb = new LegacyDamageBoardAdapter(pDmgb);
        p.registerDamageBoard(dmgb);

        dmgb.takeDamage(5, p1);
        dmgb.takeDamage(4, p2);

        //FIXME
        assertEquals(7, dmgb.getScoreForPlayer(p1));
        assertEquals(4,dmgb.getScoreForPlayer(p2));
        assertEquals(0, dmgb.getScoreForPlayer(p3));
        assertEquals(0,dmgb.getScoreForPlayer(p));
        assertEquals(0,dmgb.getScoreForPlayer(null));
        assertEquals(0,dmgb.getScoreForPlayer(p));

        p.reportDeath(true);
        p.addSkull();
        dmgb = new LegacyDamageBoardAdapter(p.getDamageBoard());
        p.registerDamageBoard(dmgb);

        dmgb.takeDamage(5, p1);
        dmgb.takeDamage(5, p2);

        assertEquals(5, dmgb.getScoreForPlayer(p1));
        assertEquals(2,dmgb.getScoreForPlayer(p2));


        p.reportDeath(true);
        p.addSkull();
        pDmgb = new OrdinaryDamageBoard(p);
        pDmgb.setListener(new VirtualDamageBoardView(p, vgv));
        dmgb = new LegacyDamageBoardAdapter(pDmgb);
        p.registerDamageBoard(dmgb);

        dmgb.takeDamage(5, p2);
        dmgb.takeDamage(5, p1);

        assertEquals(3, dmgb.getScoreForPlayer(p2));
        assertEquals(1,dmgb.getScoreForPlayer(p1));

        p.reportDeath(true);
        p.reportDeath(true);
        p.reportDeath(true);
        p.addSkull();
        p.addSkull();
        p.addSkull();

        pDmgb = new OrdinaryDamageBoard(p);
        pDmgb.setListener(new VirtualDamageBoardView(p, vgv));
        dmgb = new LegacyDamageBoardAdapter(pDmgb);
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

    @Test
    public void testRenew() throws Exception {
        java.util.Map oldMarks = p.getDamageBoard().getMarksMap();
        p.getDamageBoard().renewDamageBoard();
        assertNotEquals(dmgb, p.getDamageBoard());
        assertEquals(oldMarks, p.getDamageBoard().getMarksMap());
    }
}