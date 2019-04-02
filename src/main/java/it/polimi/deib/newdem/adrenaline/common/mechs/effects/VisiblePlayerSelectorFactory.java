package it.polimi.deib.newdem.adrenaline.common.mechs.effects;

import it.polimi.deib.newdem.adrenaline.common.mechs.game.Player;

import java.util.List;

public class VisiblePlayerSelectorFactory implements PlayerSelectorFactory {

    MetaPlayer sourcePlayer;

    public VisiblePlayerSelectorFactory(MetaPlayer sourcePlayer){
        //TODO

    }

    @Override
    public PlayerSelector makeSelector(EffectVisitor visitor, List<Player> excluded) {
        //TODO

        return null;
    }
}
