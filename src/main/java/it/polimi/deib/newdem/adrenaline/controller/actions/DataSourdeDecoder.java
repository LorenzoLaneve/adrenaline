package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.controller.effects.MetaPlayer;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentReceipt;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import it.polimi.deib.newdem.adrenaline.view.TurnView;

import java.util.List;

public class DataSourdeDecoder implements ActionDataSource {

    TurnView turnView;

    public DataSourdeDecoder(TurnView turnView) {
        this.turnView = turnView;
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
    public int actionDidRequestChoice(List<Integer> choices) {
        return 0;
    }

    @Override
    public PaymentReceipt actionDidRequestPayment(PaymentInvoice invoice, Integer choice) {
        return null;
    }
}
