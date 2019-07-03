package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.controller.InterruptExecutionException;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.game.turn.TurnListener;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.model.mgmt.User;
import it.polimi.deib.newdem.adrenaline.view.TurnView;
import it.polimi.deib.newdem.adrenaline.view.inet.UserEventLocker;
import it.polimi.deib.newdem.adrenaline.view.inet.events.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VirtualTurnView implements TurnView, TurnListener {

    private VirtualGameView gameView;

    private List<User> usersOnHold;

    private User activeUser;

    public VirtualTurnView(VirtualGameView gameView) {
        this.gameView = gameView;
        this.usersOnHold = new ArrayList<>();
    }

    private <T extends UserEvent> T waitOnEvent(Class<T> eventClass) {
        try {
            UserEventLocker<T> locker = new UserEventLocker<>();
            return locker.waitOnEvent(eventClass, activeUser.getBoundConnection());
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
    public ActionType turnDidRequestAction(List<ActionType> actionTypeList) throws UndoException {
        ValOrUndo<ActionType> ret = chooseAction(actionTypeList);
        if (ret.shouldUndo()) throw new UndoException();
        return ret.getValue();
    }

    @Override
    public WeaponCard actionDidRequestWeaponCard(List<WeaponCard> availableCards) throws UndoException {
        List<Integer> cardIDs = availableCards.stream().map(WeaponCard::getCardID).collect(Collectors.toList());

        ValOrUndo<Integer> ret = chooseWeaponCard(cardIDs);
        if (ret.shouldUndo()) throw new UndoException();

        if (ret.getValue() != null) {
            for (WeaponCard p : availableCards) {
                if (ret.getValue() == p.getCardID()) {
                    return p;
                }
            }
        }
        return null;
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
    public Player actionDidRequestPlayer(MetaPlayer metaPlayer, List<Player> legalPlayers, boolean forceChoice) throws UndoException {
        List<PlayerColor> playerColors = legalPlayers.stream().map(Player::getColor).collect(Collectors.toList());

        ValOrUndo<PlayerColor> ret = choosePlayer(metaPlayer, playerColors, forceChoice);
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
    public Tile actionDidRequestTile(List<Tile> legalTiles, boolean forceChoice) throws UndoException {
        List<TilePosition> tilePositions = legalTiles.stream().map(Tile::getPosition).collect(Collectors.toList());

        ValOrUndo<TilePosition> ret = chooseTile(tilePositions, forceChoice);
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
    public Integer actionDidRequestCardFragment(Integer cardID, List<Integer> choices, boolean forceChoice) throws UndoException {
        ValOrUndo<Integer> ret = chooseCardFragment(cardID, choices, forceChoice);
        if (ret.shouldUndo()) throw new UndoException();

        return ret.getValue();
    }

    @Override
    public PaymentReceiptData actionDidRequestPayment(PaymentInvoice invoice, AmmoSet playerAmmos, List<Integer> powerUps, int fragmentToPay) throws UndoException {
        ValOrUndo<PaymentReceiptData> ret = choosePayment(invoice, playerAmmos, powerUps, fragmentToPay);
        if (ret.shouldUndo()) throw new UndoException();

        return ret.getValue();
    }




    // TurnView methods
    @Override
    public void startTurn(PlayerColor actor) {
        if (activeUser != null) {
            this.usersOnHold.add(activeUser);
        }
        this.activeUser = gameView.getUserFromColor(actor);

        if (usersOnHold.isEmpty()) {
            gameView.sendEvent(new TurnStartEvent(actor));
        } else {
            gameView.sendEvent(new RevengeStartEvent(actor));
        }
    }

    @Override
    public void endTurn(PlayerColor actor) {
        if (activeUser != gameView.getUserFromColor(actor)) {
            usersOnHold.remove(gameView.getUserFromColor(actor));
        } else {
            if (!usersOnHold.isEmpty()) {
                activeUser = usersOnHold.remove(usersOnHold.size() - 1);
            } else activeUser = null;
        }

        if (activeUser == null) {
            gameView.sendEvent(new TurnEndEvent(actor));
        } else {
            gameView.sendEvent(new RevengeEndEvent(actor));
        }
    }

    @Override
    public ValOrUndo<ActionType> chooseAction(List<ActionType> availableActions) {
        activeUser.sendEvent(new TurnActionRequest(availableActions));
        return waitOnEvent(TurnActionResponse.class).getValue();
    }

    @Override
    public ValOrUndo<Integer> chooseWeaponCard(List<Integer> cardIDs) {
        activeUser.sendEvent(new TurnWeaponRequest(cardIDs));
        return waitOnEvent(TurnWeaponResponse.class).getValue();
    }

    @Override
    public ValOrUndo<Integer> choosePowerUpCard(List<Integer> cardIDs) {
        activeUser.sendEvent(new TurnPowerUpRequest(cardIDs));
        return waitOnEvent(TurnPowerUpResponse.class).getValue();
    }

    @Override
    public ValOrUndo<PlayerColor> choosePlayer(MetaPlayer metaPlayer, List<PlayerColor> legalPlayers, boolean forceChoice) {
        activeUser.sendEvent(new TurnPlayerRequest(metaPlayer, legalPlayers, forceChoice));
        return waitOnEvent(TurnPlayerResponse.class).getValue();
    }

    @Override
    public ValOrUndo<TilePosition> chooseTile(List<TilePosition> legalTiles, boolean forceChoice) {
        activeUser.sendEvent(new TurnTileRequest(legalTiles, forceChoice));
        return waitOnEvent(TurnTileResponse.class).getValue();
    }

    @Override
    public ValOrUndo<Integer> chooseCardFragment(Integer cardID, List<Integer> fragments, boolean forceChoice) {
        activeUser.sendEvent(new TurnFragmentRequest(cardID, fragments, forceChoice));
        return waitOnEvent(TurnFragmentResponse.class).getValue();
    }

    @Override
    public ValOrUndo<PaymentReceiptData> choosePayment(PaymentInvoice invoice, AmmoSet playerInv, List<Integer> cardIDs, int fragmentToPay) {
        activeUser.sendEvent(new TurnPaymentRequest(invoice, playerInv, cardIDs, fragmentToPay));
        return waitOnEvent(TurnPaymentResponse.class).getValue();
    }

}
