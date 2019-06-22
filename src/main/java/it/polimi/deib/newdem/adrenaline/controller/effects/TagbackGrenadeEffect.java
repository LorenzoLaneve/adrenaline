package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

public class TagbackGrenadeEffect implements Effect {

    @Override
    public void apply(EffectManager manager, Player actor) throws UndoException {
        manager.damagePlayer(actor, manager.getAttacker(), 0, 1);
    }

}
