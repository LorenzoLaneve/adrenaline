package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectVisitor;

public class AnyPlayerSelectorFactory implements PlayerSelectorFactory {

    public AnyPlayerSelectorFactory() {
        // nothing to do here
    }

    @Override
    public PlayerSelector makeSelector(EffectVisitor visitor) {
        return new AnyPlayerSelector();
    }
}
