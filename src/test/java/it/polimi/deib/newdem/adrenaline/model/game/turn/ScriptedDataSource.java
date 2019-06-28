package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.List;

public class ScriptedDataSource implements TurnDataSource {

    ActionType[] arr;
    int i;

    public ScriptedDataSource(ActionType ... types) {
        arr = types;
        i = 0;
    }

    @Override
    public ActionType chooseAction(List<ActionType> actionTypeList) {
        ActionType out = arr[i];
        i++;
        return out;
    }

    @Override
    public PowerUpCard chooseCard(List<PowerUpCard> cards) {
        return null;
    }

    @Override
    public Player actionDidRequestPlayer(MetaPlayer metaPlayer, PlayerSelector selector) {
        return null;
    }

    @Override
    public Tile actionDidRequestTile(TileSelector selector) {
        return null;
    }

    @Override
    public void turnDidStart(Player actor) {

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
