package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NegatedPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.VisiblePlayerSelector;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public class HeatseekerEffect implements Effect {

    @Override
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        Player redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new NegatedPlayerSelector(
                new VisiblePlayerSelector(attacker)
        ));

        visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 3, 0));
    }

}
