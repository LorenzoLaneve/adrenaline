package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.controller.InterruptExecutionException;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.model.game.turn.TurnDataSource;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserEventSubscriber;
import it.polimi.deib.newdem.adrenaline.view.inet.events.ActionSelectionEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.ActionSelectionRequest;
import it.polimi.deib.newdem.adrenaline.view.inet.events.PowerUpCardSelectionEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.PowerUpCardSelectionRequest;

import java.util.List;
import java.util.stream.Collectors;

public class VirtualTurnView implements TurnDataSource {

    private User user;

    private ActionType receivedActionType;

    private int receivedPowerUpCard;

    private boolean powerUpReceived;


    private final UserEventSubscriber<ActionSelectionEvent> actionSubscriber;

    private final UserEventSubscriber<PowerUpCardSelectionEvent> powerUpCardSubscriber;


    public VirtualTurnView(User user) {
        this.user = user;

        this.actionSubscriber = this::receiveActionType;
        this.powerUpCardSubscriber = this::receivePowerUp;
    }


    @Override
    public synchronized ActionType chooseAction(List<ActionType> actionTypeList) {
        if (actionTypeList == null) {
            throw new IllegalArgumentException("Action type list must not be null");
        }

        user.subscribeEvent(ActionSelectionEvent.class, actionSubscriber);
        user.sendEvent(new ActionSelectionRequest(actionTypeList));

        this.receivedActionType = null;
        try {
            do {
                wait();

                if (receivedActionType != null && !actionTypeList.contains(receivedActionType)) {
                    user.sendEvent(new ActionSelectionRequest(actionTypeList));

                    this.receivedActionType = null;
                }

            } while (receivedActionType == null);

        } catch (Exception x) {
            user.unsubscribeEvent(ActionSelectionEvent.class, actionSubscriber);

            throw new InterruptExecutionException("Time out on action type request");
        }


        user.unsubscribeEvent(ActionSelectionEvent.class, actionSubscriber);
        return receivedActionType;
    }

    private synchronized void receiveActionType(UserConnection connection, ActionSelectionEvent event) {
        this.receivedActionType = event.getSelectedActionType();
        notifyAll();
    }


    @Override
    public synchronized PowerUpCard chooseCard(List<PowerUpCard> cards) {
        if (cards == null) {
            throw new IllegalArgumentException("Card list must not be null");
        }

        List<Integer> cardList = cards.stream().map(PowerUpCard::getCardID).collect(Collectors.toList());

        user.subscribeEvent(PowerUpCardSelectionEvent.class, powerUpCardSubscriber);
        user.sendEvent(new PowerUpCardSelectionRequest(cards));

        this.powerUpReceived = false;
        try {
            do {
                wait();

                if (powerUpReceived && !cardList.contains(receivedPowerUpCard)) {
                    user.sendEvent(new PowerUpCardSelectionRequest(cards));

                    this.powerUpReceived = false;
                }

            } while (!powerUpReceived);

        } catch (Exception x) {
            user.unsubscribeEvent(PowerUpCardSelectionEvent.class, powerUpCardSubscriber);

            throw new InterruptExecutionException("Time out on action type request");
        }


        user.unsubscribeEvent(ActionSelectionEvent.class, actionSubscriber);

        for (PowerUpCard card : cards) {
            if (card.getCardID() == receivedPowerUpCard) {
                return card;
            }
        }
        return null;
    }

    private synchronized void receivePowerUp(UserConnection connection, PowerUpCardSelectionEvent event) {
        this.receivedPowerUpCard = event.getSelectedCardID();
        notifyAll();
    }

}
