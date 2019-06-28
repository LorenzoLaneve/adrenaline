package it.polimi.deib.newdem.adrenaline.controller.actions;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearTileSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class PickupAction extends ActionBasePlain {

    private int minDist;
    private int maxDist;

    public PickupAction(Player actor, ActionDataSource actionDataSource, int minDist, int maxDist) {
        super(actor, actionDataSource, actor.getGame());
        this.minDist = minDist;
        this.maxDist = maxDist;
        // TODO implement
    }

    @Override
    public void start() {
        TileSelector selector = new NearTileSelector(actor, minDist, maxDist);
        // may pickup weapon OR drop
        // first, select tile (drop or spawn)
        Tile target = listener.actionDidRequestTile(selector);
        if(target.hasSpawnPoint()) {
            // is spawnpoint -> weapons
            // missing listener endponit
            // listener.actionDidRequestChoiche();
        }
    }

}
