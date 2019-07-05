package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;

import java.util.List;

public class MockMapListener implements MapListener {

    /**
     * Simple Mock Object Used for testing purposes.
     */


    @Override
    public void playerDidMove(Player player, Tile source, Tile destination) {

    }

    @Override
    public void playerDidSpawn(Player player, Tile spawnPoint) {

    }

    @Override
    public void dropDidSpawn(Tile tile, DropInstance drop) {

    }

    @Override
    public void dropDidDespawn(Tile tile) {

    }

    @Override
    public void playerDidDie(Player player) {

    }

    @Override
    public void playerDidLeaveMap(Player player) {

    }

    @Override
    public void mapDidRestoreData(MapData data) {

    }

    @Override
    public void weaponDidSpawn(Tile tile, WeaponCard weapon) {

    }

    @Override
    public void weaponDidDespawn(Tile tile, WeaponCard weapon) {

    }

    @Override
    public void playerDidResurrect(Player player) {

    }
}
