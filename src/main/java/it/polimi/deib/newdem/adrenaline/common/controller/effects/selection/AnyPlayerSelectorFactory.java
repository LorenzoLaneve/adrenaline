package it.polimi.deib.newdem.adrenaline.common.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.common.controller.effects.EffectVisitor;
import it.polimi.deib.newdem.adrenaline.common.model.game.Player;

import java.util.List;

public class AnyPlayerSelectorFactory implements PlayerSelectorFactory {

    public AnyPlayerSelectorFactory(){
        //TODO
    }

    @Override
    public PlayerSelector makeSelector(EffectVisitor visitor, List<Player> excluded) {
        //TODO
        return null;
    }
}
