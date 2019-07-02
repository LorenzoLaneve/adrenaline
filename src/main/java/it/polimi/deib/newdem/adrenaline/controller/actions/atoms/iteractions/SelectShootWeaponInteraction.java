package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.items.Weapon;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;

import java.util.List;
import java.util.stream.Collectors;

public class SelectShootWeaponInteraction extends InteractionBase {

    public SelectShootWeaponInteraction(InteractionContext context) {
        super(context);
    }

    @Override
    public void execute() throws UndoException {
        List<Weapon> availableWeapons = context.getActor().getInventory().getLoadedWeapons();

        if (availableWeapons.isEmpty()) throw new UndoException();

        Weapon selectedWeapon = selectWeapon(availableWeapons);

        context.getEffectContext().setSelectedWeaponCard(selectedWeapon.getCard());
        context.pushInteraction(new DischargeInteraction(context, selectedWeapon));
    }

    private Weapon selectWeapon(List<Weapon> availableWeaponCards) throws UndoException {
        List<WeaponCard> selectables = availableWeaponCards.stream().map(Weapon::getCard).collect(Collectors.toList());

        WeaponCard selectedCard = null;

        selectedCard = context.getDataSource().chooseWeaponCard(selectables);

        if(null == selectedCard)  {throw new UndoException(); }

        // get weapon from ID in starting list
        for (Weapon w : availableWeaponCards) {
            if (w.getCard().getCardID() == selectedCard.getCardID()) {
                return w;
            }
        }

        throw new IllegalStateException();
    }

    @Override
    public void revert() {
        // no changes to revert
    }

    @Override
    public boolean requiresInput() {
        return true;
    }
}
