package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.model.game.Player;
import it.polimi.deib.newdem.adrenaline.model.game.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.map.MapListener;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.MapView;

import java.util.List;

public class VirtualMapView implements MapView, MapListener {


    @Override
    public void playerDidMove(Player player, Tile source, Tile destination) {
        // TODO
    }

    @Override
    public void setTiles(List<TilePosition> tileData) {
        // TODO
    }

    @Override
    public void setSpawnPoints(List<TilePosition> tileData) {
        // TODO
    }

    @Override
    public void addDrops(TilePosition tile, DropType drop1, DropType drop2, DropType drop3) {
        // TODO
    }

    @Override
    public void movePlayer(PlayerColor player, TilePosition destTile) {
        // TODO
    }

    @Override
    public void spawnPlayer(PlayerColor player, TilePosition tile) {
        // TODO
    }

    @Override
    public void killPlayer(PlayerColor player) {
        // TODO
    }

    @Override
    public void removePlayer(PlayerColor player) {
        // TODO
    }

}
