package it.polimi.deib.newdem.adrenaline.model.game.changes;

import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;

import java.util.List;

public class PowerUpCardDiscardGameChange implements GameChange {

    private Player player;

    private List<PowerUpCard> discardedPowerUps;


    public PowerUpCardDiscardGameChange(Player player, List<PowerUpCard> powerUps) {
        this.player = player;
        this.discardedPowerUps = powerUps;
    }


    @Override
    public void update(Game game) {
        player.getInventory().removePowerUp(discardedPowerUps);
    }

    @Override
    public void revert(Game game) {
        // TODO
    }
}
