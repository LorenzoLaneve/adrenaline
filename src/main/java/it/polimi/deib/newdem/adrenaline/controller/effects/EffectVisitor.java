package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.List;

public interface EffectVisitor {

    /**
     * Returns a Player object associated with the given meta player.
     * If no player is bound, a callback will be sent to the caller to make a choice among players verifying the given selector.
     * @throws UndoException if the caller requested an undo action.
     */
    Player getBoundPlayer(MetaPlayer player, PlayerSelector selector) throws UndoException;

    /**
     * Prompts the caller for a tile among the tiles in the map responding to the given selector.
     * @throws UndoException if the caller requested an undo action.
     */
    Tile getTile(TileSelector selector) throws UndoException;

    /**
     * Prompts the caller to choose an effect among the given effects.
     * @throws UndoException if the caller requested an undo action.
     */
    Effect chooseEffect(List<Effect> choices) throws UndoException;

    /**
     * Adds the effect to the effects queue, implying that it will be executed in the future.
     */
    void scheduleEffect(Effect effect);

    /**
     * Generates a game change that has to be applied to the game as a consequence of the execution of this effect.
     */
    void reportGameChange(GameChange gameChange);

    void enqueueEffect();

}
