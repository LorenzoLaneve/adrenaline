package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectContext;
import it.polimi.deib.newdem.adrenaline.controller.effects.PlasmaGunEffect;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;

import java.util.List;

/**
 * Atoms implementing this iterface may be used as an {@code EffectContext} aware
 * of thier caller and all other information necessary to an effect's resolution
 */
public interface AtomEffectContext extends EffectContext {

    /**
     * Inform this context of the weapon containing the effect.
     *
     * @param card the selected weapon.
     */
    void setSelectedWeaponCard(WeaponCard card);

    /**
     * Set whether the triggers on damage dealt and taken should fire during
     * the resolution of this effect
     *
     * @param flag should the triggers be fired
     */
    void setEnableDamageTriggers(boolean flag);

    /**
     * Set the victim of the given effect.
     *
     * @param victim the victim
     */
    void setVictim(Player victim);

    /**
     * Return all the players that have been damaged during this effect's resolution
     *
     * @return list of damaged players
     */
    List<Player> getDamagedPlayers();
}
