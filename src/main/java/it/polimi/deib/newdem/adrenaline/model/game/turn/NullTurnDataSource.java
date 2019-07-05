package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.List;

/**
 * A null object to allow testing and default values.
 *
 * All of this methods do not report to anything.
 */
public class NullTurnDataSource implements TurnDataSource {

    /**
     * This method does not report to anything
     */
    @Override
    public ActionType requestAction(List<ActionType> actionTypeList) throws UndoException {
        return null;
    }

    /**
     * This method does not report to anything
     */
    @Override
    public void pushActor(Player actor) {

    }

    /**
     * This method does not report to anything
     */
    @Override
    public void popActor(Player actor) {

    }

    /**
     * This method does not report to anything
     */
    @Override
    public Player requestPlayer(MetaPlayer metaPlayer, PlayerSelector selector, boolean forceChoice) throws UndoException {
        return null;
    }

    /**
     * This method does not report to anything
     */
    @Override
    public WeaponCard chooseWeaponCard(List<WeaponCard> cards) throws UndoException {
        return null;
    }

    /**
     * This method does not report to anything
     */
    @Override
    public PowerUpCard choosePowerUpCard(List<PowerUpCard> cards) throws UndoException {
        return null;
    }

    /**
     * This method does not report to anything
     */
    @Override
    public Tile requestTile(TileSelector selector, boolean forceChoice) throws UndoException {
        return null;
    }

    /**
     * This method does not report to anything
     */
    @Override
    public Integer requestFragment(int cardID, List<Integer> fragments, boolean forceChoice) throws UndoException {
        return null;
    }

    /**
     * This method does not report to anything
     */
    @Override
    public PaymentReceipt requestPayment(PaymentInvoice invoice, Integer choice) throws UndoException {
        return null;
    }

    /**
     * This method does not report to anything
     */
    @Override
    public Player peekActor() {
        return null;
    }
}
