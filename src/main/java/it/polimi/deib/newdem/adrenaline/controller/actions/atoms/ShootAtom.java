package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions.EntryPointType;
import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.model.items.Weapon;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;

import java.util.List;
import java.util.stream.Collectors;

public class ShootAtom extends AtomContext {
    // TODO     IMPLEMENT ENTRYPOINT AND EVERYTHING ELSE
    // FIXME
    public ShootAtom(AtomsContainer parent) {
        super(parent, EntryPointType.SHOOT);
    }

    /*
    @Override
    public void executeFromStart() throws UndoException {

        List<Weapon> availableWeapons = getActor().getInventory().getLoadedWeapons();

        if (availableWeapons.isEmpty()) throw new UndoException();

        Weapon selectedWeapon = selectWeapon(availableWeapons);
        this.usedWeapon = selectedWeapon.getCard();

        selectedWeapon.discharge();

        applyEffect(selectedWeapon.getCard().getEffect());

    }

    private Weapon selectWeapon(List<Weapon> availableWeaponCards) {
        List<WeaponCard> selectables = availableWeaponCards.stream().map(Weapon::getCard).collect(Collectors.toList());

        WeaponCard selectedCard = null;
        do {
            try {
                selectedCard = parent.getDataSource().chooseWeaponCard(selectables);
            } catch (UndoException e) {
                // not allowed, do not propagate and repeat
            }
        } while (selectedCard == null);

        // get weapon from ID in starting list
        for(Weapon w : availableWeaponCards) {
            if(w.getCard().getCardID() == selectedCard.getCardID()) {
                return w;
            }
        }

        throw new IllegalStateException();
    }

    private void applyEffect(Effect effect) {
        boolean effectResolved = false;
        do {
            try {
                effect.apply(new EffectManager(this), getActor());
                effectResolved = true;
            }
            catch (UndoException e) {
                // do nothing, effectResolve is already false
            }
        }
        while (!effectResolved);
    }
    */
}
