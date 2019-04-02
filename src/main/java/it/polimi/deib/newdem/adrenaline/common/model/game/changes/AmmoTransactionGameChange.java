package it.polimi.deib.newdem.adrenaline.common.model.game.changes;

import it.polimi.deib.newdem.adrenaline.common.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.common.model.game.Player;
import it.polimi.deib.newdem.adrenaline.common.model.items.AmmoSet;

public abstract class AmmoTransactionGameChange implements GameChange {

    protected Player player;
    protected AmmoSet ammos;

    public AmmoTransactionGameChange(Player player, AmmoSet ammos){
        //TODO
    }
}
