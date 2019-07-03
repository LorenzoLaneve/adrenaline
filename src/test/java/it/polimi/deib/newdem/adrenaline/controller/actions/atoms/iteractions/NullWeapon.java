package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.Weapon;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;

public class NullWeapon implements Weapon {

    private boolean isLoaded;

    public NullWeapon() {
        isLoaded = true;
    }

    @Override
    public WeaponCard getCard() {
        return new NullWeaponCard();
    }

    @Override
    public boolean isLoaded() {
        return isLoaded;
    }

    @Override
    public void discharge() {
        isLoaded = false;
    }

    @Override
    public void reload() {
        isLoaded = true;
    }

    @Override
    public Player getOwner() {
        return null;
    }
}
