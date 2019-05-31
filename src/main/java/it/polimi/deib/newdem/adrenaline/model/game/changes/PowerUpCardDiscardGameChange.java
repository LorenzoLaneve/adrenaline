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
        // TODO
    }


    @Override
    public void update(Game game) {
        // TODO
    }

    @Override
    public void revert(Game game) {
        // TODO
    }
}
