package it.polimi.deib.newdem.adrenaline.model.game.player;

import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
/**
 * A null object to allow testing and default values.
 *
 * All of this methods do not report to anything.
 */
public final class NullPlayerListener implements PlayerListener {

    /**
     * This method does not report to anything
     */
    @Override
    public void playerDidInit(PlayerData data) {
        // nothing to do here.
    }

    /**
     * This method does not report to anything
     */
    @Override
    public void playerDidReceivePowerUpCard(Player player, PowerUpCard powerUpCard) {
        // nothing to do here.
    }

    /**
     * This method does not report to anything
     */
    @Override
    public void playerDidDiscardPowerUpCard(Player player, PowerUpCard powerUpCard) {
        // nothing to do here.
    }

    /**
     * This method does not report to anything
     */
    @Override
    public void playerDidReceiveWeaponCard(Player player, WeaponCard weaponCard) {
        // nothing to do here.
    }

    /**
     * This method does not report to anything
     */
    @Override
    public void playerDidDiscardWeaponCard(Player player, WeaponCard weaponCard) {
        // nothing to do here.
    }

    /**
     * This method does not report to anything
     */
    @Override
    public void playerDidUpdateScore(Player player, int newScore) {
        // nothing to do here.
    }

    /**
     * This method does not report to anything
     */
    @Override
    public void playerDidReceiveAmmos(Player player, AmmoSet ammos) {
        // nothing to do here.
    }

    /**
     * This method does not report to anything
     */
    @Override
    public void playerDidDiscardAmmos(Player player, AmmoSet ammos) {
        // nothing to do here.
    }

    /**
     * This method does not report to anything
     */
    @Override
    public void playerDidUnloadWeaponCard(Player player, WeaponCard weaponCard) {
        // nothing to do here.
    }

    /**
     * This method does not report to anything
     */
    @Override
    public void playerDidReloadWeaponCard(Player player, WeaponCard weaponCard) {
        // nothing to do here.
    }
}
