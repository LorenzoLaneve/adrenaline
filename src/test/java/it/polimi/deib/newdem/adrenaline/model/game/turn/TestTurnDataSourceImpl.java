package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.TestingUtils;
import it.polimi.deib.newdem.adrenaline.controller.Config;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.AtomicActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearTileSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.ColorUserPair;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameImpl;
import it.polimi.deib.newdem.adrenaline.model.game.GameParameters;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
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

public class TestTurnDataSourceImpl {

    Map map;
    Player player1;
    Player player2;
    Player player3;
    Player player4;
    Game game;
    TurnDataSource dataSource;

    private class MockTurnListener implements TurnListener{

        @Override
        public void turnDidStart(Player actor) {

        }

        @Override
        public void turnWillEnd(Player actor) {

        }

        @Override
        public ActionType turnDidRequestAction(List<ActionType> actionTypeList) throws UndoException {
            return new ActionType(AtomicActionType.MOVE1);
        }

        @Override
        public WeaponCard actionDidRequestWeaponCard(List<WeaponCard> availableCards) throws UndoException {
            return null;
        }

        @Override
        public PowerUpCard actionDidRequestPowerUpCard(List<PowerUpCard> cardsIDs) throws UndoException {
            return game.getPowerUpDeck().getCardFromId(5);
        }

        @Override
        public Player actionDidRequestPlayer(MetaPlayer metaPlayer, List<Player> legalPlayers, boolean forceChoice) throws UndoException {
            return player3;
        }

        @Override
        public Tile actionDidRequestTile(List<Tile> legalTiles, boolean forceChoice) throws UndoException {
            return map.getTile(new TilePosition(0,2));
        }

        @Override
        public Integer actionDidRequestCardFragment(Integer cardID, List<Integer> choices, boolean forceChoice) throws UndoException {
            return 1;
        }

        @Override
        public PaymentReceiptData actionDidRequestPayment(PaymentInvoice invoice, AmmoSet playerAmmos, List<Integer> powerUps, int fragmentToPay) throws UndoException {
            return new PaymentReceiptData(1, 1, 1, new ArrayList<>());
        }

    }

    @Before
    public void setUp() throws Exception {
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

        dataSource = new TurnDataSourceImpl(new MockTurnListener(), game);
    }

    @Test
    public void chooseAction() {
        List<ActionType> actionTypeList = new ArrayList<>();

        ActionType actionType = new ActionType(AtomicActionType.MOVE1);

        actionTypeList.add(actionType);

        try {
            dataSource.requestAction(actionTypeList);
        }catch (UndoException e){
            fail();
        }
    }

    @Test
    public void chooseCard() {
        PowerUpCard card1 = game.getPowerUpDeck().getCardFromId(4);
        PowerUpCard card2 = game.getPowerUpDeck().getCardFromId(3);

        List<PowerUpCard> powerUpCardList = new ArrayList<>();
        powerUpCardList.add(card1);
        powerUpCardList.add(card2);
        try {
            dataSource.choosePowerUpCard(powerUpCardList);
        }catch (UndoException e){
            fail();
        }

    }

    @Test
    public void actionDidRequestPlayer() {

       try {
           PlayerSelector selector = new NearPlayerSelector(player1, 1, 2);
           Player p = dataSource.requestPlayer(MetaPlayer.RED, selector, true);
           assertEquals(player3, p);
       }catch (UndoException e){
           fail();
       }
    }

    @Test
    public void actionDidRequestTile() {
        try{
            TileSelector tileSelector = new NearTileSelector(player1.getTile(), 1, 2);
            Tile tile = dataSource.requestTile(tileSelector, true);
            assertEquals(map.getTile(new TilePosition(0,2)), tile);
        }catch (UndoException e){
            fail();
        }
    }

    @Test
    public void actionDidRequestChoice() {
        List<Integer> choices = new ArrayList<>();
        choices.add(1);

        try {
            int choice = dataSource.requestFragment(1, choices, true);
            assertEquals(1, choice);
        }catch (UndoException e){
            fail();
        }
    }

    @Test
    public void actionDidRequestPayment() {
        dataSource.pushActor(player1);
        try{
            PaymentReceipt paymentReceipt = dataSource.requestPayment(
                    new PaymentInvoice(1,1,1,0),
                    1);
            assertEquals(1, paymentReceipt.getPayedBlueAmmos());
            assertEquals(1, paymentReceipt.getPayedRedAmmos());
            assertEquals(1, paymentReceipt.getPayedYellowAmmos());
        }catch (UndoException e){
            fail();
        }
    }

    @Test
    public void turnDidStart() {
        dataSource.pushActor(player1);
    }
}