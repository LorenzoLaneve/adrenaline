package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearPlayerSelector;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public class WhisperEffect implements Effect {

    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new NearPlayerSelector(actor, 2, 100));

        manager.damagePlayer(actor, redPlayer, 3, 1);
    }

}
