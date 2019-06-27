package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.controller.InterruptExecutionException;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.model.game.turn.TurnDataSource;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.inet.UserEventLocker;
import it.polimi.deib.newdem.adrenaline.view.inet.events.ActionSelectionEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.ActionSelectionRequest;
import it.polimi.deib.newdem.adrenaline.view.inet.events.PowerUpCardSelectionEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.PowerUpCardSelectionRequest;

import java.util.List;
import java.util.stream.Collectors;

public class VirtualTurnView implements TurnDataSource {

    private User user;

    public VirtualTurnView(User user) {
        this.user = user;
    }


    @Override
    public synchronized ActionType chooseAction(List<ActionType> actionTypeList) {
        if (actionTypeList == null) {
            throw new IllegalArgumentException("Action type list must not be null");
        }

        ActionSelectionEvent event;

        user.sendEvent(new ActionSelectionRequest(actionTypeList));
        try {
            UserEventLocker<ActionSelectionEvent> locker = new UserEventLocker<>();
            do {
                event = locker.waitOnEvent(ActionSelectionEvent.class, user.getBoundConnection());
            } while (!actionTypeList.contains(event.getSelectedActionType()));
        } catch (Exception x) {
            throw new InterruptExecutionException("Time out on action type request");
        }

        return event.getSelectedActionType();
    }

    @Override
    public synchronized PowerUpCard chooseCard(List<PowerUpCard> cards) {
        if (cards == null) {
            throw new IllegalArgumentException("Card list must not be null");
        }

        List<Integer> cardList = cards.stream().map(PowerUpCard::getCardID).collect(Collectors.toList());

        PowerUpCardSelectionEvent event;

        user.sendEvent(new PowerUpCardSelectionRequest(cardList));
        try {
            UserEventLocker<PowerUpCardSelectionEvent> locker = new UserEventLocker<>();
            do {
                event = locker.waitOnEvent(PowerUpCardSelectionEvent.class, user.getBoundConnection());
            } while (!cardList.contains(event.getSelectedCardID()));
        } catch (Exception x) {
            throw new InterruptExecutionException("Time out on powerup request");
        }

        for (PowerUpCard card : cards) {
            if (card.getCardID() == event.getSelectedCardID()) {
                return card;
            }
        }
        return null;
    }

}
