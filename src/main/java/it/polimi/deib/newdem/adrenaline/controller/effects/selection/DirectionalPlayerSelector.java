package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Direction;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.ArrayList;
import java.util.List;

public class DirectionalPlayerSelector implements PlayerSelector {

    private Player sourcePlayer;
    private Direction direction;
    private boolean ignoreWalls;

    public DirectionalPlayerSelector(Player sourcePlayer, Direction direction, boolean ignoreWalls) {
        this.sourcePlayer = sourcePlayer;
        this.direction = direction;
        this.ignoreWalls = ignoreWalls;
    }

    @Override
    public boolean isSelectable(Map map, Player player) {

        List<Tile> tilesSelectablePlayers = sourcePlayer.getTile().getTiles(direction,ignoreWalls);

        List<Player> selectablePlayers = new ArrayList<>();

        for(Tile tile:tilesSelectablePlayers){
            selectablePlayers.addAll(tile.getPlayers());
        }
        return selectablePlayers.contains(player);
    }
}
