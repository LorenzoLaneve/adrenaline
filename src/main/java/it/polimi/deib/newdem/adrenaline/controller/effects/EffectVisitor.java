package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.List;

public interface EffectVisitor {

    /**
     * Returns a Player object associated with the given meta player.
     * If no player is bound, a callback will be sent to the caller to make a choice among players verifying the given selector.
     * @throws UndoException if the caller requested an undo action.
     */
    default Player getBoundPlayer(MetaPlayer player, PlayerSelector selector) throws UndoException {
        return getBoundPlayer(player, selector, true);
    }

    /**
     * Returns a Player object associated with the given meta player.
     * If no player is bound, a callback will be sent to the caller to make a choice among players verifying the given selector.
     * @throws UndoException if the caller requested an undo action.
     * @return The player selected by the caller, or {@code null} if the player decided not to select anyone.
     * @apiNote if {@code mandatory} is set to {@code true}, the return value of this method must not be null.
     * If {@code null} is returned for a given meta player, then all the successive calls to this method with the same meta player
     * will return {@code null} as well, and without asking for the caller to choose.
     */
    Player getBoundPlayer(MetaPlayer player, PlayerSelector selector, boolean mandatory) throws UndoException;

    /**
     * Returns a Player object associated with the given meta player.
     * If no player is bound, {@code null} will be returned.
     * @see EffectVisitor#getBoundPlayer(MetaPlayer, PlayerSelector) overload that will ask to bound the player if it is not bound yet.
     */
    Player getBoundPlayer(MetaPlayer player);

    /**
     * Prompts the caller for a tile among the tiles in the map responding to the given selector.
     * @throws UndoException if the caller requested an undo action.
     */
    Tile getTile(TileSelector selector) throws UndoException;

    /**
     * Prompts the caller to choose an effect among the given effects.
     * @returns The integer for the effect chosen by the player, or {@code null} if the caller requested to stop.
     * @throws UndoException if the caller requested an undo action.
     */
    Integer chooseEffect(List<Integer> choices) throws UndoException;

    /**
     * Adds the effect to the effects queue, implying that it will be executed in the future.
     */
    void scheduleEffect(Effect effect);

    /**
     * Requests the caller to pay the given amount of ammos.
     * The caller may be asked to select power-ups to pay, and if the payment is accepted,
     * the visitor will report an ammo payment game change associated with the current effect.
     * @param player The player that will be charged of the payment.
     * @param effect An integer that identifies what the payment is for.
     * @return {@code true} iff the called accepted the payment.
     * @throws UndoException if the caller requested an undo action.
     */
    boolean requestPayment(Player player, PaymentInvoice payment, Integer effect) throws UndoException;

    /**
     * Generates a game change that has to be applied to the game as a consequence of the execution of this effect.
     */
    void reportGameChange(GameChange gameChange);

}
