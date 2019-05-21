package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.model.game.Player;
import it.polimi.deib.newdem.adrenaline.model.game.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;
import it.polimi.deib.newdem.adrenaline.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.model.map.MapListener;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.MapView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class VirtualMapView implements MapView, MapListener {


    @Override
    public void playerDidMove(Player player, Tile source, Tile destination) {
        movePlayer(player.getColor(), destination.getPosition());
    }

    @Override
    public void playerDidSpawn(Player player, Tile spawnPoint) {
        spawnPlayer(player.getColor(), spawnPoint.getPosition());
    }

    @Override
    public void dropDidSpawn(Tile tile, DropInstance drop) {
        List<DropType> drops = new ArrayList<>();

        AmmoSet ammoSet = drop.getAmmos();

        for (int i = 0; i < ammoSet.getBlueAmmos(); i++) {
            drops.add(DropType.BLUE_AMMO);
        }

        for (int i = 0; i < ammoSet.getRedAmmos(); i++) {
            drops.add(DropType.RED_AMMO);
        }

        for (int i = 0; i < ammoSet.getBlueAmmos(); i++) {
            drops.add(DropType.YELLOW_AMMO);
        }

        if(drop.hasPowerUp()){
            drops.add(DropType.POWER_UP);
        }


        addDrops(tile.getPosition(), drops.get(0), drops.get(1), drops.get(2));
    }

    @Override
    public void playerDidDie(Player player) {
        killPlayer(player.getColor());
    }

    @Override
    public void playerDidLeaveMap(Player player) {
        removePlayer(player.getColor());
    }

    @Override
    public void mapDidSendTileData(List<Tile> tileData) {
        List<TilePosition> tileDataPosition = new ArrayList<>();
        for (Tile tile: tileData){
            tileDataPosition.add(tile.getPosition());
        }
        setTiles(tileDataPosition);
    }

    @Override
    public void mapDidSendSpawnPointData(List<Tile> spawnPointTileData) {
        List<TilePosition> spawnPointTileDataPosition = new ArrayList<>();

        for (Tile tile: spawnPointTileData){
            spawnPointTileDataPosition.add(tile.getPosition());
        }

        setSpawnPoints(spawnPointTileDataPosition);
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
