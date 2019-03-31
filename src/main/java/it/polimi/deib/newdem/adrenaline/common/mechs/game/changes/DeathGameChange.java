package it.polimi.deib.newdem.adrenaline.common.mechs.game.changes;

import it.polimi.deib.newdem.adrenaline.common.mechs.game.Game;
import it.polimi.deib.newdem.adrenaline.common.mechs.game.GameChange;
import it.polimi.deib.newdem.adrenaline.common.mechs.game.Player;

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
