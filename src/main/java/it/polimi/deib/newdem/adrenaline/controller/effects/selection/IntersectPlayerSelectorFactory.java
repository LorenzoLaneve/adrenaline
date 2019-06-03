package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectVisitor;

public class IntersectPlayerSelectorFactory implements PlayerSelectorFactory {

    private PlayerSelectorFactory secondSelectorFactory;
    private PlayerSelectorFactory firstSelectorFactory;

    public IntersectPlayerSelectorFactory(PlayerSelectorFactory firstSelectorFactory, PlayerSelectorFactory secondSelectorFactory) {
        this.firstSelectorFactory = firstSelectorFactory;
        this.secondSelectorFactory = secondSelectorFactory;
    }

    @Override
    public PlayerSelector makeSelector(EffectVisitor visitor) {
        return new IntersectPlayerSelector(firstSelectorFactory.makeSelector(visitor), secondSelectorFactory.makeSelector(visitor));
    }
}
