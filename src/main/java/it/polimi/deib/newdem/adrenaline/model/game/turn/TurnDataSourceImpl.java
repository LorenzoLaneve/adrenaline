package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.Card;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.ArrayList;
import java.util.List;

public class TurnDataSourceImpl implements TurnDataSource {

    TurnListener listener;
    Game game;

    public TurnDataSourceImpl(TurnListener listener, Game game) {
        this.listener = listener;
        this.game = game;
    }

    @Override
    public ActionType chooseAction(List<ActionType> actionTypeList) throws UndoException {
        return listener.userDidRequestActionChoice(actionTypeList);
    }

    @Override
    public PowerUpCard chooseCard(List<PowerUpCard> cards) throws UndoException {
        return listener.actionDidRequestPowerUpCard(cards);
    }

    @Override
    public Player actionDidRequestPlayer(MetaPlayer metaPlayer, PlayerSelector selector) throws UndoException {
        Map m = game.getMap();
        List<Player> selectables = new ArrayList<>();
        for(Player p : game.getPlayers()) {
            if(selector.isSelectable(m, p)) {
                selectables.add(p);
            }
        }

        return listener.actionDidRequestPlayer(metaPlayer, selectables);
    }

    @Override
    public Tile actionDidRequestTile(TileSelector selector) throws UndoException {
        List<Tile> selectables = new ArrayList<>();
        Map m = game.getMap();

        for(Tile tile : m.getAllTiles()) {
            if(selector.isSelectable(m, tile)) {
                selectables.add(tile);
            }
        }

        return listener.actionDidRequestTile(selectables);
    }

    @Override
    public int actionDidRequestChoice(List<Integer> choices) throws UndoException {
        return listener.actionDidRequestCardFragment(choices);
    }

    @Override
    public PaymentReceipt actionDidRequestPayment(PaymentInvoice invoice, Integer choice) throws UndoException {
        PaymentReceiptData receiptData = listener.actionDidRequestPayment(invoice, choice);
        if (receiptData == null) return null;
        return receiptData.makeRich(game.getPowerUpDeck());
    }

    @Override
    public void turnDidStart(Player actor) {
        // TODO notify to views and stuff
    }
}
