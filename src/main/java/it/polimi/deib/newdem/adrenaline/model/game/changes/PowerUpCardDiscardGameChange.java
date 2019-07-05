package it.polimi.deib.newdem.adrenaline.model.game.changes;

import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.OutOfSlotsException;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;

import java.util.List;

/**
 * GameChange object that removes power up cards from the given player's inventory.
 */
public class PowerUpCardDiscardGameChange implements GameChange {

    private Player player;

    private List<PowerUpCard> discardedPowerUps;

    /**
     * Builds a new {@code PowerUpCardDiscardGameChange} representing the discard of the given {@code PowerUpCard}s
     * @param player discarder
     * @param powerUps discarded powerups
     */
    public PowerUpCardDiscardGameChange(Player player, List<PowerUpCard> powerUps) {
        this.player = player;
        this.discardedPowerUps = powerUps;
    }


    @Override
    public void update(Game game) {
        player.getInventory().removePowerUps(discardedPowerUps);
    }

    @Override
    public void revert(Game game) {

        try {
            for (PowerUpCard card : discardedPowerUps) {
                player.getInventory().addPowerUp(card);
            }
        } catch (OutOfSlotsException e) {
            throw new IllegalStateException();
        }

    }
}
