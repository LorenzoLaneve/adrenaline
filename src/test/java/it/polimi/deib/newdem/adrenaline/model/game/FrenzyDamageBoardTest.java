package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.TestingUtils;
import it.polimi.deib.newdem.adrenaline.controller.Config;
import it.polimi.deib.newdem.adrenaline.controller.effects.EffectManager;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.server.NullVirtualGameView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualDamageBoardView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualGameView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualKillTrackView;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FrenzyDamageBoardTest {

    private Player p;
    private LegacyDamageBoardAdapter dmgb;
    Map map;
    Player player1;
    Player player2;
    Player player3;
    Player player4;
    Game game;
    VirtualGameView vgv;

    @Before
    public void setUp(){
        TestingUtils.loadSingleton();
        map =  Map.createMap(this.getClass().getClassLoader().getResource("TestMap.json").getFile().replace("%20", " "));

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

        gp.setGameMap(map);

        game = new GameImpl(gp);

        vgv = new NullVirtualGameView();
        game.setGameListener(vgv);
        game.setKillTrackListener(new VirtualKillTrackView(vgv)); //???

        game.init();

        player1 = game.getPlayerFromColor(PlayerColor.YELLOW);
        player2 = game.getPlayerFromColor(PlayerColor.GREEN);
        player3 = game.getPlayerFromColor(PlayerColor.GRAY);
        player4 = game.getPlayerFromColor(PlayerColor.MAGENTA);

        player1.getDamageBoard().setListener(new VirtualDamageBoardView(player1, vgv));
        player2.getDamageBoard().setListener(new VirtualDamageBoardView(player2, vgv));
        player3.getDamageBoard().setListener(new VirtualDamageBoardView(player3, vgv));
        player4.getDamageBoard().setListener(new VirtualDamageBoardView(player4, vgv));

        p = player1;
        DamageBoard pDmgb = new FrenzyDamageBoard(p);
        pDmgb.setListener(new VirtualDamageBoardView(p, vgv));
        dmgb = new LegacyDamageBoardAdapter(pDmgb);
        p.registerDamageBoard(dmgb);
    }

    @Test
    public void testGetScoreForPlayer() {
        Player p1 = player2;
        Player p2 = player3;
        Player p3 = player4;

        dmgb.takeDamage(2, p3);
        dmgb.takeDamage(5, p1);
        dmgb.takeDamage(4, p2);

        assertEquals(2, dmgb.getScoreForPlayer(p1));
        assertEquals(1,dmgb.getScoreForPlayer(p2));
        assertEquals(1, dmgb.getScoreForPlayer(p3));
        assertEquals(0,dmgb.getScoreForPlayer(p));
        assertEquals(0,dmgb.getScoreForPlayer(null));



        p.addSkull();
        p.addSkull();
        p.addSkull();
        p.addSkull();
        p.addSkull();
        p.addSkull();

        DamageBoard pDmgb = new FrenzyDamageBoard(p);
        pDmgb.setListener(new VirtualDamageBoardView(p, vgv));
        dmgb = new LegacyDamageBoardAdapter(pDmgb);
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

    @Test
    public void testRenew() throws Exception {
        java.util.Map oldMarks = p.getDamageBoard().getMarksMap();
        p.getDamageBoard().renewDamageBoard();
        assertNotEquals(dmgb, p.getDamageBoard());
        assertEquals(oldMarks, p.getDamageBoard().getMarksMap());
    }
}