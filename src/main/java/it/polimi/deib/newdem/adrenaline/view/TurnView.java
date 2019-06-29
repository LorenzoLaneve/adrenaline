package it.polimi.deib.newdem.adrenaline.view;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.MetaPlayer;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentReceiptData;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;

import java.io.Serializable;
import java.util.List;

public interface TurnView {

    class ValOrUndo<T> implements Serializable {

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


    void startTurn(PlayerColor actor);

    void endTurn(PlayerColor actor);

    ValOrUndo<ActionType> chooseAction(List<ActionType> availableActions);

    ValOrUndo<Integer> choosePowerUpCard(List<Integer> cardIDs);

    ValOrUndo<PlayerColor> choosePlayer(MetaPlayer metaPlayer, List<PlayerColor> legalPlayers, boolean forceChoice);

    ValOrUndo<TilePosition> chooseTile(List<TilePosition> legalTiles, boolean forceChoice);

    ValOrUndo<Integer> chooseCardFragment(List<Integer> fragments, boolean forceChoice);

    ValOrUndo<PaymentReceiptData> choosePayment(PaymentInvoice invoice, int fragmentToPay);


}
