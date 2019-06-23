package it.polimi.deib.newdem.adrenaline.model.game.changes;

import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoColor;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;

public class AmmoLossGameChange implements GameChange {

    private Player player;
    private AmmoSet ammos;


    public AmmoLossGameChange(Player player, AmmoSet ammos) {
        this.ammos = ammos;
        this.player = player;
    }

    @Override
    public void update(Game game) {
        player.getInventory().removeAmmo(AmmoColor.YELLOW, ammos.getYellowAmmos());
        player.getInventory().removeAmmo(AmmoColor.RED, ammos.getRedAmmos());
        player.getInventory().removeAmmo(AmmoColor.BLUE, ammos.getBlueAmmos());
    }

    @Override
    public void revert(Game game) {
        player.getInventory().addAmmo(AmmoColor.YELLOW, ammos.getYellowAmmos());
        player.getInventory().addAmmo(AmmoColor.RED, ammos.getRedAmmos());
        player.getInventory().addAmmo(AmmoColor.BLUE, ammos.getBlueAmmos());
    }
}
