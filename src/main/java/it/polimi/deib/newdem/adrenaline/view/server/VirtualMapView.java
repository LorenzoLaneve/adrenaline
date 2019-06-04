package it.polimi.deib.newdem.adrenaline.view.server;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerColor;
import it.polimi.deib.newdem.adrenaline.model.items.AmmoSet;
import it.polimi.deib.newdem.adrenaline.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.map.MapListener;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import it.polimi.deib.newdem.adrenaline.model.map.TilePosition;
import it.polimi.deib.newdem.adrenaline.view.MapView;
import it.polimi.deib.newdem.adrenaline.view.inet.events.*;

import java.util.ArrayList;
import java.util.List;

public class VirtualMapView implements MapView, MapListener {

    private VirtualGameView gameView;


    public VirtualMapView(VirtualGameView gameView) {
        this.gameView = gameView;
    }

    public void addMapEventListener(MapViewEventListener listener) {
        // TODO
    }

    @Override
    public void playerDidResurrect(Player player) {
        //TODO implement
    }

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
    public void weaponDidSpawn(Tile tile, WeaponCard weapon) {
        addWeapon(tile.getPosition(), weapon.getCardID());
    }

    @Override
    public void playerDidGrabDrop(Player player, DropInstance drop, Tile tile) {
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


        acquireDrop(tile.getPosition(), player.getColor(), drops.get(0), drops.get(1), drops.get(2));
    }


    /// MapView methods

    @Override
    public void setTiles(List<TilePosition> tileData) {
        gameView.sendEvent(new MapTileDataEvent(new ArrayList<>(tileData)));
    }

    @Override
    public void setSpawnPoints(List<TilePosition> tileData) {
        gameView.sendEvent(new MapSpawnPointDataEvent(new ArrayList<>(tileData)));
    }

    @Override
    public void addDrops(TilePosition tile, DropType drop1, DropType drop2, DropType drop3) {
        gameView.sendEvent(new SpawnDropEvent(drop1, drop2, drop3, tile));
    }

    @Override
    public void movePlayer(PlayerColor player, TilePosition destTile) {
        gameView.sendEvent(new MovePlayerEvent(player, destTile));
    }

    @Override
    public void spawnPlayer(PlayerColor player, TilePosition tile) {
        gameView.sendEvent(new SpawnPlayerEvent(player, tile));
    }

    @Override
    public void killPlayer(PlayerColor player) {
        gameView.sendEvent(new DeathPlayerEvent(player));
    }

    @Override
    public void removePlayer(PlayerColor player) {
        gameView.sendEvent(new LeaveMapPlayerEvent(player));
    }

    @Override
    public void addWeapon(TilePosition tilePosition, int cardId) {
        gameView.sendEvent(new SpawnWeaponEvent(tilePosition, cardId));
    }

    @Override
    public void acquireDrop(TilePosition tile, PlayerColor player, DropType drop1, DropType drop2, DropType drop3) {
        gameView.sendEvent(new AcquireDropEvent(drop1, drop2, drop3, tile, player));
    }

}
