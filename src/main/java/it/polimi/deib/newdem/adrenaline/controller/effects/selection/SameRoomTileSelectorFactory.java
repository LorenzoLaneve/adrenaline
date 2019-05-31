package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.controller.effects.EffectVisitor;
import it.polimi.deib.newdem.adrenaline.controller.effects.MetaPlayer;

public class SameRoomTileSelectorFactory implements TileSelectorFactory {

    private MetaPlayer player;

    public SameRoomTileSelectorFactory(MetaPlayer player) {
        this.player = player;
    }

    @Override
    public TileSelector makeSelector(EffectVisitor visitor) {
        return new SameRoomTileSelector(visitor.getBoundPlayer(player));
    }
}
