package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.TestingUtils;
import it.polimi.deib.newdem.adrenaline.controller.Config;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.killtrack.NullKillTrackLister;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.turn.OrdinaryTurn;
import it.polimi.deib.newdem.adrenaline.model.game.turn.Turn;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.map.*;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.server.NullVirtualGameView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualDamageBoardView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualGameView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualKillTrackView;
import org.junit.Before;
import org.junit.Test;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static it.polimi.deib.newdem.adrenaline.model.game.GameImpl.MAX_PLAYERS_PER_GAME;
import static org.junit.Assert.*;

public class GameImplTest {

    GameImpl game;
    GameParameters gp;
    Map map;
    Player p1;
    List<ColorUserPair> colorUserPairs;

    @Before
    public void setUp() throws Exception {
        TestingUtils.loadSingleton();

        gp = new GameParameters();
        String encodedPath = getClass().getClassLoader().getResource("Map0_0.json").getFile();
        String decodedPath = URLDecoder.decode(encodedPath, StandardCharsets.UTF_8.name());
        map = Map.createMap(decodedPath);
        colorUserPairs = new ArrayList<>(MAX_PLAYERS_PER_GAME);
        colorUserPairs.add(new ColorUserPair(PlayerColor.MAGENTA, new User()));

        gp.setGameMap(map);
        gp.setColorUserOrder(colorUserPairs);
        gp.setMinPlayers(1);

        game = new GameImpl(gp);
        game.init();
        p1 = game.getPlayerFromColor(PlayerColor.MAGENTA);
    }

    @Test
    public void testGetMap() throws Exception {
        assertNotNull(game.getMap());
        assertNotNull(game.getMap().getTile(new TilePosition(0,0)));
    }

    @Test
    public void testGetPlayer() throws Exception {
        GameParameters gp = new GameParameters();
        List<ColorUserPair> colorUserPairs = new ArrayList<>();

        colorUserPairs.add(new ColorUserPair(PlayerColor.MAGENTA, new User()));
        gp.setColorUserOrder(colorUserPairs);
        gp.setMinPlayers(1);
        gp.setGameMap(map);

        Game thisGame = new GameImpl(gp);
        thisGame.init();

        assertNotNull(thisGame.getPlayerFromColor(PlayerColor.MAGENTA));

        assertNotNull(game.getPlayerFromColor(PlayerColor.MAGENTA));

        assertNotEquals(thisGame.getPlayerFromColor(PlayerColor.MAGENTA),
                game.getPlayerFromColor(PlayerColor.MAGENTA));
    }

    @Test
    public void testIsInFrenzy() throws Exception {
        assertFalse(game.isInFrenzy());
    }

    @Test
    public void testShouldGoFrenzy() throws Exception {
        List<ColorUserPair> colorUserPairs = gp.getColorUserOrder();
        colorUserPairs.add(new ColorUserPair(PlayerColor.GREEN, new User()));
        GameParameters gameParameters = new GameParameters();

        gameParameters.setColorUserOrder(colorUserPairs);
        gameParameters.setGameMap(map);

        // map.movePlayer(p2, map.getTile(new TilePosition(0,0)));

        gameParameters.setMinPlayers(1);
        game = new GameImpl(gameParameters);
        game.init();  //<<<<<

        Player p2 = game.getPlayerFromColor(PlayerColor.GREEN);

        VirtualGameView vgv = new NullVirtualGameView();

        game.setGameListener(vgv);
        game.setKillTrackListener(new VirtualKillTrackView(vgv)); //???

        p2.getDamageBoard().setListener(new VirtualDamageBoardView(p2, vgv));
        // p2.init();

        map.movePlayer(p2, map.getTile(new TilePosition(0,0)));

        assertFalse(game.shouldGoFrenzy());
        for(int i = 0; i <= GameParameters.KILLTRACK_STARTING_SIZE_DEFAULT; i++) {
            Turn t = game.getNextTurn();
            for(int z = 0; z < 10; z++) {
                try {
                    p2.getDamageBoard().appendDamage(p1);
                }
                catch (DamageTrackFullException e) {
                    // ok
                }
                p2.reportDeath(true);
            }

            game.concludeTurn(t);
        }
        assertTrue(game.isInFrenzy());
    }

    /*
    @Test
    public void testReset() throws Exception {
        game.reset();
    }*/

    @Test
    public void testGetNextTurn() throws Exception {
        assertNotNull(game.getNextTurn());
        // no concludeTurn, which would add a new turn
        assertNull(game.getNextTurn());
    }

    @Test
    public void testConcludeTurn() throws Exception {
        Turn t = game.getNextTurn();
        game.concludeTurn(t);
        assertTrue(game.getNextTurn() instanceof OrdinaryTurn);
    }

    @Test
    public void testGoFrenzy() throws Exception {
        Player p2 = new MockPlayer();
        p2.init();
        Player p3 = game.getPlayerFromColor(PlayerColor.MAGENTA);
        VirtualGameView vgv = new NullVirtualGameView();
        game.setGameListener(vgv);
        game.setKillTrackListener(new VirtualKillTrackView(vgv)); //???

        p3.getDamageBoard().setListener(new VirtualDamageBoardView(p3, vgv));
        p3.getDamageBoard().setMarksFromPlayer(2,p2);
        game.goFrenzy();
        assertEquals(2, p3.getMarksFromPlayer(p2));
    }

    @Test
    public void testGoFrenzyFirstPlayerCard() throws Exception {

        // List<ColorUserPair> colorUserPairs = gp.getColorUserOrder();
        GameParameters gp = GameParameters.fromConfig(Config.getDefaultConfig());
        List<ColorUserPair> colorUserPairs = new ArrayList<>();

        colorUserPairs.add(new ColorUserPair(PlayerColor.GREEN, new User()));
        colorUserPairs.add(new ColorUserPair(PlayerColor.YELLOW, new User()));
        colorUserPairs.add(new ColorUserPair(PlayerColor.GRAY, new User()));
        gp.setColorUserOrder(colorUserPairs);
        gp.setGameMap(map);

        GameImpl myGame = new GameImpl(gp);
        myGame.init();

        Player p1 = null, p2 = null, p3 = null;
        List<Player> players = myGame.getPlayers();

        for(int i = 0; i < players.size(); i++) {
            if(players.get(i).hasFirstPlayerCard()) {
                p1 = players.get( i      % (players.size()));
                p2 = players.get((i + 1) % (players.size()));
                p3 = players.get((i + 2) % (players.size()));
            }
        }
        assertNotNull(p1);
        assertNotNull(p2);
        assertNotNull(p3);


        // shoot is disabled due to absence of loaded weapon
        assertEquals(3 - 1, p1.getMoves().size());
        assertEquals(3 - 1, p2.getMoves().size());
        assertEquals(3 - 1, p3.getMoves().size());

        Turn lastTurn = myGame.getNextTurn();
        Player lastPlayer = lastTurn.getActivePlayer();
        myGame.concludeTurn(lastTurn);

        myGame.goFrenzy();

        assertEquals(2, p3.getMoves().size());
        assertEquals(3, p1.getMoves().size());
        assertEquals(2, p2.getMoves().size());

    }

    @Test
    public void testGoFrenzyCondition() throws Exception {
        GameParameters gp = GameParameters.fromConfig(Config.getDefaultConfig());
        gp.setGameMap(map);
        gp.setColorUserOrder(Arrays.asList(
                new ColorUserPair(PlayerColor.YELLOW, new User()),
                new ColorUserPair(PlayerColor.GREEN, new User()),
                new ColorUserPair(PlayerColor.GRAY, new User())
        ));

        GameImpl game = new GameImpl(gp);
        game.init();
        Player first = null;

        for(Player p : game.getPlayers()) {
            if(p.hasFirstPlayerCard()) {
                first = p;
                break;
            }
        }
        assertNotNull(first);
        assertTrue(first.hasFirstPlayerCard());

        game.goFrenzy();
    }

    @Test
    public void testSetUserForColor() throws Exception {
        game.setUserForColor(new User(), PlayerColor.MAGENTA);
    }

    @Test
    public void testGetUserByPlayer() throws Exception {
        Player p1 = game.getPlayerFromColor(PlayerColor.MAGENTA);
        assertNotNull(game.getUserByPlayer(p1));
    }

    @Test(expected = IllegalStateException.class)
    public void testGoFrenzyNegative() throws Exception {
        game.goFrenzy();
        assertFalse(game.shouldGoFrenzy());
        game.goFrenzy();
    }

    @Test
    public void testGetPlayerFromColorNegative() throws Exception {
        GameParameters gp = new GameParameters();
        gp.setGameMap(map);
        gp.setColorUserOrder(Arrays.asList(new ColorUserPair(
                PlayerColor.GREEN, new User()
        )));
        gp.setMinPlayers(1);
        Game myGame = new GameImpl(gp);
        myGame.init();
        assertNull(myGame.getPlayerFromColor(PlayerColor.MAGENTA));
    }

    @Test(expected = IllegalStateException.class)
    public void testInitNegativeNoPlayers() throws Exception {
        GameParameters gp = new GameParameters();
        Game mygame = new GameImpl(gp);
        mygame.init();
    }

    @Test(expected = IllegalStateException.class)
    public void testInitNegativeDouble() throws Exception {
        GameParameters gp = new GameParameters();
        gp.setColorUserOrder(Arrays.asList(
                new ColorUserPair(PlayerColor.MAGENTA, new User())
        ));
        Game myGame = new GameImpl(gp);
        myGame.init();
        try{
            myGame.init();
        }
        catch (IllegalStateException e) {
            throw e;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetUserForColorNegativeNoColor() throws Exception {
        game.setUserForColor(new User(), PlayerColor.GREEN);
    }

    @Test(expected = IllegalStateException.class)
    public void testInitNEgativeNoPlayers() throws Exception {
        GameParameters gp = new GameParameters();
        Game myGame = new GameImpl(gp);
        myGame.init();
    }

    @Test(expected = IllegalStateException.class)
    public void testTesetNegativeNoPlayers() throws Exception {
        GameParameters gp = new GameParameters();
        Game myGame = new GameImpl(gp);
        myGame.init();
    }

    @Test
    public void testSetListener() throws Exception {
        game.setGameListener(new NullGameListener());
    }

    @Test
    public void testDoubleKill() throws Exception {
        GameParameters gp = GameParameters.fromConfig(Config.getDefaultConfig());
        gp.setColorUserOrder(Arrays.asList(
                new ColorUserPair(PlayerColor.MAGENTA, new User()),
                new ColorUserPair(PlayerColor.GREEN, new User()),
                new ColorUserPair(PlayerColor.GRAY, new User())
        ));
        gp.setGameMap(TestingMapBuilder.getNewMap(this.getClass()));
        Game g = new GameImpl(gp);
        g.init();
        Turn t = g.getNextTurn();
        Player p = t.getActivePlayer();

        VirtualGameView vgv = new NullVirtualGameView();
        game.setGameListener(vgv);
        game.setKillTrackListener(new VirtualKillTrackView(vgv)); //???

        p.getDamageBoard().setListener(new VirtualDamageBoardView(p, vgv));

        List<Player> opponents = g.getPlayers();
        opponents.remove(p);
        assertEquals(2, opponents.size());
        Player o1 = opponents.get(0);
        o1.getDamageBoard().setListener(new VirtualDamageBoardView(o1, vgv));
        Player o2 = opponents.get(1);
        o2.getDamageBoard().setListener(new VirtualDamageBoardView(o2, vgv));
        Map m = g.getMap();
        Tile spawn = m.getSpawnPointFromColor(AmmoColor.RED);
        m.movePlayer(p, spawn);
        m.movePlayer(o1, spawn);
        m.movePlayer(o2, spawn);

        new DamageGameChange(p,o1,DamageBoardImpl.DEATH_SHOT_INDEX + 1, 0).update(g);
        new DamageGameChange(p,o2,DamageBoardImpl.DEATH_SHOT_INDEX + 1, 0).update(g);

        g.concludeTurn(t);
        assertEquals(19, p.getScore());
    }

    @Test
    public void testFrenzyTurnGeneration() throws Exception {
        game.goFrenzy();
        game.concludeTurn(game.getNextTurn());
    }

    @Test
    public void testGetTurnTime() throws Exception {
        assertEquals(gp.getTurnTime(), game.getTurnTime());
    }

    @Test
    public void testSetKilltrackListenerOngoing() throws Exception {
        game.setKillTrackListener(new NullKillTrackLister());
    }

    @Test
    public void testGetPowerUpDeck() throws Exception {
        // TODO assert not null
        game.getPowerUpDeck();
    }
}