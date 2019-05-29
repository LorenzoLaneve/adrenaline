package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearTileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.changes.MovementGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class MoveEffect extends ConcreteEffect{

    private int minDistance;
    private int maxDistance;

    public MoveEffect(int id, int minDist, int maxDist){
        super(id);

        this.minDistance = minDist;
        this.maxDistance = maxDist;
    }

    @Override
    public void apply(EffectVisitor visitor) throws UndoException {
        Player attacker = visitor.getBoundPlayer(MetaPlayer.ATTACKER);

        Tile destTile = visitor.getTile(new NearTileSelector(attacker.getTile(), minDistance, maxDistance));

        visitor.reportGameChange(new MovementGameChange(attacker, destTile));
    }
}
