package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.BlackListFilterPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.VisiblePlayerSelector;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.ArrayList;
import java.util.List;

public class LockRifleEffect implements Effect {

    private static final int SECOND_LOCK = 1;

    private static final PaymentInvoice SECOND_LOCK_PAYMENT = new PaymentInvoice(1,0,0,0);


    @Override
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        Player redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new VisiblePlayerSelector(attacker));

        visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 2, 1));

        if (visitor.requestPayment(attacker, SECOND_LOCK_PAYMENT, SECOND_LOCK)) {

            List<Player> excludedPlayers = new ArrayList<>();
            excludedPlayers.add(redPlayer);

            Player bluePlayer = visitor.getBoundPlayer(MetaPlayer.BLUE,
                    new BlackListFilterPlayerSelector(excludedPlayers, new VisiblePlayerSelector(attacker)));

            visitor.reportGameChange(new DamageGameChange(attacker, bluePlayer, 0, 1));
        }

    }

}
