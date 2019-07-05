package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.MetaPlayer;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentReceiptData;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.List;

public class NullTurnListener implements TurnListener {

    @Override
    public void turnDidStart(Player actor) {

    }

    @Override
    public void turnWillEnd(Player actor) {

    }

    @Override
    public ActionType turnDidRequestAction(List<ActionType> actionTypeList) throws UndoException {
        return null;
    }

    @Override
    public WeaponCard actionDidRequestWeaponCard(List<WeaponCard> availableCards) throws UndoException {
        return null;
    }

    @Override
    public PowerUpCard actionDidRequestPowerUpCard(List<PowerUpCard> availableCards) throws UndoException {
        return null;
    }

    @Override
    public Player actionDidRequestPlayer(MetaPlayer metaPlayer, List<Player> legalPlayers, boolean forceChoice) throws UndoException {
        return null;
    }

    @Override
    public Tile actionDidRequestTile(List<Tile> legalTiles, boolean forceChoice) throws UndoException {
        return null;
    }

    @Override
    public Integer actionDidRequestCardFragment(Integer cardID, List<Integer> choices, boolean forceChoice) throws UndoException {
        return null;
    }

    @Override
    public PaymentReceiptData actionDidRequestPayment(PaymentInvoice invoice, AmmoSet playerAmmos, List<Integer> powerUps, int fragmentToPay) throws UndoException {
        return null;
    }
}
