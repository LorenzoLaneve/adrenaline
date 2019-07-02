package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.game.player.PlayerInventory;
import it.polimi.deib.newdem.adrenaline.model.items.Weapon;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;

public class RequireDiscardInteraction extends InteractionBase {

    private WeaponCard newCard;

    public RequireDiscardInteraction(InteractionContext context, WeaponCard weapon) {
        super(context);
        this.newCard = weapon;
    }

    @Override
    public void execute() throws UndoException {
        if (context.getActor().getInventory().getAllWeaponCards().size() >= PlayerInventory.MAX_WEAPONS) {
            context.pushInteraction(new SelectDiscardWeaponInteraction(context, newCard));
        }
        else {
            // no discard required, append replace w/ null
            context.pushInteraction(new AssignWeaponInteraction(context, newCard, null));
        }
    }

    @Override
    public void revert() {
        // nothing to revert, no changes were made
    }

    @Override
    public boolean requiresInput() {
        return false;
    }
}
