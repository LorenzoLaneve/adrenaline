package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelectorFactory;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelectorFactory;
import it.polimi.deib.newdem.adrenaline.model.game.changes.MovementGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class KnockbackEffect extends ConcreteEffect {

    private MetaPlayer metaTarget;

    private TileSelectorFactory tileSelectorFactory;
    private PlayerSelectorFactory playerSelectorFactory;

    public KnockbackEffect(int id, MetaPlayer metaTarget, PlayerSelectorFactory playerSelectorFactory, TileSelectorFactory tileSelectorFactory) {
        super(id);

        this.metaTarget = metaTarget;

        this.tileSelectorFactory = tileSelectorFactory;
        this.playerSelectorFactory = playerSelectorFactory;
    }

    @Override
    public void apply(EffectVisitor visitor) throws UndoException {
        Player target = visitor.getBoundPlayer(metaTarget, playerSelectorFactory.makeSelector(visitor));

        Tile targetTile = visitor.getTile(tileSelectorFactory.makeSelector(visitor));

        visitor.reportGameChange(new MovementGameChange(target, targetTile));
    }
}
