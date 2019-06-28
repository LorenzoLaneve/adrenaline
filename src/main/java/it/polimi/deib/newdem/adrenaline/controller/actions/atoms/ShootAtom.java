package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.effects.*;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.WeaponCard;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;

import java.util.List;
import java.util.stream.Collectors;

public class ShootAtom extends AtomBase implements EffectContext {

    public ShootAtom(AtomsContainer parent) {
        super(parent);
    }

    @Override
    public void execute() throws ConnectionException {
        List<Effect> availableWeaponEffects;
        availableWeaponEffects = getActor().getInventory().getReadyWeapons().getWeapons().stream()
                .map(WeaponCard::getEffect)
                .collect(Collectors.toList());
        int selectedIndex = parent.getDataSource().actionDidRequestChoiche(availableWeaponEffects);
        Effect selectedEffect = availableWeaponEffects.get(selectedIndex);

        boolean effectResolved = false;
        do {
            try {
                selectedEffect.apply(new EffectManager(this), getActor());
                effectResolved = true;
            }
            catch (UndoException e) {
                // do nothing, effectResolve is already false
            }
        }
        while (!effectResolved);

    }

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
            selectedPlayer = parent.getDataSource().actionDidRequestPlayerBinding(
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
    public PaymentReceipt choosePayment(PaymentInvoice price, Integer choice) throws UndoException {
        return parent.getDataSource().actionDidRequestPayment(price);
        // FIXME Integer choice?
    }

    @Override
    public void damageDealtTrigger(Player attacker, Player victim) {

    }

    @Override
    public void damageTakenTrigger(Player attacker, Player victim) {

    }
}
