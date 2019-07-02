package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.map.OrdinaryTile;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;

import java.util.ArrayList;
import java.util.List;

import static sun.swing.MenuItemLayoutHelper.max;

public class ScriptedDataSource implements TurnDataSource {

    ActionType[] arr;
    List<Tile> tiles;
    List<Integer> pups;
    List<Integer> weaponCardIndex;
    int i;
    int paymentsToUndo;

    private static final int UNDO_TILE_X = 90;
    private static final int UNDO_TILE_Y = 90;
    private static final int UNDO_WEAPON_CARD_INDEX = 10;
    private static final int UNDO_PUP_INDEX = 42;


    public ScriptedDataSource(ActionType ... types) {
        arr = types;
        i = 0;
        tiles = new ArrayList<>();
        pups = new ArrayList<>();
        weaponCardIndex = new ArrayList<>();
        paymentsToUndo = 0;
    }

    public static Tile getUndoTile() {
        return new OrdinaryTile(new TilePosition(UNDO_TILE_X, UNDO_TILE_Y));
    }

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


    @Override
    public ActionType requestAction(List<ActionType> actionTypeList) throws UndoException {
        ActionType out = arr[i];
        i++;
        return out;
    }

    @Override
    public void pushActor(Player actor) {

    }

    @Override
    public void popActor(Player actor) {

    }

    @Override
    public Player requestPlayer(MetaPlayer metaPlayer, PlayerSelector selector, boolean forceChoice) throws UndoException {
        return null;
    }
/*
    @Override
    public PowerUpCard chooseCard(List<PowerUpCard> cards) {
        return null;
    }
*/

    public static int getUndoWeaponCardIndex() {
        return UNDO_WEAPON_CARD_INDEX;
    }

    public void pushWeaponCardIndex(int i) {
        weaponCardIndex.add(i);
    }

    public int getWeaponCardLeftovers() {
        return weaponCardIndex.size();
    }

    @Override
    public WeaponCard chooseWeaponCard(List<WeaponCard> cards) throws UndoException {
        int index = weaponCardIndex.remove(weaponCardIndex.size() - 1);
        if(UNDO_WEAPON_CARD_INDEX == index) throw new UndoException();
        return cards.get(index);
    }

    @Override
    public PowerUpCard choosePowerUpCard(List<PowerUpCard> cards) throws UndoException {
        int index = pups.remove(pups.size() - 1);
        if(UNDO_PUP_INDEX == index) throw new UndoException();
        PowerUpCard pup = cards.get(index);
        if(null == pup) throw new IllegalStateException();
        return pup;
    }

        @Override
        public Tile requestTile(TileSelector selector, boolean forceChoice) throws UndoException {
            Tile tile = tiles.remove(tiles.size() - 1);
            if(null == tile) throw new IllegalStateException();
            if(tile.getPosition().equals(new TilePosition(UNDO_TILE_X,UNDO_TILE_Y))) throw new UndoException();
            return tile;
        }
/*
    @Override
    public Player actionDidRequestPlayer(MetaPlayer metaPlayer, PlayerSelector selector) {
        return null;
    }

    @Override
    public Tile actionDidRequestTile(TileSelector selector) throws UndoException {
        Tile tile = tiles.remove(tiles.size() - 1);
        if(null == tile) throw new IllegalStateException();
        if(tile.getPosition().equals(new TilePosition(UNDO_TILE_X,UNDO_TILE_Y))) throw new UndoException();
        return tile;
    }

        @Override
        public void turnDidStart(Player actor) {

        }
*/
    @Override
    public Integer requestFragment(int cardID, List<Integer> fragments, boolean forceChoice) throws UndoException {
        return null;
    }

    public void undoNextPayment() {
        paymentsToUndo = max(paymentsToUndo, 1);
    }

    public void addScheduledUndoPayment() {
        paymentsToUndo++;
    }

    @Override
    public PaymentReceipt requestPayment(PaymentInvoice invoice, Integer choice) throws UndoException {
        if(paymentsToUndo > 0) {
            paymentsToUndo--;
            throw new UndoException();
        }
        return new PaymentReceipt(invoice.getRedAmmos(), invoice.getBlueAmmos(), invoice.getYellowAmmos(), new ArrayList<>());
    }

}
