package it.polimi.deib.newdem.adrenaline.model.game.changes;

import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentReceipt;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;

public class PaymentGameChange implements GameChange {

    private PaymentReceipt receipt;

    public PaymentGameChange(Player player, PaymentReceipt receipt) {
        // TODO
    }

    @Override
    public void update(Game game) {
        // TODO
    }

    @Override
    public void revert(Game game) {
        // TODO
    }
}
