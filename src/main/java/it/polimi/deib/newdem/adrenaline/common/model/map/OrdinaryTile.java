package it.polimi.deib.newdem.adrenaline.common.model.map;

import it.polimi.deib.newdem.adrenaline.common.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.common.model.items.WeaponSet;

public class OrdinaryTile extends ConcreteTile {

    @Override
    public boolean hasSpawnPoint() {
        return false;
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
