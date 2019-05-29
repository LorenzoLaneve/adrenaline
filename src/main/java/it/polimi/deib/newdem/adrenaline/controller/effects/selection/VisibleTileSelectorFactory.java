package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectVisitor;
import it.polimi.deib.newdem.adrenaline.controller.effects.MetaPlayer;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;


public class VisibleTileSelectorFactory implements TileSelectorFactory {
    private MetaPlayer sourcePlayer;

    public VisibleTileSelectorFactory(MetaPlayer sourcePlayer){
        this.sourcePlayer = sourcePlayer;
    }

    @Override
    public TileSelector makeSelector(EffectVisitor visitor) {
        Player p = visitor.getBoundPlayer(sourcePlayer);

        return new VisibleTileSelector(p.getTile());
    }
}
