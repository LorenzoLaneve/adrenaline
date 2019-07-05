package it.polimi.deib.newdem.adrenaline.model.game.turn;

import it.polimi.deib.newdem.adrenaline.controller.actions.ActionType;
import it.polimi.deib.newdem.adrenaline.controller.effects.MetaPlayer;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentInvoice;
import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentReceiptData;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.List;

/**
 * Receives and propagates notifications about events that happen during a turn
 */
public interface TurnListener {

    /**
     * Notifies that a new turn has begun
     * @param actor whose turn begun
     */
    void turnDidStart(Player actor);

    /**
     * Nortifies that the current turn is over
     *
     * @param actor whose turn is over
     */
    void turnWillEnd(Player actor);

    /**
     * Used when requesting Action.
     * @param actionTypeList list of actions, usually taken from the actionboard.
     * @return the actioType chosen
     * @throws UndoException
     */
    ActionType turnDidRequestAction(List<ActionType> actionTypeList) throws UndoException;

    /**
     * Uesd when requesting Weapon.
     * @param availableCards list of weaponcards, usually as part of a weaponSet either in player o spawnPointTile
     * @return the weaponCard chosen
     * @throws UndoException
     */
    WeaponCard actionDidRequestWeaponCard(List<WeaponCard> availableCards) throws UndoException;
    /**
     * Uesd when requesting PowerUp.
     * @param availableCards list of PowerUp ids, usually as part of a the player inventory
     * @return the weaponCard chosen
     * @throws UndoException
     */
    PowerUpCard actionDidRequestPowerUpCard(List<PowerUpCard> availableCards) throws UndoException;

    /**
     * Used when requesting Player.
     * @param metaPlayer the color on the weapon/powerup.
     * @param legalPlayers the player available to select.
     * @param forceChoice condition to loop until valid input is received.
     * @return a player in legalPlayers
     * @throws UndoException
     */
    Player actionDidRequestPlayer(MetaPlayer metaPlayer, List<Player> legalPlayers, boolean forceChoice) throws UndoException;

    /**
     * Used when requesting Tile.
     * @param legalTiles the tiles available to select.
     * @param forceChoice condition to loop until valid input is received.
     * @return a tile in legalTiles
     * @throws UndoException
     */
    Tile actionDidRequestTile(List<Tile> legalTiles, boolean forceChoice) throws UndoException;

    /**
     * Used when requesting which specific effect to be used of a weapon.
     * @param cardID weaponID
     * @param choices list of the fragments ids
     * @param forceChoice boolean used to compel to choose, effectively looping until a valid input is received.
     * @return int corresponding to the fragment chosen
     * @throws UndoException to allow Undo
     */
    Integer actionDidRequestCardFragment(Integer cardID, List<Integer> choices, boolean forceChoice) throws UndoException;

    /**
     * Used when requesting payment.
     * @param invoice to be payed.
     * @param playerAmmos ammoSet of the paying player.
     * @param powerUps ids of the paying player's powerups
     * @param fragmentToPay if the payment is for a weapon it's the fragment to be payed, defaults to -1 otherwise.
     * @return
     * @throws UndoException
     */
    PaymentReceiptData actionDidRequestPayment(PaymentInvoice invoice, AmmoSet playerAmmos, List<Integer> powerUps, int fragmentToPay) throws UndoException;

}
