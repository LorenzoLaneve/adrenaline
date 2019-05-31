package it.polimi.deib.newdem.adrenaline.model.game.changes;

import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;

public class AmmoLossGameChange implements GameChange {

    private Player player;
    private AmmoSet ammos;


    public AmmoLossGameChange(Player player, AmmoSet ammos) {
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
