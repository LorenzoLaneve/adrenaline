package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.List;

public interface ActionDataSource {

    /**
     * Used when requesting Player.
     * @param metaPlayer the color on the weapon/powerup.
     * @param selector the condition the returned player has to meet.
     * @param forceChoice condition to loop until valid input is received.
     * @return a player that meets the condition of the selector
     * @throws UndoException
     */
    Player requestPlayer(MetaPlayer metaPlayer, PlayerSelector selector, boolean forceChoice) throws UndoException;

    WeaponCard chooseWeaponCard(List<WeaponCard> cards) throws UndoException;

    PowerUpCard choosePowerUpCard(List<PowerUpCard> cards) throws UndoException;

    Tile requestTile(TileSelector selector, boolean forceChoice) throws UndoException;

    /**
     *  Used when requesting which specif effect to used of a weapon.
     * @param cardID weaponID
     * @param fragments list of the fragments ids
     * @param forceChoice boolean used to compel to choose, effectively looping until a valid input is received.
     * @return int corresponding to the fragment chosen
     * @throws UndoException to allow Undo
     */
    Integer requestFragment(int cardID, List<Integer> fragments, boolean forceChoice) throws UndoException;

    /**
     * Used when requesting payment.
     * @param invoice to be payed.
     * @param choice if the payment is for a weapon it's the fragment to be payed, defaults to -1 otherwise.
     * @return the Payment Receipt
     * @throws UndoException
     */
    PaymentReceipt requestPayment(PaymentInvoice invoice, Integer choice) throws UndoException;

}
