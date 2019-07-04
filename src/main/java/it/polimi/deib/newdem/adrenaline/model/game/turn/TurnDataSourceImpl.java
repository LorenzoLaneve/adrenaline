package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TurnDataSourceImpl implements TurnDataSource {

    private TurnListener listener;
    private Game game;

    private List<Player> playersOnHold;
    private Player activePlayer;

    public TurnDataSourceImpl(TurnListener listener, Game game) {
        this.playersOnHold = new ArrayList<>();
        this.activePlayer = null;

        this.listener = listener;
        this.game = game;
    }


    @Override
    public void pushActor(Player actor) {
        if (activePlayer != null) {
            this.playersOnHold.add(activePlayer);
        }
        this.activePlayer = actor;

        listener.turnDidStart(actor);
    }

    @Override
    public void popActor(Player actor) {
        if (activePlayer != actor) {
            playersOnHold.remove(actor);
        } else {
            if (!playersOnHold.isEmpty()) {
                activePlayer = playersOnHold.remove(playersOnHold.size() - 1);
            } else activePlayer = null;
        }

        listener.turnWillEnd(actor);
    }

    @Override
    public Player peekActor() {
        return activePlayer;
    }

    @Override
    public ActionType requestAction(List<ActionType> actionTypeList) throws UndoException {
        return listener.turnDidRequestAction(actionTypeList);
    }

    @Override
    public Player requestPlayer(MetaPlayer metaPlayer, PlayerSelector selector, boolean forceChoice) throws UndoException {
        Map m = game.getMap();
        List<Player> selectables = new ArrayList<>();
        for(Player p : game.getPlayers()) {
            if(selector.isSelectable(m, p)) {
                selectables.add(p);
            }
        }

        Player choice;
        do {
            choice = listener.actionDidRequestPlayer(metaPlayer, selectables, forceChoice);
        } while (forceChoice && (choice == null || !selectables.contains(choice)));

        return choice;
    }

    @Override
    public WeaponCard chooseWeaponCard(List<WeaponCard> cards) throws UndoException {
        return listener.actionDidRequestWeaponCard(cards);
    }

    @Override
    public PowerUpCard choosePowerUpCard(List<PowerUpCard> cards) throws UndoException {
        return listener.actionDidRequestPowerUpCard(cards);
    }

    @Override
    public Tile requestTile(TileSelector selector, boolean forceChoice) throws UndoException {
        List<Tile> selectables = new ArrayList<>();
        Map m = game.getMap();

        for(Tile tile : m.getAllTiles()) {
            if(selector.isSelectable(m, tile)) {
                selectables.add(tile);
            }
        }

        Tile choice;
        do {
            choice = listener.actionDidRequestTile(selectables, forceChoice);
        } while (forceChoice && (choice == null || !selectables.contains(choice)));

        return choice;
    }

    @Override
    public Integer requestFragment(int cardID, List<Integer> fragments, boolean forceChoice) throws UndoException {
        Integer choice;
        do {
            choice = listener.actionDidRequestCardFragment(cardID, fragments, forceChoice);
        } while (forceChoice && (choice == null || !fragments.contains(choice)));

        return choice;
    }

    @Override
    public PaymentReceipt requestPayment(PaymentInvoice invoice, Integer choice) throws UndoException {
        AmmoSet playerInv = activePlayer.getInventory().getAmmoSet();

        List<Integer> powerUpIDs = activePlayer.getInventory().getAllPowerUps().stream().map(PowerUpCard::getCardID).collect(Collectors.toList());

        PaymentReceipt receipt;
        do {
            PaymentReceiptData receiptData = listener.actionDidRequestPayment(invoice, playerInv, powerUpIDs, choice);

            if (receiptData == null) return null;

            receipt = PaymentReceipt.fromData(receiptData, game.getPowerUpDeck());
            for (PowerUpCard card : receipt.getPayedPowerUpCards()) {
                if (!activePlayer.getInventory().getAllPowerUps().contains(card)) {
                    receipt = null;
                    break;
                }
            }

            if (receipt != null && !invoice.matches(receipt)) {
                receipt = null;
            }
        } while (receipt == null);

        return receipt;
    }
}
