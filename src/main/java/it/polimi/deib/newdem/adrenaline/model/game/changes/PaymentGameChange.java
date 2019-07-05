package it.polimi.deib.newdem.adrenaline.model.game.changes;

import it.polimi.deib.newdem.adrenaline.controller.effects.PaymentReceipt;
import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;
import it.polimi.deib.newdem.adrenaline.model.items.OutOfSlotsException;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;

/**
 * GameChange object that performs a payment, removing the resources specified in the given PaymentReceipt object
 * from the given player's inventory.
 */
public class PaymentGameChange implements GameChange {

    private PaymentReceipt receipt;
    private Player player;

    /**
     * Builds a new {@code GameChange} to detract a {@code PaymentReceipt} from a {@code Player}'s {@code Inventory}
     * @param player buyer
     * @param receipt cost
     */
    public PaymentGameChange(Player player, PaymentReceipt receipt) {
        this.player = player;
        this.receipt = receipt;
    }

    @Override
    public void update(Game game) {

        player.getInventory().removeAmmoSet(new AmmoSet(receipt.getPayedRedAmmos(), receipt.getPayedYellowAmmos(), receipt.getPayedBlueAmmos()));

        if(receipt.getPayedPowerUpCards()!= null){
            player.getInventory().removePowerUps(receipt.getPayedPowerUpCards());
        }

    }

    @Override
    public void revert(Game game) {

        player.getInventory().addAmmoSet(new AmmoSet(receipt.getPayedRedAmmos(), receipt.getPayedYellowAmmos(), receipt.getPayedBlueAmmos()));

        if(receipt.getPayedPowerUpCards()!= null){

            try {
                for (PowerUpCard card : receipt.getPayedPowerUpCards()) {
                    player.getInventory().addPowerUp(card);
                }
            } catch (OutOfSlotsException e) {
                throw new IllegalStateException();
            }

        }

    }
}
