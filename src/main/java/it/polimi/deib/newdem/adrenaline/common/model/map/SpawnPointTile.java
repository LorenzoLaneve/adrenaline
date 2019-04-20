package it.polimi.deib.newdem.adrenaline.common.model.map;

import it.polimi.deib.newdem.adrenaline.common.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.common.model.items.OutOfSlotsException;
import it.polimi.deib.newdem.adrenaline.common.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.common.model.items.WeaponSet;

public class SpawnPointTile extends ConcreteTile {

    private WeaponSet weaponSet = new WeaponSet();

    @Override
    public boolean hasSpawnPoint() {
        return true;
    }

    @Override
    public DropInstance inspectDrop() {
        return null;
    }

    @Override
    public WeaponSet inspectWeaponSet() {
        return this.weaponSet.copyWeaponSet();
    }

    @Override
    public void addDrop(DropInstance drop) throws NotOrdinaryTileException{
        throw new NotOrdinaryTileException("It's not possible to add a drop since this is a spawn point tile");
    }

    @Override
    public void addWeapon(WeaponCard weapon) throws OutOfSlotsException, WeaponAlreadyPresentException {
        this.weaponSet.addWeapon(weapon);
    }

    @Override
    public DropInstance grabDrop() throws NotOrdinaryTileException {
        throw new NotOrdinaryTileException("It's not possible to grab a drop since this is a spawn point tile");
    }

    @Override
    public WeaponCard grabWeapon(WeaponCard weaponCard) {
        if(this.weaponSet.getWeapons().contains(weaponCard)){
            this.weaponSet.removeWeapon(weaponCard);
            return weaponCard;
        }
        else{
            return null;
        }
    }

    @Override
    public boolean missingDrop() {
        return true;
    }
}
