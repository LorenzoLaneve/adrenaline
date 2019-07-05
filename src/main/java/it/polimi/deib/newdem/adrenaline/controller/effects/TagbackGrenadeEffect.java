package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

/**
 * Card effect that implements Adrenaline' Tagback Grenade power up card.
 * @see Effect for further information about the card effects.
 */
public class TagbackGrenadeEffect implements Effect {

    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {
        manager.damagePlayer(actor, manager.getAttacker(), 0, 1);
    }

}
