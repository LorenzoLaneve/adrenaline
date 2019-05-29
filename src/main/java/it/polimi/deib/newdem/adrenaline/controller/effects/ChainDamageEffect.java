package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.VisiblePlayerSelector;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public class ChainDamageEffect extends ConcreteEffect {

    public ChainDamageEffect(int id) {
        super(id);
    }

    @Override
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        Player redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new VisiblePlayerSelector(attacker));
        visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 2, 0));

        if (/*visitor.pay(PaymentInvoice)*/true) {
            Player bluePlayer = visitor.getBoundPlayer(MetaPlayer.BLUE, new VisiblePlayerSelector(redPlayer));
            visitor.reportGameChange(new DamageGameChange(attacker, bluePlayer, 1, 0));

            if (/*visitor.pay(blabla))*/true) {
                Player greenPlayer = visitor.getBoundPlayer(MetaPlayer.GREEN, new VisiblePlayerSelector(bluePlayer));
                visitor.reportGameChange(new DamageGameChange(attacker, greenPlayer, 2, 0));
            }
        }
    }
}
