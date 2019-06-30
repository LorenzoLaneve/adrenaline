package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.List;

/**
 * An object that represents a context where an effect can run.
 * This provides to an EffectManager all the methods that can be used to ask the actor for various choices
 * that are requested by the enclosing effect object.
 * It also provides the game context needed to apply and revert game changes reported by the EffectManager.
 */
public interface EffectContext {

    /**
     * Applies the given game change according to this context.
     */
    void applyGameChange(GameChange gameChange);

    /**
     * Reverts the given game change according to this context.
     * this methods MUST completely neutralize all the changes made by the corresponding call to applyGameChange()
     */
    void revertGameChange(GameChange gameChange);

    /**
     * Returns the player that is playing in this context.
     */
    Player getActor();

    /**
     * Returns the player that triggered this context, or {@code null} if this context is not damage-taken triggered.
     */
    Player getAttacker();

    /**
     * Returns the player that triggered this context, or {@code null} if this context is not damage-dealt triggered.
     */
    Player getVictim();


    /**
     * Asks the user associated with the actor to choose a player that responds to the given selector.
     * @param player A MetaPlayer object as mentioned in the card.
     * @param selector A PlayerSelector predicate that filters the selectable players.
     * @param forceChoice Whether the choice is mandatory or not.
     * @return The player chosen by the user.
     * @throws UndoException if the user requested to undo the last action.
     */
    Player choosePlayer(MetaPlayer player, PlayerSelector selector, boolean forceChoice) throws UndoException;

    /**
     * Asks the user associated with the actor to choose a tile.
     * @param selector A TileSelector predicate that filters the selectable tiles.
     * @param forceChoice Whether the choice is mandatory or not.
     * @return The tile chosen by the user.
     * @throws UndoException if the user requested to undo the last action.
     */
    Tile chooseTile(TileSelector selector, boolean forceChoice) throws UndoException;

    /**
     * Asks the user associated with the actor to choose a fragment.
     * @param choices The IDs of the selectable fragments.
     * @return The fragment chosen by the user.
     * @throws UndoException if the user requested to undo the last action.
     */
    Integer chooseFragment(List<Integer> choices) throws UndoException;

    /**
     * Requests the user associated with the actor to pay for a given choice.
     * @param price The price of the effect.
     * @param choice The ID of the effect the payment is for.
     * @return A PaymentReceipt object containing the user's choice about the equip used to pay,
     * or {@code null} if the payment was rejected.
     * @throws UndoException if the user requested to undo the last action.
     */
    PaymentReceipt choosePayment(PaymentInvoice price, int choice) throws UndoException;

    /**
     * Notifies a damage dealt trigger to the context. The attacker has to be asked for additional power ups to be called.
     * @param attacker The player whose attack triggered this event.
     * @param victim The player who took damage.
     */
    void damageDealtTrigger(Player attacker, Player victim);

    /**
     * Notifies a damage taken trigger to the context. The victim has to be asked for power ups to be applied to the attacker
     * @param attacker The player whose attack triggered this event.
     * @param victim The player who took damage.
     */
    void damageTakenTrigger(Player attacker, Player victim);

}
