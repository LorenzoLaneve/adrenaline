package it.polimi.deib.newdem.adrenaline.model.game.player;

import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;

public interface PlayerListener {

    void playerDidReceivePowerUpCard(Player player, PowerUpCard powerUpCard);

    void playerDidDiscardPowerUpCard(Player player, PowerUpCard powerUpCard);

    void playerDidReceiveWeaponCard(Player player, WeaponCard weaponCard);

    void playerDidDiscardWeaponCard(Player player, WeaponCard weaponCard);

    void playerDidReceiveAmmos(Player player, AmmoSet ammos);

    void playerDidDiscardAmmos(Player player, AmmoSet ammos);

    void playerDidUnloadWeaponCard(Player player, WeaponCard weaponCard);

    void playerDidReloadWeaponCard(Player player, WeaponCard weaponCard);

}
