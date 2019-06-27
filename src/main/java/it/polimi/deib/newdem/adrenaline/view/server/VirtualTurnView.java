package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.controller.InterruptExecutionException;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.Effect;
import it.polimi.deib.newdem.adrenaline.controller.effects.MetaPlayer;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.turn.TurnDataSource;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.TurnView;
import it.polimi.deib.newdem.adrenaline.view.inet.UserConnection;
import it.polimi.deib.newdem.adrenaline.view.inet.UserEventLocker;
import it.polimi.deib.newdem.adrenaline.view.inet.events.ActionSelectionEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.ActionSelectionRequest;
import it.polimi.deib.newdem.adrenaline.view.inet.events.PowerUpCardSelectionEvent;
import it.polimi.deib.newdem.adrenaline.view.inet.events.PowerUpCardSelectionRequest;

import java.util.List;
import java.util.stream.Collectors;

public class VirtualTurnView implements TurnDataSource, TurnView { // ActionView

    private User user;
    private boolean powerUpReceived;
    private int receivedPowerUpCardId;

    public VirtualTurnView(User user) {
        this.user = user;
        this.powerUpReceived = false;
        this.receivedPowerUpCardId = -1;
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

    private synchronized void receivePowerUp(UserConnection connection, PowerUpCardSelectionEvent event) {
        this.powerUpReceived = true;
        this.receivedPowerUpCardId = event.getSelectedCardID();
        notifyAll();
    }

    @Override
    public Player actionDidRequestPlayerBinding(MetaPlayer metaPlayer, PlayerSelector selector) {
        // TODO
        return null;
    }

    @Override
    public Tile actionDidRequestTile(TileSelector selector) {
        return null;
    }

    @Override
    public int actionDidRequestChoiche(List<Effect> choices) {
        return 0;
    }

    @Override
    public int actionDidRequestAdditionalDamage() {
        return 0;
    }

    @Override
    public int actionDidRequestRevengeMark(Player attackedPlayer) {
        return 0;
    }

    @Override
    public void actionDidEmitGameChange(GameChange gameChange) {

    }

    // \/ VIEW
    @Override
    public PlayerColor askForPlayer(MetaPlayer metaPlayer, List<PlayerColor> allowedPlayers) {
        return null;
    }

    @Override
    public TilePosition askForTile(List<TilePosition> legalTiles) {
        return null;
    }

    @Override
    public int askForChoice(List<Integer> allowedChoices) {
        return 0;
    }

    // missing methods for action elements
}
