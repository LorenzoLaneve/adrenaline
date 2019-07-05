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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestCyberbladeEffect {

    Map map;
    Player player1;
    Player player2;
    Player player3;
    EffectManager manager;
    EffectManager managerNoPayment;
    Game game;
    int counter;


    public class CyberbladeContext implements EffectContext{

        @Override
        public Player choosePlayer(MetaPlayer player, PlayerSelector selector, boolean forceChoice) throws UndoException {

            if(player == MetaPlayer.RED){
                return player2;
            }
            else{
                return player3;
            }

        }

        @Override
        public Tile chooseTile(TileSelector selector, boolean forceChoice) throws UndoException {
            return map.getTile(new TilePosition(1,0));
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

            return new PaymentReceipt(0,0,1, powerUpCards);
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

    public class CyberbladeContextNoPayment implements EffectContext {

        @Override
        public Player choosePlayer(MetaPlayer player, PlayerSelector selector, boolean forceChoice) throws UndoException {

            if(player == MetaPlayer.RED){
                return player2;
            }
            else{
                return player3;
            }

        }

        @Override
        public Tile chooseTile(TileSelector selector, boolean forceChoice) throws UndoException {
            return map.getTile(new TilePosition(1,0));
        }

        @Override
        public Integer chooseFragment(List<Integer> choices) throws UndoException {

            Integer returnInt = counter;

            counter++;


            return returnInt;
        }

        @Override
        public PaymentReceipt choosePayment(PaymentInvoice price, int choice) throws UndoException {
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
        map =  Map.createMap("TestMap.json");

        GameParameters gp = GameParameters.fromConfig(Config.getDefaultConfig());

        ColorUserPair colorUserPair1 = new ColorUserPair(PlayerColor.YELLOW, new User());
        ColorUserPair colorUserPair2 = new ColorUserPair(PlayerColor.GREEN, new User());
        ColorUserPair colorUserPair3 = new ColorUserPair(PlayerColor.GRAY, new User());
        List<ColorUserPair> listPairs = new ArrayList<>();
        listPairs.add(colorUserPair1);
        listPairs.add(colorUserPair2);
        listPairs.add(colorUserPair3);

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

        player1.getDamageBoard().setListener(new VirtualDamageBoardView(player1, vgv));
        player2.getDamageBoard().setListener(new VirtualDamageBoardView(player2, vgv));
        player3.getDamageBoard().setListener(new VirtualDamageBoardView(player3, vgv));

        player1.getInventory().addAmmo(AmmoColor.YELLOW, 1);
        player2.getInventory().addAmmo(AmmoColor.BLUE, 1);
        player3.getInventory().addAmmo(AmmoColor.BLUE, 1);


        TilePosition tilePosition = new TilePosition(0,0);

        TilePosition tilePosition3 = new TilePosition(1,0);

        Tile destination = map.getTile(tilePosition);
        Tile destination3 = map.getTile(tilePosition3);


        map.movePlayer(player1, destination);
        map.movePlayer(player2, destination);
        map.movePlayer(player3, destination3);

        manager = new EffectManager(new CyberbladeContext());

        counter = 1;

    }

    /**
     * Tests the effect by executing it on a simple mock game and verifying that the changes generated by the effect are the ones expected.
     * In the environment tested in this method the effect requires payment.
     */
    @Test
    public void testApplyPayment() {
        CyberbladeEffect effect = new CyberbladeEffect();

        try{
            manager.execute(effect);
        }catch (UndoException e){
            fail();
        }

        assertEquals(2,player1.getInventory().getYellow());
        assertEquals(2,player2.getDamageFromPlayer(player1));
        assertEquals(2,player3.getDamageFromPlayer(player1));
        assertEquals(map.getTile(new TilePosition(1,0)), player1.getTile());


    }
    /**
     * Tests the effect by executing it on a simple mock game and verifying that the changes generated by the effect are the ones expected.
     * In the environment tested in this method the effect doesn't require payment.
     */
    @Test
    public void testApplyNoPayment() {
        CyberbladeEffect effect = new CyberbladeEffect();

        managerNoPayment = new EffectManager(new CyberbladeContextNoPayment());

        try{
            managerNoPayment.execute(effect);
        }catch (Exception e){
            fail();
        }

        assertEquals(3,player1.getInventory().getBlue());
        assertEquals(2,player2.getDamageFromPlayer(player1));
        assertEquals(0,player3.getDamageFromPlayer(player1));

        assertEquals(map.getTile(new TilePosition(1,0)), player1.getTile());


    }

}