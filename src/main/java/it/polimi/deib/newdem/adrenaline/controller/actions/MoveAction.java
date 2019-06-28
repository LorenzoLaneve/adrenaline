package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearTileSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.changes.MovementGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class MoveAction extends ActionBasePlain {

    private int minDist;
    private int maxDist;

    public MoveAction(Player actor,ActionDataSource actionDataSource,
                      int minDist,
                      int maxDist) {
        super(actor, actionDataSource, actor.getGame());
        this.minDist = minDist;
        this.maxDist = maxDist;
    }

    @Override
    public void start() {
        // do the thing of moving
        // basically implement MoveEffect
        // but without effect
        TileSelector selector = new NearTileSelector(actor, minDist, maxDist);
        Tile target = listener.actionDidRequestTile(selector);
        GameChange gc = new MovementGameChange(actor, target);
        gc.update(game);
    }


}
