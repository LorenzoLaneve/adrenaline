package it.polimi.deib.newdem.adrenaline.controller.effects.selection;


import it.polimi.deib.newdem.adrenaline.controller.effects.EffectVisitor;

public interface PlayerSelectorFactory {

    PlayerSelector makeSelector(EffectVisitor visitor);

}
