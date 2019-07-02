package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;

import java.util.List;

public class SelectNewWeaponInteraction extends InteractionBase {

    public SelectNewWeaponInteraction(InteractionContext context) {
        super(context);
    }

    @Override
    public void execute() throws UndoException {
        List<WeaponCard> availableWeaponCards = context.getActor().getTile().inspectWeaponSet().getWeapons();

        if (availableWeaponCards.isEmpty()) {throw new UndoException(); }

        WeaponCard selectedWeaponCard = context.getDataSource().chooseWeaponCard(availableWeaponCards);

        if(null == selectedWeaponCard)  {throw new UndoException(); }

        context.pushInteraction(new NewWeaponPaymentInteraction(context, selectedWeaponCard));
    }

    @Override
    public void revert() {
        // no changes were made, nothing to revert
    }

    @Override
    public boolean requiresInput() {
        return true;
    }
/*
    private Weapon selectWeaponCard(List<WeaponCard> availableWeaponCards) throws UndoException {
         WeaponCard selectedCard = context.getDataSource().chooseWeaponCard(availableWeaponCards);

        // get Weapon object from WeaponCard id ID in starting list
        for (Weapon w : availableWeaponCards) {
            if (w.getCard().getCardID() == selectedCard.getCardID()) {
                return w;
            }
        }
        throw new IllegalStateException();
    }
    */
}
