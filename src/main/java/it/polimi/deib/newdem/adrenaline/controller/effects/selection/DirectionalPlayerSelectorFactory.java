package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectVisitor;
import it.polimi.deib.newdem.adrenaline.controller.effects.MetaPlayer;

public class DirectionalPlayerSelectorFactory implements PlayerSelectorFactory {

    MetaPlayer sourcePlayer;
    boolean ignoreWalls;

    public DirectionalPlayerSelectorFactory(MetaPlayer sourcePlayer, boolean ignoreWalls){
        this.sourcePlayer = sourcePlayer;
        this.ignoreWalls = ignoreWalls;
    }

    @Override
    public PlayerSelector makeSelector(EffectVisitor visitor) {
        return new DirectionalPlayerSelector(visitor.getBoundPlayer(sourcePlayer), ignoreWalls);
    }
}
