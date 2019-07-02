package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectContext;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;

public interface AtomEffectContext extends EffectContext {

    void setSelectedWeaponCard(WeaponCard card);

}
