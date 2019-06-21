package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NegatedPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.VisiblePlayerSelector;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public class HeatseekerEffect implements Effect {

    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new NegatedPlayerSelector(
                new VisiblePlayerSelector(actor)
        ));

        manager.damagePlayer(actor, redPlayer, 3, 0);
    }
}
