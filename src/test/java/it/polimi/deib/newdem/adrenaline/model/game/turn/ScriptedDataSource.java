package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.map.OrdinaryTile;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.ArrayList;
import java.util.List;

public class ScriptedDataSource implements TurnDataSource {

    ActionType[] arr;
    List<Tile> tiles;
    List<Integer> pups;
    int i;
    private static final int UNDO_TILE_X = 90;
    private static final int UNDO_TILE_Y = 90;


    public ScriptedDataSource(ActionType ... types) {
        arr = types;
        i = 0;
        tiles = new ArrayList<>();
        pups = new ArrayList<>();
    }

    @Override
    public ActionType chooseAction(List<ActionType> actionTypeList) {
        ActionType out = arr[i];
        i++;
        return out;
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

    @Override
    public PowerUpCard chooseCard(List<PowerUpCard> cards) {

        PowerUpCard pup = cards.get(pups.remove(pups.size() - 1));
        if(null == pup) throw new IllegalStateException();
        return pup;
    }

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

    @Override
    public int actionDidRequestChoice(List<Integer> choices) throws UndoException {
        return 0;
    }

    @Override
    public PaymentReceipt actionDidRequestPayment(PaymentInvoice invoice, Integer choice) throws UndoException {
        return null;
    }
}
