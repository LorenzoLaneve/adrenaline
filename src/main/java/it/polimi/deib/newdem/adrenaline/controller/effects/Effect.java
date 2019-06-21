package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

@FunctionalInterface
public interface Effect {

    void apply(EffectManager manager, Player actor) throws UndoException;

}
