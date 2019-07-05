package it.polimi.deib.newdem.adrenaline.model.game.turn;

import com.sun.org.apache.xpath.internal.operations.Bool;
import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.Deck;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.Weapon;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.map.MockGame;
import it.polimi.deib.newdem.adrenaline.model.map.OrdinaryTile;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import static sun.swing.MenuItemLayoutHelper.max;

public class ScriptedDataSource extends TurnDataSourceImpl {

    ActionType[] arr;
    List<Tile> tiles;
    List<Integer> pups;
    WeaponIndexQueue weaponCardIndex;
    int i;
    // int paymentsToUndo;
    Deque<SheduledPaymentType> paymentUndoQ;
    Deque<Integer> fragmentQ;
    // Deque<Player> playerFromMetaQ;
    PlayerRecordQueue playerFromMetaQ;

    private static final int UNDO_TILE_X = 90;
    private static final int UNDO_TILE_Y = 90;
    private static final int NULL_TILE_X = 89;
    private static final int NULL_TILE_Y = 89;
    private static final int UNDO_WEAPON_CARD_INDEX = 10;
    private static final int NULL_WEAPON_CARD_INDEX = 11;
    private static final int UNDO_PUP_INDEX = 42;
    private static final int NULL_PUP_INDEX = 43;


    public ScriptedDataSource(Game game, ActionType ... types) {
        super(new NullTurnListener(), game);
        arr = types;
        i = 0;
        tiles = new ArrayList<>();
        pups = new ArrayList<>();
        weaponCardIndex = new WeaponIndexQueue();
        paymentUndoQ = new ArrayDeque<>();
        fragmentQ = new ArrayDeque<>();
        playerFromMetaQ = new PlayerRecordQueue();
    }

    public static Tile getUndoTile() {
        return new OrdinaryTile(new TilePosition(UNDO_TILE_X, UNDO_TILE_Y));
    }

    public static Tile getNullTile() {return new OrdinaryTile(new TilePosition(NULL_TILE_X, NULL_TILE_Y)); }

    // Tile (90, 90) means throw undo exception
    public void pushTile(Tile tile) {
        tiles.add(tile);
    }

    public void pushPupIndex(Integer i) {
        pups.add(i);
    }

    public static int getUndoPupIndex() {
        return UNDO_PUP_INDEX;
    }

    public static int getNullPupIndex() {
        return NULL_PUP_INDEX;
    }


    @Override
    public ActionType requestAction(List<ActionType> actionTypeList) throws UndoException {
        ActionType out = arr[i];
        i++;
        return out;
    }

    public void pushUndoPlayer() {
        playerFromMetaQ.pushUndo();
    }

    public void pushNullPlayer() {
        playerFromMetaQ.pushNull();
    }

    public void pushPlayerFromMeta(Player p) {
        playerFromMetaQ.pushPlayer(p);
    }

    @Override
    public Player requestPlayer(MetaPlayer metaPlayer, PlayerSelector selector, boolean forceChoice) throws UndoException {
        // if(playerFromMetaQ.isEmpty()) return null;
        return playerFromMetaQ.pop();
    }

    public static int getUndoWeaponCardIndex() {
        return UNDO_WEAPON_CARD_INDEX;
    }

    public static int getNullWeaponCardIndex() { return NULL_WEAPON_CARD_INDEX; }

    public void pushWeaponCardIndex(int i) {
        weaponCardIndex.pushIndex(i);
    }

    public void pushWeaponCardId(int id) {
        weaponCardIndex.pushWeaponCardId(id);
    }

    public int getWeaponCardLeftovers() {
        return weaponCardIndex.size();
    }

    @Override
    public WeaponCard chooseWeaponCard(List<WeaponCard> cards) throws UndoException {
        IndexOrId unknown = weaponCardIndex.pop();
        if(unknown.isIndex()) {
            int index = unknown.getVal();
            if (UNDO_WEAPON_CARD_INDEX == index) throw new UndoException();
            if(NULL_WEAPON_CARD_INDEX == index) return null;
            return cards.get(index);
        }
        else {
            for(WeaponCard card : cards) {
                if (card.getCardID() == unknown.getVal()){
                    return card;
                }
            }
        }
        throw new IllegalStateException();
    }

    @Override
    public PowerUpCard choosePowerUpCard(List<PowerUpCard> cards) throws UndoException {
        int index = pups.remove(pups.size() - 1);
        if(UNDO_PUP_INDEX == index) throw new UndoException();
        if(NULL_PUP_INDEX == index) return null;
        PowerUpCard pup = cards.get(index);
        if(null == pup) throw new IllegalStateException();
        return pup;
    }

        @Override
        public Tile requestTile(TileSelector selector, boolean forceChoice) throws UndoException {
            Tile tile = tiles.remove(tiles.size() - 1);
            if(null == tile) throw new IllegalStateException();
            if(tile.getPosition().equals(new TilePosition(UNDO_TILE_X,UNDO_TILE_Y))) throw new UndoException();
            if(tile.getPosition().equals(new TilePosition(NULL_TILE_X,  NULL_TILE_Y))) return null;
            return tile;
        }

    public void pushFragment(Integer fragment) {
        fragmentQ.push(fragment);
    }

    @Override
    public Integer requestFragment(int cardID, List<Integer> fragments, boolean forceChoice) throws UndoException {
        if (fragmentQ.isEmpty()) return null;
        return fragmentQ.pop();
    }

    public void pushAcceptPayment() {
        paymentUndoQ.push(SheduledPaymentType.ACCEPT);
    }

    public void pushUndoPayment() {
        paymentUndoQ.push(SheduledPaymentType.UNDO);
    }

    public void pushNullPayment() {
        paymentUndoQ.push(SheduledPaymentType.NULL);
    }

    @Override
    public PaymentReceipt requestPayment(PaymentInvoice invoice, Integer choice) throws UndoException {
        if (paymentUndoQ.isEmpty()) return new PaymentReceipt(invoice.getRedAmmos(), invoice.getBlueAmmos(), invoice.getYellowAmmos(), new ArrayList<>());
        switch (paymentUndoQ.pop()) {
            case NULL: return null;
            case UNDO: throw new UndoException();
            case ACCEPT: return new PaymentReceipt(invoice.getRedAmmos(), invoice.getBlueAmmos(), invoice.getYellowAmmos(), new ArrayList<>());
        }
        return new PaymentReceipt(invoice.getRedAmmos(), invoice.getBlueAmmos(), invoice.getYellowAmmos(), new ArrayList<>());
    }
}
