package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectVisitor;
import it.polimi.deib.newdem.adrenaline.controller.effects.MetaPlayer;

public class NearPlayerSelectorFactory implements PlayerSelectorFactory {

    private MetaPlayer sourcePlayer;
    private int minDistance;
    private int maxDistance;

    public NearPlayerSelectorFactory(MetaPlayer sourcePlayer, int minDistance, int maxDistance){
        this.sourcePlayer = sourcePlayer;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
    }

    @Override
    public PlayerSelector makeSelector(EffectVisitor visitor) {
        return new NearPlayerSelector(visitor.getBoundPlayer(sourcePlayer), minDistance, maxDistance);
    }
}
