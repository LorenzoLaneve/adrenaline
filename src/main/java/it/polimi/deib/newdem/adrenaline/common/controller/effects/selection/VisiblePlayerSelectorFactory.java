package it.polimi.deib.newdem.adrenaline.common.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.common.controller.effects.EffectVisitor;
import it.polimi.deib.newdem.adrenaline.common.controller.effects.MetaPlayer;
import it.polimi.deib.newdem.adrenaline.common.model.game.Player;

import java.util.List;

public class VisiblePlayerSelectorFactory implements PlayerSelectorFactory {

    private MetaPlayer sourcePlayer;

    public VisiblePlayerSelectorFactory(MetaPlayer sourcePlayer){
        //TODO

    }

    @Override
    public PlayerSelector makeSelector(EffectVisitor visitor, List<Player> excluded) {
        //TODO

        return null;
    }
}
