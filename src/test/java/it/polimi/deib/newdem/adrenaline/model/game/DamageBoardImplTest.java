package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.TestingUtils;
import it.polimi.deib.newdem.adrenaline.controller.Config;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerImpl;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.map.TestingMapBuilder;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.view.client.gui.GUIGameWindow;
import it.polimi.deib.newdem.adrenaline.view.server.NullVirtualGameView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualDamageBoardView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualGameView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualKillTrackView;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DamageBoardImplTest {

    private Player p1;
    private Player p2;
    private Player p3;
    private Player p4;
    private LegacyDamageBoardAdapter dmgb;
    private Game game;
    private VirtualGameView vgv;

    @Before
    public void setUp() {
        TestingUtils.loadSingleton();
        GameParameters gp = GameParameters.fromConfig(Config.getDefaultConfig());
        ColorUserPair colorUserPair1 = new ColorUserPair(PlayerColor.YELLOW, new User());
        ColorUserPair colorUserPair2 = new ColorUserPair(PlayerColor.GREEN, new User());
        ColorUserPair colorUserPair3 = new ColorUserPair(PlayerColor.GRAY, new User());
        ColorUserPair colorUserPair4 = new ColorUserPair(PlayerColor.MAGENTA, new User());
        List<ColorUserPair> listPairs = new ArrayList<>();
        listPairs.add(colorUserPair1);
        listPairs.add(colorUserPair2);
        listPairs.add(colorUserPair3);
        listPairs.add(colorUserPair4);

        gp.setColorUserOrder(listPairs);

        gp.setMinPlayers(1);
        gp.setGameMap(TestingMapBuilder.getNewMap(this.getClass()));
        game = new GameImpl(gp);

        vgv = new NullVirtualGameView();
        game.setGameListener(vgv);
        game.setKillTrackListener(new VirtualKillTrackView(vgv));

        game.init();
        p1 = game.getPlayerFromColor(PlayerColor.YELLOW);
        p2 = game.getPlayerFromColor(PlayerColor.GREEN);
        p3 = game.getPlayerFromColor(PlayerColor.GRAY);
        p4 = game.getPlayerFromColor(PlayerColor.MAGENTA);

        p1.getDamageBoard().setListener(new VirtualDamageBoardView(p1, vgv));
        p2.getDamageBoard().setListener(new VirtualDamageBoardView(p2, vgv));
        p3.getDamageBoard().setListener(new VirtualDamageBoardView(p3, vgv));
        p4.getDamageBoard().setListener(new VirtualDamageBoardView(p4, vgv));
        Map m = game.getMap();
        Tile spawn = m.getSpawnPointFromColor(AmmoColor.RED);
        m.movePlayer(p1, spawn);

        DamageBoard pDmgb = new OrdinaryDamageBoard(p1);
        pDmgb.setListener(new VirtualDamageBoardView(p1, vgv));
        dmgb = new LegacyDamageBoardAdapter(pDmgb);
        p1.registerDamageBoard(dmgb);
    }

    @Test
    public void testGetPlayerPositive() {
        assertEquals(p1, dmgb.getPlayer());
    }

    @Test
    public void testTakeDamagePositive() {

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

        dmgb.takeMark(1,p1);
        dmgb.takeMark(2,p2);
        dmgb.takeMark(3,p1);

        assertEquals(2,dmgb.getTotalMarksFromPlayer(p2));
        assertEquals(3,dmgb.getTotalMarksFromPlayer(p1));
    }

    @Test
    public void testComparator()  {

        dmgb.takeDamage(1, p4);
        dmgb.takeDamage(1,p2);
        dmgb.takeDamage(1,p3);

        assertEquals(9, dmgb.getScoreForPlayer(p4));
        assertEquals(6, dmgb.getScoreForPlayer(p2));
        assertEquals(4, dmgb.getScoreForPlayer(p3));


        // ((MockPlayer) p1).die();
        p1.reportDeath(true);
        p1.addSkull();
        DamageBoard pDmgb = new OrdinaryDamageBoard(p1);
        pDmgb.setListener(new VirtualDamageBoardView(p1, vgv));
        dmgb = new LegacyDamageBoardAdapter(pDmgb);
        p1.registerDamageBoard(dmgb);

        dmgb.takeDamage(1, p4);
        dmgb.takeDamage(1,p2);
        dmgb.takeDamage(1,p3);

        assertEquals(7, dmgb.getScoreForPlayer(p4));
        assertEquals(4, dmgb.getScoreForPlayer(p2));
        assertEquals(2, dmgb.getScoreForPlayer(p3));

        // ((MockPlayer) p1).die();
        p1.reportDeath(true);
        p1.addSkull();
        pDmgb = new OrdinaryDamageBoard(p1);
        pDmgb.setListener(new VirtualDamageBoardView(p1, vgv));
        dmgb = new LegacyDamageBoardAdapter(pDmgb);
        p1.registerDamageBoard(dmgb);

        dmgb.takeDamage(2,p3);
        dmgb.takeDamage(1,p2);

        assertEquals(5, dmgb.getScoreForPlayer(p3));
        assertEquals(2, dmgb.getScoreForPlayer(p2));

        // ((MockPlayer) p1).die();
        p1.reportDeath(true);
        p1.addSkull();
        pDmgb = new OrdinaryDamageBoard(p1);
        pDmgb.setListener(new VirtualDamageBoardView(p1, vgv));
        dmgb = new LegacyDamageBoardAdapter(pDmgb);
        p1.registerDamageBoard(dmgb);

        dmgb.takeDamage(1,p3);
        dmgb.takeDamage(2,p2);

        assertEquals(2, dmgb.getScoreForPlayer(p3));
        assertEquals(2, dmgb.getScoreForPlayer(p2));
    }

    @Test
    public void testGetMarkMapPositive() {
        DamageBoard d = p1.getDamageBoard();

        p1.getDamageBoard().setMarksFromPlayer(1, p2);
        assertNotNull(d.getMarksMap());
        java.util.Map<Player, Integer> map = d.getMarksMap();
        map.get(p2);
        System.out.println(d.getMarksMap().get(p2));
        assertEquals(1, d.getMarksMap().get(p2).intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegative() {
        DamageBoard dmgb = new OrdinaryDamageBoard(null);
    }

    // getPlayerFromColor can't fail, no point in testGetPlayerNegative

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

    @Test
    public void testPopDamage() throws Exception {
        DamageBoard dmgb = p1.getDamageBoard();
        p1.registerDamageBoard(dmgb);

        try{
            dmgb.popDamage();
        }
        catch (DamageTrackEmptyException e) {
            // ok
        }
        catch (Exception e) {
            fail();
        }

        p1.getDamageBoard().appendDamage(p2);
        Player p5 = dmgb.popDamage();
        assertEquals(p5, p2);
    }

    @Test
    public void testMarkConvert() throws Exception {
        // TODO
        // p1.getDamageBoard().setMarksFromPlayer();
    }

    @Test
    public void testAddMark() throws Exception {
        // DamageGameChange(Player attacker, Player attacked, int dmgAmt, int mrkAmt)
        GameChange gc1 = new DamageGameChange(p2, p1, 0, 1);
        GameChange gc2 = new DamageGameChange(p2, p1, 0, 1);
        gc1.update(game);
        gc2.update(game);
        assertEquals(2, p1.getMarksFromPlayer(p2));
    }
}