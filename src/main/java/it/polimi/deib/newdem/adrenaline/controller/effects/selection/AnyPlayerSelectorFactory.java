package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectVisitor;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.List;

public class AnyPlayerSelectorFactory implements PlayerSelectorFactory {

    public AnyPlayerSelectorFactory() {
        // nothing to do here
    }

    @Override
    public PlayerSelector makeSelector(EffectVisitor visitor, List<Player> excluded) {
        return new AnyPlayerSelector();
    }
}
