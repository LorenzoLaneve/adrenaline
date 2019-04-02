package it.polimi.deib.newdem.adrenaline.common.model.map;

import it.polimi.deib.newdem.adrenaline.common.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.common.model.items.WeaponSet;

public class SpawnPointTile extends ConcreteTile {

    // TODO figure out data structure
    @Override
    public boolean hasSpawnPoint() {
        return true;
    }

    @Override
    public DropInstance getDrops() {
        // TODO implement
        return null;
    }

    @Override
    public WeaponSet getWeapons() {
        // TODO implement
        return null;
    }
}
