package it.polimi.deib.newdem.adrenaline.common.mechs.actions;

import it.polimi.deib.newdem.adrenaline.common.mechs.game.Player;
import it.polimi.deib.newdem.adrenaline.common.mechs.items.Weapon;

public class WeaponCallAction extends ActionBaseImpl {

    private Weapon weapon;


    public WeaponCallAction(Player actor, Weapon weapon) {
        super(actor);
        this.weapon = weapon;
        // TODO implement
    }

    public Weapon getWeapon() {
        return weapon;
        // TODO implement

    }
}
