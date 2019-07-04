package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

/**
 * A simple function that represents the behaviour of a card.
 * Thanks to reflection, implementations of this interface can be seen as importable assets.
 * @see EffectManager for possible features that can be used to create a card effect.
 */
@FunctionalInterface
public interface Effect {

    /**
     * @param manager The effect manager that provides the context for this effect.
     * @param actor The player associated to the user that executes this effect.
     * @throws UndoException if the user requested to undo the last action.
     * @implNote Once an action (and so the called effect) has completely finished, it is not possible to undo it,
     */
    void apply(EffectManager manager, Player actor) throws UndoException;

}
