package it.polimi.deib.newdem.adrenaline.model.game.changes;

import it.polimi.deib.newdem.adrenaline.model.game.Game;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class MovementGameChange implements GameChange {

    private Player player;

    private Tile destinationTile;

    public MovementGameChange(Player player, Tile destTile) {
        this.player = player;
        this.destinationTile = destTile;
    }

    @Override
    public void update(Game game) {
        player.getTile().getMap().movePlayer(player,destinationTile);
    }

    @Override
    public void revert(Game game) {
        //TODO
    }
}
