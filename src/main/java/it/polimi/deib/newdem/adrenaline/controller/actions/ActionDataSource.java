package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.List;

public interface ActionDataSource {

    Player actionDidRequestPlayer(MetaPlayer metaPlayer, PlayerSelector selector) throws UndoException;

    Tile actionDidRequestTile(TileSelector selector) throws UndoException;

    int actionDidRequestChoice(List<Integer> choices) throws UndoException;

    // TODO add payment
    // TODO add choiche fragment

    PaymentReceipt actionDidRequestPayment(PaymentInvoice invoice, Integer choice) throws UndoException;
}
