package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.BlackListFilterPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.VisiblePlayerSelector;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.ArrayList;
import java.util.List;

public class LaserMagnumEffect implements Effect {

    private static final int GIGAWATT_MODE = 1;

    private static final PaymentInvoice GIGAWATT_MODE_PAYMENT = new PaymentInvoice(1,1,1,0);

    @Override
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        if (!visitor.requestPayment(attacker, GIGAWATT_MODE_PAYMENT, GIGAWATT_MODE)) {
            Player redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new VisiblePlayerSelector(attacker));

            visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 0,1));
        } else {
            Player redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new VisiblePlayerSelector(attacker), false);

            if (redPlayer != null) {
                visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 2, 0));

                List<Player> excludedPlayers = new ArrayList<>();
                excludedPlayers.add(redPlayer);

                PlayerSelector blueSelector = new BlackListFilterPlayerSelector(excludedPlayers,
                        new VisiblePlayerSelector(attacker));

                Player bluePlayer = visitor.getBoundPlayer(MetaPlayer.BLUE, blueSelector, false);
                if (bluePlayer != null) {
                    visitor.reportGameChange(new DamageGameChange(attacker, bluePlayer, 1, 0));
                }
            }
        }

    }

}
