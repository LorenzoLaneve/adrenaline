package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.BlackListFilterPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.VisiblePlayerSelector;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.ArrayList;
import java.util.List;

public class MachineGunEffect implements Effect {

    private static final int FOCUS_SHOT = 1;

    private static final int TURRET_TRIPOD = 2;

    private static final PaymentInvoice FOCUS_SHOT_PAYMENT = new PaymentInvoice(0,0,1,0);

    private static final PaymentInvoice TURRET_TRIPOD_PAYMENT = new PaymentInvoice(0,1,0,0);

    @Override
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        Player redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new VisiblePlayerSelector(attacker));
        visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 1, 0));


        List<Player> excludedPlayers = new ArrayList<>();
        excludedPlayers.add(redPlayer);

        Player bluePlayer = visitor.getBoundPlayer(MetaPlayer.BLUE,
                new BlackListFilterPlayerSelector(excludedPlayers, new VisiblePlayerSelector(attacker)), false);
        if (bluePlayer != null) {
            visitor.reportGameChange(new DamageGameChange(attacker, bluePlayer, 1, 0));
        }

        if (visitor.requestPayment(attacker, FOCUS_SHOT_PAYMENT, FOCUS_SHOT)) {
            visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 1, 0));
        }

        if (visitor.requestPayment(attacker, TURRET_TRIPOD_PAYMENT, TURRET_TRIPOD)) {

            if (bluePlayer != null) {
                visitor.reportGameChange(new DamageGameChange(attacker, bluePlayer, 1,0));
            }

            excludedPlayers.add(bluePlayer);
            Player greenPlayer = visitor.getBoundPlayer(MetaPlayer.GREEN,
                    new BlackListFilterPlayerSelector(excludedPlayers, new VisiblePlayerSelector(attacker)), false);

            if (greenPlayer != null) {
                visitor.reportGameChange(new DamageGameChange(attacker, greenPlayer, 1, 0));
            }
        }


    }
}
