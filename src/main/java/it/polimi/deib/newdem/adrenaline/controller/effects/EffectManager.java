package it.polimi.deib.newdem.adrenaline.controller.effects;

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

    private EffectContext context;


    public EffectManager(EffectContext context) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null.");
        }

        this.reportedChanges = new ArrayList<>();
        this.boundPlayers = new EnumMap<>(MetaPlayer.class);

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


    private void undoEffect() {
        Collections.reverse(reportedChanges);
        for (GameChange gc : reportedChanges) {
            context.revertGameChange(gc);
        }
        reportedChanges.clear();
    }

    public void damagePlayer(Player attacker, Player victim, int damageAmount, int markAmount) {
        reportGameChange(new DamageGameChange(attacker, victim, damageAmount, markAmount));

        // TODO DAMAGE DEALT trigger
        // TODO DAMAGE TAKEN trigger
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
        // FIXME not boundable players is necessary.

        if (p == null) {
            // FIXME exclude already bound players and actor.
            p = context.choosePlayer(player, selector, forceChoice);

            if (p != null) {
                boundPlayers.put(player, p);
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