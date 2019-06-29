package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.MetaPlayer;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentReceiptData;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.List;

public interface TurnListener {

    void turnDidStart(Player actor);

    void turnWillEnd(Player actor);

    ActionType userDidRequestActionChoice(List<ActionType> actionTypeList) throws UndoException;

    PowerUpCard actionDidRequestPowerUpCard(List<PowerUpCard> cardsIDs) throws UndoException;

    Player actionDidRequestPlayer(MetaPlayer metaPlayer, List<Player> legalPlayers) throws UndoException;

    Tile actionDidRequestTile(List<Tile> legalTiles) throws UndoException;

    Integer actionDidRequestCardFragment(List<Integer> choices) throws UndoException;

    PaymentReceiptData actionDidRequestPayment(PaymentInvoice invoice, int choice) throws UndoException;
}
