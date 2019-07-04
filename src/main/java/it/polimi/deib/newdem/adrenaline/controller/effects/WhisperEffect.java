package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearPlayerSelector;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

/**
 * Card effect that implements Adrenaline' Whisper weapon card.
 * @see Effect for further information about the card effects.
 */
public class WhisperEffect implements Effect {

    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {
        Player redPlayer = manager.bindPlayer(MetaPlayer.RED, new NearPlayerSelector(actor, 2, 100));

        manager.damagePlayer(actor, redPlayer, 3, 1);
    }

}
