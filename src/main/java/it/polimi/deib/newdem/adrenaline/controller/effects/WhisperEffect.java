package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearPlayerSelector;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public class WhisperEffect implements Effect {

    @Override
    public void apply(EffectVisitor visitor) throws UndoException {

        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        Player redPlayer = visitor.getBoundPlayer(MetaPlayer.RED, new NearPlayerSelector(attacker, 2, 100));

        visitor.reportGameChange(new DamageGameChange(attacker, redPlayer, 3, 1));
    }

}
