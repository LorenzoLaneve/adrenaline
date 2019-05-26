package it.polimi.deib.newdem.adrenaline.model.game;

import it.polimi.deib.newdem.adrenaline.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;

public interface PlayerListener {

    void playerTookDamage(int dmgAmount, Player attacker);

    void playerTookMark(int markAmount, Player attacker);

    void playerDidDrawPowerUpCard(PowerUpCard powerUpCard);

    void playerDidDiscardPowerUpCard(PowerUpCard powerUpCard);

    void playerDidGrabDrop(DropInstance dropInstance);

    void playerDidGrabWeapon(WeaponCard weaponCard);

    void playerDidDiscardWeapon(WeaponCard weaponCard);


}
