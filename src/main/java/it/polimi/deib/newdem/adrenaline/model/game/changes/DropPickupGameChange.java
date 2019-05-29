package it.polimi.deib.newdem.adrenaline.model.game.changes;

import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class DropPickupGameChange implements GameChange {

    private Player player;

    private Tile dropTile;

    public DropPickupGameChange(Player player, Tile dropTile) {
        this.player = player;
        this.dropTile = dropTile;
    }

    @Override
    public void update(Game game) {
        // TODO
    }

    @Override
    public void revert(Game game) {
        // TODO
    }

}
