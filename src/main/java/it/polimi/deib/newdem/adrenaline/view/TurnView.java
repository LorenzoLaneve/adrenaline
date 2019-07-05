package it.polimi.deib.newdem.adrenaline.view;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.MetaPlayer;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentReceiptData;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;

import java.io.Serializable;
import java.util.List;

/**
 * View object that observes a turn and manages the interactions with the user.
 */
public interface TurnView {

    /**
     * A wrapper class used to add the "undo" value to the type {@code T}.
     */
    class ValOrUndo<T extends Serializable> implements Serializable {

        private T value;
        private boolean undo;

        public ValOrUndo() {
            this.undo = true;
        }

        public ValOrUndo(T value) {
            this.value = value;
            this.undo = false;
        }

        public T getValue() {
            return value;
        }

        public boolean shouldUndo() {
            return undo;
        }

    }

    /**
     * Notifies that the given player started a new turn.
     * If a turn is already started, then further calls to this method will mean
     * that the game is asking the given player a revenge power up.
     */
    void startTurn(PlayerColor actor);

    /**
     * Notifies that the game has finished asking the given player interactions,
     * either ordinary turn or revenge power ups.
     */
    void endTurn(PlayerColor actor);

    /**
     * Locks the current thread until the user has chosen an action among the given ones.
     */
    ValOrUndo<ActionType> chooseAction(List<ActionType> availableActions);

    /**
     * Locks the current thread until the user has chosen a weapon card among the given ones.
     */
    ValOrUndo<Integer> chooseWeaponCard(List<Integer> cardIDs);

    /**
     * Locks the current thread until the user has chosen a power up card among the given ones.
     */
    ValOrUndo<Integer> choosePowerUpCard(List<Integer> cardIDs);

    /**
     * Locks the current thread until the user has chosen a player among the given ones.
     * @param metaPlayer The meta player that will be bound to the chosen player.
     * @param forceChoice Whether the player has to be chosen or not.
     */
    ValOrUndo<PlayerColor> choosePlayer(MetaPlayer metaPlayer, List<PlayerColor> legalPlayers, boolean forceChoice);

    /**
     * Locks the current thread until the user has chosen a player among the given ones.
     * @param forceChoice Whether the tile has to be chosen or not.
     */
    ValOrUndo<TilePosition> chooseTile(List<TilePosition> legalTiles, boolean forceChoice);

    /**
     * Locks the current thread
     * @param cardID The ID of the weapon card containing the fragments.
     * @param fragments The selectable IDs of the fragment as specified by the card effect.
     * @param forceChoice Whether the fragment has to be chosen or not.
     */
    ValOrUndo<Integer> chooseCardFragment(Integer cardID, List<Integer> fragments, boolean forceChoice);

    /**
     * Asks the user to choose a payment. The user can reject the payment (returning {@code null}), or
     * choose what to use to pay, among ammos and power ups.
     * @param invoice The PaymentInvoice object representing the price.
     * @param playerInv An AmmoSet object containing the ammos that the player can use to pay.
     * @param cardIDs The ID of the power ups that can be used to pay
     * @param fragmentToPay An ID specifying what the user is going to pay.
     * @return
     */
    ValOrUndo<PaymentReceiptData> choosePayment(PaymentInvoice invoice, AmmoSet playerInv, List<Integer> cardIDs, int fragmentToPay);

}
