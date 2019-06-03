package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectVisitor;

public class UnionPlayerSelectorFactory implements PlayerSelectorFactory {

    private PlayerSelectorFactory secondSelectorFactory;
    private PlayerSelectorFactory firstSelectorFactory;

    public UnionPlayerSelectorFactory(PlayerSelectorFactory firstSelectorFactory, PlayerSelectorFactory secondSelectorFactory) {
        this.firstSelectorFactory = firstSelectorFactory;
        this.secondSelectorFactory = secondSelectorFactory;
    }

    @Override
    public PlayerSelector makeSelector(EffectVisitor visitor) {
        return new UnionPlayerSelector(firstSelectorFactory.makeSelector(visitor), secondSelectorFactory.makeSelector(visitor));
    }
}
