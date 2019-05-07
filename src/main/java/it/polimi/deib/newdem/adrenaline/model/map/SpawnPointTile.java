package it.polimi.deib.newdem.adrenaline.model.map;

import it.polimi.deib.newdem.adrenaline.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.model.items.OutOfSlotsException;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponSet;


public class SpawnPointTile extends ConcreteTile {

    private WeaponSet weaponSet = new WeaponSet();

    /**Creates a new {@code SpawnPointTile} belonging to room and in position tilePosition.
     *
     * @param tilePosition the position the tile occupies.
     */
    public SpawnPointTile(TilePosition tilePosition){
        super(tilePosition);
    }

    /**Used to reveal if the tile is a Spawn Point Tile
     *
     * @return always true since it's a spawn point tile.
     */
    @Override
    public boolean hasSpawnPoint() {
        return true;
    }

    /**Used to observe the drop without grabbing it.
     *
     * @return always null since it's a Spawn point tile.
     */
    @Override
    public DropInstance inspectDrop() {
        return null;
    }

    /**Used to observe the WeaponSet without grabbing it.
     *
     * @return the spawn point's weapon set.
     */
    @Override
    public WeaponSet inspectWeaponSet() {
        return this.weaponSet.copyWeaponSet();
    }

    /**Adds drop to the tile if it doesn't have one
     *
     * @param drop the drop to add.
     * @throws NotOrdinaryTileException always since it's a spawn point tile.
     */
    @Override
    public void addDrop(DropInstance drop) throws NotOrdinaryTileException{
        throw new NotOrdinaryTileException("It's not possible to add a drop since this is a spawn point tile");
    }

    /**Adds Weapon to the tile.
     *
     * @param weapon the weapon card of the weapon to add.
     * @throws OutOfSlotsException if 3 weapons are already present in the set.
     * @throws WeaponAlreadyPresentException if weapon is already present in the set.
     */
    @Override
    public void addWeapon(WeaponCard weapon) throws OutOfSlotsException, WeaponAlreadyPresentException {
        this.weaponSet.addWeapon(weapon);
    }

    /**Grabs drop from the tile if present ends signal that after the tile lacks one.
     *
     * @return
     * @throws NotOrdinaryTileException always since it's a spawn point tile.
     */
    @Override
    public DropInstance grabDrop() throws NotOrdinaryTileException {
        throw new NotOrdinaryTileException("It's not possible to grab a drop since this is a spawn point tile");
    }

    /**Grabs weapon from the tile's weapon set if present, removing it from the set.
     *
     * @param weaponCard the weapon to grab.
     * @return the weapon to grab.
     */
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

    /**Check if the tile lacks a drop.
     *
     * @return boolean value.
     */
    @Override
    public boolean missingDrop() {
        return true;
    }

}
