package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectVisitor;
import it.polimi.deib.newdem.adrenaline.controller.effects.MetaPlayer;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;

import java.util.List;

public class VisiblePlayerSelectorFactory implements PlayerSelectorFactory {

    private MetaPlayer sourcePlayer;

    public VisiblePlayerSelectorFactory(MetaPlayer sourcePlayer){
        this.sourcePlayer = sourcePlayer;
    }

    @Override
    public PlayerSelector makeSelector(EffectVisitor visitor, List<Player> excluded) {
        return new BlackListFilterPlayerSelector(excluded,
                new VisiblePlayerSelector(visitor.getBoundPlayer(sourcePlayer))
        );
    }
}
