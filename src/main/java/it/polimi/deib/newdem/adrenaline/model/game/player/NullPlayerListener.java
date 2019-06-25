package it.polimi.deib.newdem.adrenaline.model.game.player;

import it.polimi.deib.newdem.adrenaline.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;

public final class NullPlayerListener implements PlayerListener {

    @Override
    public void playerDidDrawPowerUpCard(PowerUpCard powerUpCard) {

    }

    @Override
    public void playerDidDiscardPowerUpCard(PowerUpCard powerUpCard) {

    }

    @Override
    public void playerDidGrabDrop(DropInstance dropInstance) {

    }

    @Override
    public void playerDidGrabWeapon(WeaponCard weaponCard) {

    }

    @Override
    public void playerDidDiscardWeapon(WeaponCard weaponCard) {

    }
}
