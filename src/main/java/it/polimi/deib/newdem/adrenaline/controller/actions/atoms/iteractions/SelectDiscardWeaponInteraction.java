package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.items.Weapon;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;

import java.util.List;

/**
 * Interaction encapsulating the choice of which weapon to discard within a GRAB atom
 */
public class SelectDiscardWeaponInteraction extends InteractionBase {

    private WeaponCard newWeaponCard;

    /**
     * Builds a new {@code SelectDiscardWeaponInteraction } bound to the given {@code InteractionContext}
     * @param context this interaction's environment
     * @param newWeapon weapon to pass on to this {@code Interaction}'s children
     */
    public SelectDiscardWeaponInteraction(InteractionContext context, WeaponCard newWeapon) {
        super(context);
        this.newWeaponCard = newWeapon;
    }

    @Override
    public void execute() throws UndoException {
        List<WeaponCard> discardableCards = context.getActor().getInventory().getAllWeaponCards();

        WeaponCard discardedCard = context.getDataSource().chooseWeaponCard(discardableCards);
        if (discardedCard != null) {
            Weapon discardedWeapon = getWeaponFromCard(discardedCard, context.getActor().getInventory().getAllWeapons());
            context.pushInteraction(new AssignWeaponInteraction(context, newWeaponCard, discardedWeapon));
        } else {
            throw new UndoException();
        }
    }

    @Override
    public void revert() {
        // no changes
    }

    @Override
    public boolean requiresInput() {
        return true;
    }

    private Weapon getWeaponFromCard(WeaponCard card, List<Weapon> candidates) {
        for(Weapon c : candidates) {
            if(c.getCard().getCardID() == card.getCardID()) {
                return c;
            }
        }
        throw new IllegalArgumentException();
    }
}
