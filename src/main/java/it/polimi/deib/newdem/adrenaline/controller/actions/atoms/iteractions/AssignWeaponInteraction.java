package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.OutOfSlotsException;
import it.polimi.deib.newdem.adrenaline.model.items.Weapon;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponImpl;
import it.polimi.deib.newdem.adrenaline.model.map.NotSpawnPointTileException;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import it.polimi.deib.newdem.adrenaline.model.map.WeaponAlreadyPresentException;

public class AssignWeaponInteraction extends InteractionBase {

    private WeaponCard newWeaponCard;
    private Weapon discardedWeapon;

    /**
     * Builds a new AssignWeaponInteraction object.
     *
     * @param context context this interaction belongs to.
     * @param newWeapon new weapon to add. Can't be null.
     * @param discardedWeapon weapon to discard. May be null.
     */
    public AssignWeaponInteraction(InteractionContext context, WeaponCard newWeapon, Weapon discardedWeapon) {
        super(context);
        this.newWeaponCard = newWeapon;
        this.discardedWeapon = discardedWeapon;
    }

    @Override
    public void execute() throws UndoException {
        try {
            Tile spawnpoint = context.getActor().getTile();
            Player player = context.getActor();

            // 1. remove new weapon from spawn
            spawnpoint.grabWeapon(newWeaponCard);

            // 1.a remove selected discard weapon if applicable
            if(null != discardedWeapon) {
                player.getInventory().removeWeapon(discardedWeapon);
            }

            // 2. add new weapon to player's inventory
            player.getInventory().addWeapon(new WeaponImpl(newWeaponCard, player));

            // 3. if a weapon was discarded by player, put it on spawn
            if(null != discardedWeapon) {
                spawnpoint.addWeapon(discardedWeapon.getCard());
            }
        }
        catch (NotSpawnPointTileException | OutOfSlotsException | WeaponAlreadyPresentException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void revert() {
        // don't care because is terminal for action (other than atom) => will never be reverted
    }

    @Override
    public boolean requiresInput() {
        return true;
    }
}
