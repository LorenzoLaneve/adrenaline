package it.polimi.deib.newdem.adrenaline.common.model.map;

import it.polimi.deib.newdem.adrenaline.common.model.items.DropInstance;
import it.polimi.deib.newdem.adrenaline.common.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.common.model.items.WeaponSet;

public class OrdinaryTile extends ConcreteTile {

    private boolean missingDrop = true;
    private DropInstance dropInstance;

    /**Used to reveal if the tile is a Spawn Point Tile
     *
     * @return always false.
     */
    @Override
    public boolean hasSpawnPoint() {
        return false;
    }

    /**Used to observe the drop without grabbing it.
     *
     * @return The drop currently present of the tile, null if no drop is present.
     */
    @Override
    public DropInstance inspectDrop() {

        if(!missingDrop){
            return dropInstance.copyDrop();
        }
        else{
            return null;
        }
    }

    /**Used to observe the WeaponSet without grabbing it.
     *
     * @return empty set.
     */
    @Override
    public WeaponSet inspectWeaponSet() {
        WeaponSet set;
        set = new WeaponSet();
        return set;
    }

    /**adds drop to the tile if it doesn't have one
     *
     * @param drop the drop to add.
     * @throws DropAlreadyPresentException if the tile already has a drop.
     */
    @Override
    public void addDrop(DropInstance drop) throws DropAlreadyPresentException {
        if(missingDrop){
            dropInstance = drop;
            missingDrop = false;
        }
        else{
            throw new DropAlreadyPresentException("Drop already present");
        }

    }

    /**
     *
     * @param weapon
     * @throws NotSpawnPointTileException always since it is not a spawn point tile.
     */
    @Override
    public void addWeapon(WeaponCard weapon) throws NotSpawnPointTileException {
        throw new NotSpawnPointTileException("It is not possible to add Weapons to an Ordinary Tile");
    }

    /**Grabs drop from the tile if present ends signal that after the tile lacks one.
     *
     * @return the drop present in the tile.
     */
    @Override
    public DropInstance grabDrop() {
        if(!missingDrop){
            missingDrop = true;
            return dropInstance.copyDrop();
        }
        else{
            return null;
        }

    }

    /**
     *
     * @param weaponCard
     * @return empty weapon set since this is an ordinary tile.
     */
    @Override
    public WeaponSet grabWeapon(WeaponCard weaponCard) {
        WeaponSet set;
        set = new WeaponSet();
        return set;
    }

    /**Check if the tile lacks a drop.
     *
     * @return boolean value.
     */
    @Override
    public boolean missingDrop() {
        return missingDrop;
    }
}
