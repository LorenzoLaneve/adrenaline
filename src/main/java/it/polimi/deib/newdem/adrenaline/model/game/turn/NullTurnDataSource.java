package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.TimeoutException;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.List;

public class NullTurnDataSource implements TurnDataSource {

    @Override
    public ActionType requestAction(List<ActionType> actionTypeList) throws UndoException {
        return null;
    }

    @Override
    public void pushActor(Player actor) {

    }

    @Override
    public void popActor(Player actor) {

    }

    @Override
    public Player requestPlayer(MetaPlayer metaPlayer, PlayerSelector selector, boolean forceChoice) throws UndoException {
        return null;
    }

    @Override
    public WeaponCard chooseWeaponCard(List<WeaponCard> cards) throws UndoException {
        return null;
    }

    @Override
    public PowerUpCard choosePowerUpCard(List<PowerUpCard> cards) throws UndoException {
        return null;
    }

    @Override
    public Tile requestTile(TileSelector selector, boolean forceChoice) throws UndoException {
        return null;
    }

    @Override
    public Integer requestFragment(int cardID, List<Integer> fragments, boolean forceChoice) throws UndoException {
        return null;
    }

    @Override
    public PaymentReceipt requestPayment(PaymentInvoice invoice, Integer choice) throws UndoException {
        return null;
    }
}
