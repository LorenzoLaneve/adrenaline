package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.Config;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.*;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerImpl;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.MockGame;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestElectroscytheEffect {

    Map map;
    Player player1;
    Player player2;
    Player player3;
    EffectManager manager;
    EffectManager managerNoPayment;
    Game game;
    int counter;

    public class ElectronScytheContextNoPayment implements EffectContext {

        @Override
        public Player choosePlayer(MetaPlayer player, PlayerSelector selector, boolean forceChoice) throws UndoException {
            return null;
        }

        @Override
        public Tile chooseTile(TileSelector selector, boolean forceChoice) throws UndoException {
            return null;
        }

        @Override
        public Integer chooseFragment(List<Integer> choices) throws UndoException {
            Integer returnInt = counter;

            counter++;


            return returnInt;
        }

        @Override
        public PaymentReceipt choosePayment(PaymentInvoice price, Integer choice) throws UndoException {
            return null;
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

    public class ElectronScytheContext implements EffectContext{


        @Override
        public Player choosePlayer(MetaPlayer player, PlayerSelector selector, boolean forceChoice) throws UndoException {
            return null;
        }

        @Override
        public Tile chooseTile(TileSelector selector, boolean forceChoice) throws UndoException {
            return null;
        }

        @Override
        public Integer chooseFragment(List<Integer> choices) throws UndoException {
            Integer returnInt = counter;

            counter++;


            return returnInt;
        }

        @Override
        public PaymentReceipt choosePayment(PaymentInvoice price, Integer choice) throws UndoException {
            List<PowerUpCard> powerUpCards = new ArrayList<>();

            return new PaymentReceipt(1,1,0, powerUpCards);
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
        map =  Map.createMap(this.getClass().getClassLoader().getResource("TestMap.json").getFile().replace("%20", " "));

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

        game.init();

        player1 = game.getPlayerFromColor(PlayerColor.YELLOW);
        player2 = game.getPlayerFromColor(PlayerColor.GREEN);
        player3 = game.getPlayerFromColor(PlayerColor.GRAY);

        player1.getInventory().addAmmo(AmmoColor.BLUE, 1);
        player1.getInventory().addAmmo(AmmoColor.RED, 1);
        player2.getInventory().addAmmo(AmmoColor.BLUE, 1);
        player3.getInventory().addAmmo(AmmoColor.BLUE, 1);


        TilePosition tilePosition = new TilePosition(0,0);

        Tile destination = map.getTile(tilePosition);

        map.movePlayer(player1, destination);
        map.movePlayer(player2, destination);
        map.movePlayer(player3, destination);

        manager = new EffectManager(new ElectronScytheContext());

    }

    @Test
    public void testApplyPayment() {
        ElectroscytheEffect effect = new ElectroscytheEffect();

        try{
            manager.execute(effect);
        }catch (Exception e){
            fail();
        }

        assertEquals(0,player1.getInventory().getBlue());
        assertEquals(2,player2.getDamageFromPlayer(player1));
        assertEquals(2,player3.getDamageFromPlayer(player1));


    }

    @Test
    public void testApplyNoPayment() {
        ElectroscytheEffect effect = new ElectroscytheEffect();

        managerNoPayment = new EffectManager(new ElectronScytheContextNoPayment());

        try{
            managerNoPayment.execute(effect);
        }catch (Exception e){
            fail();
        }

        assertEquals(1,player2.getDamageFromPlayer(player1));
        assertEquals(1,player3.getDamageFromPlayer(player1));


    }
}