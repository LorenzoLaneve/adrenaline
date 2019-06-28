package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.model.items.Card;
import it.polimi.deib.newdem.adrenaline.model.items.Weapon;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;

import java.util.List;
import java.util.stream.Collectors;

public class ShootAtom extends AtomContext {

    public ShootAtom(AtomsContainer parent) {
        super(parent);
    }

    @Override
    public void execute() throws ConnectionException {
        List<WeaponCard> availableWeapons;
        availableWeapons = getActor().getInventory().getReadyWeapons().getWeapons();

        WeaponCard selectedWeapon = selectWeapon(availableWeapons);

        boolean effectResolved = false;

        do {
            try {
                selectedWeapon.getEffect().apply(new EffectManager(this), getActor());
                effectResolved = true;
            }
            catch (UndoException e) {
                // do nothing, effectResolve is already false
            }
        }
        while (!effectResolved);
    }

    private WeaponCard selectWeapon(List<WeaponCard> availableWeaponCards) {
        int selectedId = -1;
        List<Integer> availableWeaponsIds = availableWeaponCards.stream()
                .map(WeaponCard::getCardID)
                .collect(Collectors.toList());

        // select weapon card
        do{
            try{
                selectedId = parent.getDataSource().actionDidRequestChoice(
                        availableWeaponsIds
                );
            }
            catch (UndoException e) {
                // not allowed, do not propagate and repeat
            }
        }
        while (-1 == selectedId);

        // get weapon from ID in starting list
        for(WeaponCard w : availableWeaponCards) {
            if(w.getCardID() == selectedId) {
                return w;
            }
        }

        throw new IllegalStateException();
    }
/*
    @Override
    public void applyGameChange(GameChange gameChange) {
        gameChange.update(parent.getGame());
    }

    @Override
    public void revertGameChange(GameChange gameChange) {
        gameChange.revert(parent.getGame());
    }

    @Override
    public Player getActor() {
        return getActor();
    }

    @Override
    public Player getAttacker() {
        // contesto danno ricevuto
        return null;
        // TODO
    }

    @Override
    public Player getVictim() {
        // contesto di danno arrecato
        return null;
        // TODO
    }

    @Override
    public Player choosePlayer(MetaPlayer player, PlayerSelector selector, boolean forceChoice) throws UndoException {
        Player selectedPlayer;

        do {
            selectedPlayer = parent.getDataSource().actionDidRequestPlayer(
                    player, selector
            );
        }
        while ((null == selectedPlayer) && forceChoice);
        return selectedPlayer;
    }

    @Override
    public Tile chooseTile(TileSelector selector, boolean forceChoice) throws UndoException {
        Tile selectedTile;

        do {
            selectedTile = parent.getDataSource().actionDidRequestTile(selector);
        }
        while ((null == selectedTile) && forceChoice);

        return selectedTile;
    }

    @Override
    public Integer chooseFragment(List<Integer> choices) throws UndoException {
        // TODO add to ADS
        return 0;
    }

    @Override
    public PaymentReceipt choosePayment(PaymentInvoice price, int choice) throws UndoException {
        return parent.getDataSource().actionDidRequestPayment(price);
        // FIXME Integer choice?
    }

    @Override
    public void damageDealtTrigger(Player attacker, Player victim) {

    }

    @Override
    public void damageTakenTrigger(Player attacker, Player victim) {

    }
    */
}
