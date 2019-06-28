package it.polimi.deib.newdem.adrenaline.controller.effects;

import it.polimi.deib.newdem.adrenaline.controller.effects.selection.BlackListFilterPlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.changes.DamageGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.changes.MovementGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.changes.PaymentGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

import java.util.*;

public class EffectManager {

    private List<GameChange> reportedChanges;

    private EnumMap<MetaPlayer, Player> boundPlayers;

    private List<MetaPlayer> notBoundablePlayers;

    private EffectContext context;


    public EffectManager(EffectContext context) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null.");
        }

        this.reportedChanges = new ArrayList<>();
        this.boundPlayers = new EnumMap<>(MetaPlayer.class);
        this.notBoundablePlayers = new ArrayList<>();

        this.context = context;
    }

    public void execute(Effect effect) throws UndoException {
        try {
            effect.apply(this, context.getActor());
        } catch (UndoException x) {
            undoEffect();
            throw x;
        }
    }


    public Player getActor() {
        return context.getActor();
    }

    public Player getAttacker() {
        return context.getAttacker();
    }

    public Player getVictim() {
        return context.getVictim();
    }



    private void undoEffect() {
        Collections.reverse(reportedChanges);
        for (GameChange gc : reportedChanges) {
            context.revertGameChange(gc);
        }
        reportedChanges.clear();
    }

    public void damagePlayer(Player attacker, Player victim, int damageAmount, int markAmount) {
        reportGameChange(new DamageGameChange(attacker, victim, damageAmount, markAmount));

        if (damageAmount > 0) {
            context.damageTakenTrigger(attacker, victim);
            context.damageDealtTrigger(attacker, victim);
        }
    }

    public void movePlayer(Player player, Tile destination) {
        reportGameChange(new MovementGameChange(player, destination));
    }


    public void reportGameChange(GameChange gameChange) {
        context.applyGameChange(gameChange);

        reportedChanges.add(gameChange);
    }



    public Player getPlayer(MetaPlayer player) {
        return boundPlayers.get(player);
    }

    public Player bindPlayer(MetaPlayer player, PlayerSelector selector) throws UndoException {
        return bindPlayer(player, selector, true);
    }

    public Player bindPlayer(MetaPlayer player, PlayerSelector selector, boolean forceChoice) throws UndoException {
        Player p = boundPlayers.get(player);

        if (p == null && !notBoundablePlayers.contains(player)) {
            List<Player> excludedPlayers = new ArrayList<>();
            excludedPlayers.add(getActor()); // exclude yourself
            excludedPlayers.addAll(boundPlayers.values()); // exclude players you have already chosen

            PlayerSelector finalSelector = new BlackListFilterPlayerSelector(excludedPlayers, selector);

            p = context.choosePlayer(player, finalSelector, forceChoice);

            if (p != null) {
                boundPlayers.put(player, p);
            } else {
                notBoundablePlayers.add(player);
                // the user chose not to associate anybody to this meta player.
            }
        }
        return p;
    }


    public Tile bindTile(TileSelector selector) throws UndoException {
        return bindTile(selector, true);
    }

    public Tile bindTile(TileSelector selector, boolean forceChoice) throws UndoException {
        return context.chooseTile(selector, forceChoice);
    }

    public Integer choose(Integer... choices) throws UndoException {
        return choose(Arrays.asList(choices));
    }

    public Integer choose(List<Integer> choices) throws UndoException {
        return context.chooseFragment(choices);
    }

    public boolean pay(Integer choice, PaymentInvoice price) throws UndoException {
        PaymentReceipt receipt = context.choosePayment(price, choice);
        if (receipt != null) {
            reportGameChange(new PaymentGameChange(context.getActor(), receipt));
            return true;
        }

        return false;
    }

}
