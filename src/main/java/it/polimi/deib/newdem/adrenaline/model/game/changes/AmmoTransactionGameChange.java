package it.polimi.deib.newdem.adrenaline.model.game.changes;

import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;

public abstract class AmmoTransactionGameChange implements GameChange {

    protected Player player;
    protected AmmoSet ammos;

    public AmmoTransactionGameChange(Player player, AmmoSet ammos){
        //TODO
    }
}
