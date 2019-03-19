package it.polimi.deib.newdem.adrenaline.common.mechs.actions;

import it.polimi.deib.newdem.adrenaline.common.mechs.Player;
import it.polimi.deib.newdem.adrenaline.common.mechs.items.Weapon;

public class WeaponCallAction extends ActionBaseImpl {

    private Weapon weapon;


    public WeaponCallAction(Player actor, Weapon weapon) {
        super(actor);
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
