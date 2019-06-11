package it.polimi.deib.newdem.adrenaline.controller.effects;

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
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.*;

public class TestShockwaveEffect {

    Map map;
    Player player1;
    Player player2;
    Player player3;
    Player player4;
    ShockwaveVisitor visitor;
    ShockwaveVisitorNoPayment visitorNoPayment;
    Game game;
    int counter;
    int effectsCounter;

    public class ShockwaveVisitor extends EffectVisitorBase{

        public ShockwaveVisitor(){
            super();
        }

        @Override
        public Player askForPlayer(MetaPlayer player, PlayerSelector selector, boolean mandatory) throws UndoException {

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
        public Tile askForTile(TileSelector selector) throws UndoException {

            if(counter == 1){
                counter++;
                return map.getTile(new TilePosition(1,0));
            }else{
                return map.getTile(new TilePosition(1,1));
            }
        }

        @Override
        public Integer askForEffectChoice(List<Integer> choices) throws UndoException {
            Integer returnInt = counter;

            counter++;


            return returnInt;
        }

        @Override
        public PaymentReceipt askForPayment(Player player, PaymentInvoice payment, Integer effect) throws UndoException {
            List<PowerUpCard> powerUpCards = new ArrayList<>();

            return new PaymentReceipt(0,0,1, powerUpCards);

        }

        @Override
        public void applyGameChange(GameChange gameChange) {
            gameChange.update(game);
        }

        @Override
        public void revertGameChange(GameChange gameChange) {

        }

        @Override
        public Player getAttacker() {
            return player1;
        }
    }

    public class ShockwaveVisitorNoPayment extends EffectVisitorBase{

        public ShockwaveVisitorNoPayment(){
            super();
        }

        @Override
        public Player askForPlayer(MetaPlayer player, PlayerSelector selector, boolean mandatory) throws UndoException {

            if(player == MetaPlayer.RED){
                return player2;
            }
            else if(player == MetaPlayer.BLUE){
                return player3;
            }else{
                return null;
            }

        }

        @Override
        public Tile askForTile(TileSelector selector) throws UndoException {

            if(counter == 1){
                counter++;
                return map.getTile(new TilePosition(1,0));
            }else{
                return map.getTile(new TilePosition(1,1));
            }
        }

        @Override
        public Integer askForEffectChoice(List<Integer> choices) throws UndoException {
            Integer returnInt = counter;

            counter++;


            return returnInt;
        }

        @Override
        public PaymentReceipt askForPayment(Player player, PaymentInvoice payment, Integer effect) throws UndoException {
            List<PowerUpCard> powerUpCards = new ArrayList<>();

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
        public Player getAttacker() {
            return player1;
        }
    }

    @Before
    public void setUp() throws Exception {
        map =  Map.createMap(this.getClass().getClassLoader().getResource("Map0_0.json").getFile().replace("%20", " "));

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

        game.init();

        player1 = game.getPlayerFromColor(PlayerColor.YELLOW);
        player2 = game.getPlayerFromColor(PlayerColor.GREEN);
        player3 = game.getPlayerFromColor(PlayerColor.GRAY);
        player4 = game.getPlayerFromColor(PlayerColor.MAGENTA);

        player1.getInventory().addAmmo(AmmoColor.RED, 1);
        player2.getInventory().addAmmo(AmmoColor.BLUE, 1);
        player3.getInventory().addAmmo(AmmoColor.BLUE, 1);
        player4.getInventory().addAmmo(AmmoColor.BLUE,1);


        TilePosition tilePosition1 = new TilePosition(1,0);
        TilePosition tilePosition2 = new TilePosition(0,0);
        TilePosition tilePosition3 = new TilePosition(1,1);
        TilePosition tilePosition4 = new TilePosition(1,1);

        Tile destination1 = map.getTile(tilePosition1);
        Tile destination2 = map.getTile(tilePosition2);
        Tile destination3 = map.getTile(tilePosition3);
        Tile destination4 = map.getTile(tilePosition4);


        map.movePlayer(player1, destination1);
        map.movePlayer(player2, destination2);
        map.movePlayer(player3, destination3);
        map.movePlayer(player4,destination4);

        visitor = new ShockwaveVisitor();

        counter = 0;
        effectsCounter = 0;
    }

    @Test
    public void testApplyPayment() {
        counter = 1;
        ShockwaveEffect effect = new ShockwaveEffect();

        try{
            effect.apply(visitor);
        }catch (Exception e){
            fail();
        }

        assertEquals(1,player2.getDamageFromPlayer(player1));
        assertEquals(1,player3.getDamageFromPlayer(player1));
        assertEquals(1,player4.getDamageFromPlayer(player1));

    }

    @Test
    public void testApplyNoPayment() {
        counter = 2;
        ShockwaveEffect effect = new ShockwaveEffect();

        visitorNoPayment = new ShockwaveVisitorNoPayment();

        TilePosition tilePosition4 = new TilePosition(2,1);
        Tile destination4 = map.getTile(tilePosition4);

        map.movePlayer(player4,destination4);

        try{
            effect.apply(visitorNoPayment);
        }catch (Exception e){
            fail();
        }

        assertEquals(1,player2.getDamageFromPlayer(player1));
        assertEquals(1,player3.getDamageFromPlayer(player1));
        assertEquals(0,player4.getDamageFromPlayer(player1));

    }
}