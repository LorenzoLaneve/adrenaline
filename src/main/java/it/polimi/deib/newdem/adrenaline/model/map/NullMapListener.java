package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;


public class NullMapListener implements MapListener {
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
    public void playerDidDie(Player player) {

    }

    @Override
    public void playerDidResurrect(Player player) {

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
    public void playerDidGrabDrop(Player player, DropInstance drop, Tile tile) {

    }
}
