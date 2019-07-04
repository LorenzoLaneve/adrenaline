package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectContext;
import it.polimi.deib.newdem.adrenaline.controller.effects.PlasmaGunEffect;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;

import java.util.List;

public interface AtomEffectContext extends EffectContext {

    void setSelectedWeaponCard(WeaponCard card);

    void setEnableDamageTriggers(boolean flag);

    void setVictim(Player victim);

    List<Player> getDamagedPlayers();
}
