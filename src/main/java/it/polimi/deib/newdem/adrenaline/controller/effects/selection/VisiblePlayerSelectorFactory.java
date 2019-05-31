package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectVisitor;
import it.polimi.deib.newdem.adrenaline.controller.effects.MetaPlayer;

public class VisiblePlayerSelectorFactory implements PlayerSelectorFactory {

    private MetaPlayer sourcePlayer;

    public VisiblePlayerSelectorFactory(MetaPlayer sourcePlayer){
        this.sourcePlayer = sourcePlayer;
    }

    @Override
    public PlayerSelector makeSelector(EffectVisitor visitor) {
        return new VisiblePlayerSelector(visitor.getBoundPlayer(sourcePlayer));
    }
}
