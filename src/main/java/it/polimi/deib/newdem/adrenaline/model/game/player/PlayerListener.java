package it.polimi.deib.newdem.adrenaline.model.game.player;

import it.polimi.deib.newdem.adrenaline.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;

public interface PlayerListener {

    void playerDidTakeDamage(int dmgAmount, int mrkAmount, Player attacker);

    void playerDidDrawPowerUpCard(PowerUpCard powerUpCard);

    void playerDidDiscardPowerUpCard(PowerUpCard powerUpCard);

    void playerDidGrabDrop(DropInstance dropInstance);

    void playerDidGrabWeapon(WeaponCard weaponCard);

    void playerDidDiscardWeapon(WeaponCard weaponCard);


}
