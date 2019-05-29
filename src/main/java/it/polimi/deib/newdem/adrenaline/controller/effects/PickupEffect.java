package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearTileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DropPickupGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.changes.MovementGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class PickupEffect extends ConcreteEffect {

    private int minDistance;
    private int maxDistance;

    public PickupEffect(int id, int minDist, int maxDist) {
        super(id);

        this.minDistance = minDist;
        this.maxDistance = maxDist;
    }

    @Override
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        Tile destTile = visitor.getTile(new NearTileSelector(attacker.getTile(), minDistance, maxDistance));

        if (destTile != attacker.getTile()) {
            visitor.reportGameChange(new MovementGameChange(attacker, destTile));
        }

        if (destTile.hasSpawnPoint()) {
            // TODO ask for weapon
        } else {
            visitor.reportGameChange(new DropPickupGameChange(attacker, destTile));
        }

    }
}
