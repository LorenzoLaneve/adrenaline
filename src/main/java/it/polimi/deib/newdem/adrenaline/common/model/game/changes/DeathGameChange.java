package it.polimi.deib.newdem.adrenaline.common.model.game.changes;

import it.polimi.deib.newdem.adrenaline.common.model.game.Game;
import it.polimi.deib.newdem.adrenaline.common.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.common.model.game.Player;

public class DeathGameChange implements GameChange {

    private Player killer;
    private Player killed;

    public DeathGameChange(Player killer, Player killed){
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
