package it.polimi.deib.newdem.adrenaline.controller.effects.selection;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Direction;
import it.polimi.deib.newdem.adrenaline.model.map.Map;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import it.polimi.deib.newdem.adrenaline.view.inet.events.PlayerReconnectEvent;

import java.util.ArrayList;
import java.util.List;

public class DirectionalPlayerSelector implements PlayerSelector {

    private Player sourcePlayer;
    private Direction direction;
    private boolean ignoreWalls;

    public DirectionalPlayerSelector(Player sourcePlayer, boolean ignoreWalls) {
        this(sourcePlayer, null, ignoreWalls);
    }

    public DirectionalPlayerSelector(Player sourcePlayer, Direction direction, boolean ignoreWalls) {
        this.sourcePlayer = sourcePlayer;
        this.direction = direction;
        this.ignoreWalls = ignoreWalls;
    }

    @Override
    public boolean isSelectable(Map map, Player player) {

        List<Tile> tilesSelectablePlayers = new ArrayList<>();

        if (direction != null) {
            tilesSelectablePlayers.addAll(sourcePlayer.getTile().getTiles(direction,ignoreWalls));
        } else {
            tilesSelectablePlayers.addAll(sourcePlayer.getTile().getTiles(Direction.NORTH,ignoreWalls));
            tilesSelectablePlayers.addAll(sourcePlayer.getTile().getTiles(Direction.EAST,ignoreWalls));
            tilesSelectablePlayers.addAll(sourcePlayer.getTile().getTiles(Direction.SOUTH,ignoreWalls));
            tilesSelectablePlayers.addAll(sourcePlayer.getTile().getTiles(Direction.WEST,ignoreWalls));
        }


        List<Player> selectablePlayers = new ArrayList<>();

        for(Tile tile:tilesSelectablePlayers){
            selectablePlayers.addAll(tile.getPlayers());
        }
        return selectablePlayers.contains(player);
    }
}
