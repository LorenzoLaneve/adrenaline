package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectVisitor;
import it.polimi.deib.newdem.adrenaline.controller.effects.MetaPlayer;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.Player;

public class NearTileSelectorFactory implements TileSelectorFactory{

    private MetaPlayer sourcePlayer;
    private int minDistance;
    private int maxDistance;

    public NearTileSelectorFactory(MetaPlayer sourcePlayer, int minDist, int maxDist){
        this.sourcePlayer = sourcePlayer;
        this.minDistance = minDist;
        this.maxDistance = maxDist;
    }

    @Override
    public TileSelector makeSelector(EffectVisitor visitor) {
        try {
            Player p = visitor.getBoundPlayer(sourcePlayer, new AnyPlayerSelector());

            return new NearTileSelector(p.getTile(), minDistance, maxDistance);
        } catch (UndoException x) {
            return null;
            // FIXME what can i do here?
        }
    }
}
