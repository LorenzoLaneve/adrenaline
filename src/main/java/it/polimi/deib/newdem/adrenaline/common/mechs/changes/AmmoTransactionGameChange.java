package it.polimi.deib.newdem.adrenaline.common.mechs.changes;

import it.polimi.deib.newdem.adrenaline.common.mechs.game.GameChange;
import it.polimi.deib.newdem.adrenaline.common.mechs.game.Player;
import it.polimi.deib.newdem.adrenaline.common.mechs.items.AmmoSet;

public abstract class AmmoTransactionGameChange implements GameChange {

    protected Player player;
    protected AmmoSet ammos;

    public AmmoTransactionGameChange(Player player, AmmoSet ammos){
        //TODO
    }
}
