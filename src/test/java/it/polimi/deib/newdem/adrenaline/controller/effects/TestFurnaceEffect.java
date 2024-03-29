package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.TestingUtils;
import it.polimi.deib.newdem.adrenaline.controller.Config;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.*;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.server.NullVirtualGameView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualDamageBoardView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualGameView;
import it.polimi.deib.newdem.adrenaline.view.server.VirtualKillTrackView;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.*;

public class TestFurnaceEffect {

    Map map;
    Player player1;
    Player player2;
    Player player3;
    Player player4;
    EffectManager manager;
    Game game;
    int counter;

    public class FurnaceContext implements EffectContext{

        @Override
        public Player choosePlayer(MetaPlayer player, PlayerSelector selector, boolean forceChoice) throws UndoException {

            if(player == MetaPlayer.RED){
                return player2;
            }
            else if(player == MetaPlayer.BLUE){
                return player3;
            }else{
                return player4;
            }

        }

        @Override
        public Tile chooseTile(TileSelector selector, boolean forceChoice) throws UndoException {
            if(counter == 3){
                return map.getTile(new TilePosition(1,0));
            }else{
                return map.getTile(new TilePosition(0,1));
            }
        }

        @Override
        public Integer chooseFragment(List<Integer> choices) throws UndoException {
            Integer returnInt = counter;

            counter++;


            return returnInt;
        }

        @Override
        public PaymentReceipt choosePayment(PaymentInvoice price, int choice) throws UndoException {
            List<PowerUpCard> powerUpCards = new ArrayList<>();

            return null;
        }

        @Override
        public void damageDealtTrigger(Player attacker, Player victim) {

        }

        @Override
        public void damageTakenTrigger(Player attacker, Player victim) {

        }

        @Override
        public void applyGameChange(GameChange gameChange) {
            gameChange.update(game);
        }

        @Override
        public void revertGameChange(GameChange gameChange) {

        }

        @Override
        public Player getActor() {
            return player1;
        }

        @Override
        public Player getAttacker() {
            return null;
        }

        @Override
        public Player getVictim() {
            return null;
        }
    }


    @Before
    public void setUp() throws Exception {
        TestingUtils.loadSingleton();
        map =  Map.createMap("Map0_0.json");

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

        VirtualGameView vgv = new NullVirtualGameView();
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


        TilePosition tilePosition1 = new TilePosition(0,0);
        TilePosition tilePosition2 = new TilePosition(1,0);
        TilePosition tilePosition3 = new TilePosition(0,2);
        TilePosition tilePosition4 = new TilePosition(0,1);

        Tile destination1 = map.getTile(tilePosition1);
        Tile destination2 = map.getTile(tilePosition2);
        Tile destination3 = map.getTile(tilePosition3);
        Tile destination4 = map.getTile(tilePosition4);


        map.movePlayer(player1, destination1);
        map.movePlayer(player2, destination2);
        map.movePlayer(player3, destination3);
        map.movePlayer(player4,destination4);

        manager = new EffectManager(new FurnaceContext());

        counter = 0;

    }

    /**
     * Tests the effect by executing it on a simple mock game and verifying that the changes generated by the effect are the ones expected.
     * In the environment tested in this method the effect requires payment.
     */
    @Test
    public void testApply1() {
        counter = 1;
        FurnaceEffect effect = new FurnaceEffect();

        try{
            manager.execute(effect);
        }catch (Exception e){
            fail();
        }


        assertEquals(0,player2.getDamageFromPlayer(player1));
        assertEquals(1,player4.getDamageFromPlayer(player1));
        assertEquals(1,player3.getDamageFromPlayer(player1));
    }
    /**
     * Tests the effect by executing it on a simple mock game and verifying that the changes generated by the effect are the ones expected.
     * In the environment tested in this method the effect doesn't require payment.
     */
    @Test
    public void testApply0() {
        counter = 2;
        player3.getTile().getMap().movePlayer(player3, player2.getTile());
        FurnaceEffect effect = new FurnaceEffect();

        try{
            manager.execute(effect);
        }catch (Exception e){
            fail();
        }

        assertEquals(0,player4.getDamageFromPlayer(player1));
        assertEquals(1,player2.getDamageFromPlayer(player1));
        assertEquals(1,player2.getMarksFromPlayer(player1));
        assertEquals(1,player3.getMarksFromPlayer(player1));
        assertEquals(1,player3.getDamageFromPlayer(player1));
    }
}