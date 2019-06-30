package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.TestingUtils;
import it.polimi.deib.newdem.adrenaline.controller.Config;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionDataSource;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.MoveAction;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.ColorUserPair;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameImpl;
import it.polimi.deib.newdem.adrenaline.model.game.GameParameters;
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

import static org.junit.Assert.*;

public class TestTurnBaseImpl {

    Map map;
    Player player1;
    Player player2;
    Player player3;
    Player player4;
    Game game;
    OrdinaryTurn ordinaryTurn;
    FirstTurn firstTurn;
    TurnDataSource dataSource;
    ActionDataSource actionDataSource;

    private class MockActionDataSource implements ActionDataSource{

        @Override
        public Player actionDidRequestPlayer(MetaPlayer metaPlayer, PlayerSelector selector) throws UndoException {
            return player1;
        }

        @Override
        public Tile actionDidRequestTile(TileSelector selector) throws UndoException {
            return map.getTile(new TilePosition(1,0));
        }

        @Override
        public int actionDidRequestChoice(List<Integer> choices) throws UndoException {
            return 1;
        }

        @Override
        public PaymentReceipt actionDidRequestPayment(PaymentInvoice invoice, Integer choice) throws UndoException {
            return new PaymentReceipt(1, 0, 0, new ArrayList<>());
        }
    }

    private class MockTurnDataSource implements TurnDataSource{

        @Override
        public ActionType chooseAction(List<ActionType> actionTypeList) throws UndoException {
            actionDataSource = new MockActionDataSource();
            return new ActionType(AtomicActionType.MOVE1);
        }

        @Override
        public PowerUpCard chooseCard(List<PowerUpCard> cards) throws UndoException {
            return game.getPowerUpDeck().getCardFromId(5);
        }

        @Override
        public void turnDidStart(Player actor) {

        }

        @Override
        public Player actionDidRequestPlayer(MetaPlayer metaPlayer, PlayerSelector selector) throws UndoException {
            return null;
        }

        @Override
        public Tile actionDidRequestTile(TileSelector selector) throws UndoException {
            return null;
        }

        @Override
        public int actionDidRequestChoice(List<Integer> choices) throws UndoException {
            return 0;
        }

        @Override
        public PaymentReceipt actionDidRequestPayment(PaymentInvoice invoice, Integer choice) throws UndoException {
            return null;
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
        TestingUtils.loadSingleton();

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

        player1.getInventory().addAmmo(AmmoColor.RED, 1);
        player2.getInventory().addAmmo(AmmoColor.BLUE, 1);
        player3.getInventory().addAmmo(AmmoColor.BLUE, 1);
        player4.getInventory().addAmmo(AmmoColor.BLUE,1);


        TilePosition tilePosition1 = new TilePosition(0,0);
        TilePosition tilePosition2 = new TilePosition(1,0);
        TilePosition tilePosition3 = new TilePosition(1,0);
        TilePosition tilePosition4 = new TilePosition(1,0);

        Tile destination1 = map.getTile(tilePosition1);
        Tile destination2 = map.getTile(tilePosition2);
        Tile destination3 = map.getTile(tilePosition3);
        Tile destination4 = map.getTile(tilePosition4);


        map.movePlayer(player1, destination1);
        map.movePlayer(player2, destination2);
        map.movePlayer(player3, destination3);
        map.movePlayer(player4,destination4);

        ordinaryTurn = new OrdinaryTurn(player1);
        firstTurn = new FirstTurn(player1);


        dataSource = new MockTurnDataSource();
        ordinaryTurn.bindDataSource(dataSource);
        firstTurn.bindDataSource(dataSource);

    }

    @Test
    public void bindDataSource() {
        firstTurn.bindDataSource(dataSource);
        ordinaryTurn.bindDataSource(dataSource);
    }

    @Test
    public void getActivePlayer() {
        assertEquals(player1, ordinaryTurn.getActivePlayer());
        assertEquals(player1, firstTurn.getActivePlayer());
    }

    @Test
    public void executeOrdinary() {
        ordinaryTurn.execute();
    }

    @Test
    public void executeFirst() {
        firstTurn.execute();
    }

    @Test
    public void respawnOrdinary(){
        player1.reportDeath(true);

        ordinaryTurn.execute();

        assertEquals(new TilePosition(3, 0), player1.getTile().getPosition());
    }


}