package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.controller.InterruptExecutionException;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.turn.TurnListener;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.TurnView;
import it.polimi.deib.newdem.adrenaline.view.inet.UserEventLocker;
import it.polimi.deib.newdem.adrenaline.view.inet.events.*;

import java.util.List;
import java.util.stream.Collectors;

public class VirtualTurnView implements TurnView, TurnListener {

    // TODO forceChoice flags

    private VirtualGameView gameView;

    private User user;

    public VirtualTurnView(VirtualGameView gameView, User user) {
        this.gameView = gameView;
        this.user = user;
    }

    private <T extends UserEvent> T waitOnEvent(Class<T> eventClass) {
        try {
            UserEventLocker<T> locker = new UserEventLocker<>();
            return locker.waitOnEvent(eventClass, user.getBoundConnection());
        } catch (Exception x) {
            throw new InterruptExecutionException("Time out on turn request.");
        }
    }


    // TurnListener methods
    @Override
    public void turnDidStart(Player actor) {
        startTurn(actor.getColor());
    }

    @Override
    public void turnWillEnd(Player actor) {
        endTurn(actor.getColor());
    }

    @Override
    public ActionType userDidRequestActionChoice(List<ActionType> actionTypeList) throws UndoException {
        ValOrUndo<ActionType> ret = chooseAction(actionTypeList);
        if (ret.shouldUndo()) throw new UndoException();
        return ret.getValue();
    }

    @Override
    public PowerUpCard actionDidRequestPowerUpCard(List<PowerUpCard> legalCards) throws UndoException {
        List<Integer> cardIDs = legalCards.stream().map(PowerUpCard::getCardID).collect(Collectors.toList());

        ValOrUndo<Integer> ret = choosePowerUpCard(cardIDs);
        if (ret.shouldUndo()) throw new UndoException();

        if (ret.getValue() != null) {
            for (PowerUpCard p : legalCards) {
                if (ret.getValue() == p.getCardID()) {
                    return p;
                }
            }
        }
        return null;
    }

    @Override
    public Player actionDidRequestPlayer(MetaPlayer metaPlayer, List<Player> legalPlayers) throws UndoException {
        List<PlayerColor> playerColors = legalPlayers.stream().map(Player::getColor).collect(Collectors.toList());

        ValOrUndo<PlayerColor> ret = choosePlayer(metaPlayer, playerColors, false);
        if (ret.shouldUndo()) throw new UndoException();

        if (ret.getValue() != null) {
            for (Player p : legalPlayers) {
                if (ret.getValue() == p.getColor()) {
                    return p;
                }
            }
        }
        return null;
    }

    @Override
    public Tile actionDidRequestTile(List<Tile> legalTiles) throws UndoException {
        List<TilePosition> tilePositions = legalTiles.stream().map(Tile::getPosition).collect(Collectors.toList());

        ValOrUndo<TilePosition> ret = chooseTile(tilePositions, false);
        if (ret.shouldUndo()) throw new UndoException();

        if (ret.getValue() != null) {
            for (Tile t : legalTiles) {
                if (ret.getValue().equals(t.getPosition())) {
                    return t;
                }
            }
        }
        return null;
    }

    @Override
    public Integer actionDidRequestCardFragment(List<Integer> choices) throws UndoException {
        ValOrUndo<Integer> ret = choosePowerUpCard(choices);
        if (ret.shouldUndo()) throw new UndoException();

        return ret.getValue();
    }

    @Override
    public PaymentReceiptData actionDidRequestPayment(PaymentInvoice invoice, int fragmentToPay) throws UndoException {
        ValOrUndo<PaymentReceiptData> ret = choosePayment(invoice, fragmentToPay);
        if (ret.shouldUndo()) throw new UndoException();

        return ret.getValue();
    }




    // TurnView methods
    @Override
    public void startTurn(PlayerColor actor) {
        gameView.sendEvent(new TurnStartEvent(actor));
    }

    @Override
    public void endTurn(PlayerColor actor) {
        gameView.sendEvent(new TurnEndEvent(actor));
    }

    @Override
    public ValOrUndo<ActionType> chooseAction(List<ActionType> availableActions) {
        user.sendEvent(new TurnActionRequest(availableActions));
        return waitOnEvent(TurnActionResponse.class).getValue();
    }

    @Override
    public ValOrUndo<Integer> choosePowerUpCard(List<Integer> cardIDs) {
        user.sendEvent(new TurnPowerUpRequest(cardIDs));
        return waitOnEvent(TurnPowerUpResponse.class).getValue();
    }

    @Override
    public ValOrUndo<PlayerColor> choosePlayer(MetaPlayer metaPlayer, List<PlayerColor> legalPlayers, boolean forceChoice) {
        user.sendEvent(new TurnPlayerRequest(metaPlayer, legalPlayers, forceChoice));
        return waitOnEvent(TurnPlayerResponse.class).getValue();
    }

    @Override
    public ValOrUndo<TilePosition> chooseTile(List<TilePosition> legalTiles, boolean forceChoice) {
        user.sendEvent(new TurnTileRequest(legalTiles, forceChoice));
        return waitOnEvent(TurnTileResponse.class).getValue();
    }

    @Override
    public ValOrUndo<Integer> chooseCardFragment(List<Integer> fragments, boolean forceChoice) {
        user.sendEvent(new TurnFragmentRequest(fragments, forceChoice));
        return waitOnEvent(TurnFragmentResponse.class).getValue();
    }

    @Override
    public ValOrUndo<PaymentReceiptData> choosePayment(PaymentInvoice invoice, int fragmentToPay) {
        user.sendEvent(new TurnPaymentRequest(invoice, fragmentToPay));
        return waitOnEvent(TurnPaymentResponse.class).getValue();
    }

}
