package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.DirectionalPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.IntersectPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearPlayerSelector;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.changes.MovementGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Direction;

public class PowerGloveEffect implements Effect {

    private static final int ROCKET_FIST = 1;

    private static final PaymentInvoice ROCKET_FIST_PAYMENT = new PaymentInvoice(0,1,0,0);

    @Override
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        if (!visitor.requestPayment(attacker, ROCKET_FIST_PAYMENT, ROCKET_FIST)) {
            Player redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new NearPlayerSelector(attacker, 1, 1));

            visitor.reportGameChange(new MovementGameChange(attacker, redPlayer.getTile()));
            visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 1,2));

        } else {
            Player redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new NearPlayerSelector(attacker, 1,1));

            Direction comboDirection = attacker.getTile().getDirection(redPlayer.getTile());

            visitor.reportGameChange(new MovementGameChange(attacker, redPlayer.getTile()));
            visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 2, 0));

            Player bluePlayer = visitor.getBoundPlayer(MetaPlayer.BLUE, new IntersectPlayerSelector(
                    new DirectionalPlayerSelector(attacker, comboDirection, false),
                    new NearPlayerSelector(attacker, 1,1)
            ), false);

            if (bluePlayer != null) {
                visitor.reportGameChange(new MovementGameChange(attacker, bluePlayer.getTile()));
                visitor.reportGameChange(new DamageGameChange(attacker, bluePlayer, 2, 0));
            }

        }

    }

}
