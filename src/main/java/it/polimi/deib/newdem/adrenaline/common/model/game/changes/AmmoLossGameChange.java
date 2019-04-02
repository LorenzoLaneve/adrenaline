package it.polimi.deib.newdem.adrenaline.common.model.game.changes;

import it.polimi.deib.newdem.adrenaline.common.model.game.Game;
import it.polimi.deib.newdem.adrenaline.common.model.game.Player;
import it.polimi.deib.newdem.adrenaline.common.model.items.AmmoSet;

public class AmmoLossGameChange extends AmmoTransactionGameChange {

    public AmmoLossGameChange(Player player, AmmoSet ammos){
        super(player, ammos);
        //TODO
    }

    @Override
    public void update(Game game) {
        //TODO
    }

    @Override
    public void revert(Game game) {
        //TODO
    }
}
