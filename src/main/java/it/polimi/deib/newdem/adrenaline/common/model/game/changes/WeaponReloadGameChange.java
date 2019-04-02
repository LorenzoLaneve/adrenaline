package it.polimi.deib.newdem.adrenaline.common.model.game.changes;

import it.polimi.deib.newdem.adrenaline.common.model.game.Game;
import it.polimi.deib.newdem.adrenaline.common.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.common.model.game.Player;
import it.polimi.deib.newdem.adrenaline.common.model.items.Weapon;

public class WeaponReloadGameChange implements GameChange {

    private Player player;
    private Weapon weapon;

    public WeaponReloadGameChange(Player player, Weapon weapon){
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
