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

/**
 * Object that provides methods to card effect classes to:
 * - ask players who called the corresponding cards additional information
 *   required by the defined rules.
 * - provide methods to do basic changes to the surrounding game context.
 */
public class EffectManager {

    private List<GameChange> reportedChanges;

    private EnumMap<MetaPlayer, Player> boundPlayers;

    private List<MetaPlayer> notBoundablePlayers;

    private EffectContext context;


    /**
     * Creates a new EffectManager object that will execute effects in the given context.
     * @see EffectContext to see how to represent the context the effect should act on.
     */
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


    /**
     * Returns the Player that is using the card associated with the running effects.
     */
    public Player getActor() {
        return context.getActor();
    }

    /**
     * Returns the Player that attacked the actor, triggering this effect to be called.
     * Note that this method will return null unless the context is a revenge context
     * (e.g. the effect is from a Tagback Grenade power up)
     */
    public Player getAttacker() {
        return context.getAttacker();
    }

    /**
     * Returns the Player that the actor has attacked, triggering this effect to be called.
     * Note that this method will return null unless the context is a "additional action" context
     * (e.g. the effect is from a Targeting Scope power up)
     */
    public Player getVictim() {
        return context.getVictim();
    }


    /**
     * Undoes the effect, reverting all the changes made by it and taking the state of the
     * context to its initial state.
     */
    private void undoEffect() {
        Collections.reverse(reportedChanges);
        for (GameChange gc : reportedChanges) {
            context.revertGameChange(gc);
        }
        reportedChanges.clear();
    }

    /**
     * Registers a the given damage and marks from the {@code attacker} to the {@code victim}.
     * Note that this may trigger events to ask players for power ups that can be used in particular
     * situations.
     */
    public void damagePlayer(Player attacker, Player victim, int damageAmount, int markAmount) {
        reportGameChange(new DamageGameChange(attacker, victim, damageAmount, markAmount));

        if (damageAmount > 0) {
            context.damageTakenTrigger(attacker, victim);
            context.damageDealtTrigger(attacker, victim);
        }
    }

    /**
     * Moves the player to the given tile.
     */
    public void movePlayer(Player player, Tile destination) {
        reportGameChange(new MovementGameChange(player, destination));
    }

    /**
     * Reports the given game change object, storing it in the list of game changes
     * that should be undo in case of request from the user.
     * Note that this also requests the context to apply the game change to the game.
     */
    public void reportGameChange(GameChange gameChange) {
        context.applyGameChange(gameChange);

        reportedChanges.add(gameChange);
    }


    /**
     * Returns the player bound to the given meta player, or {@code null} if the user
     * has never chosen a player for the given meta player.
     * Note that this methods will never ask the user for a player.
     */
    public Player getPlayer(MetaPlayer player) {
        return boundPlayers.get(player);
    }

    /**
     * Returns the player bound to the given meta player by the user. If the meta player has not
     * been bound yet, a player to bind will be asked to the user.
     * @param selector A PlayerSelector object that will filter selectable players.
     * @throws UndoException if the user requested an undo
     */
    public Player bindPlayer(MetaPlayer player, PlayerSelector selector) throws UndoException {
        return bindPlayer(player, selector, true);
    }

    /**
     * Returns the player bound to the given meta player by the user. If the meta player has not
     * been bound yet, a player to bind will be asked to the user.
     * @param selector A PlayerSelector object that will filter selectable players.
     * @param forceChoice whether the user can decide not to choose anyone or be forced to make a choice.
     * @throws UndoException if the user requested an undo
     */
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

    /**
     * Asks the user to select a tile.
     * @param selector A TileSelector object that will filter selectable tiles.
     * @throws UndoException if the user requested an undo
     */
    public Tile bindTile(TileSelector selector) throws UndoException {
        return bindTile(selector, true);
    }

    /**
     * Asks the user to select a tile.
     * @param selector A TileSelector object that will filter selectable tiles.
     * @param forceChoice whether the user can decide not to choose any tile or be forced to make a choice.
     * @throws UndoException if the user requested an undo
     */
    public Tile bindTile(TileSelector selector, boolean forceChoice) throws UndoException {
        return context.chooseTile(selector, forceChoice);
    }

    /**
     * Asks the user to choose among the given choices. Choices are encoded as integers that are defined by each effect.
     * @throws UndoException if the user requested an undo
     */
    public Integer choose(Integer... choices) throws UndoException {
        return choose(Arrays.asList(choices));
    }

    /**
     * Asks the user to choose among the given choices. Choices are encoded as integers that are defined by each effect.
     * @throws UndoException if the user requested an undo
     */
    public Integer choose(List<Integer> choices) throws UndoException {
        return context.chooseFragment(choices);
    }

    /**
     * Asks the user to pay the given price and with what they want to pay (e.g. use power ups).
     * @return Whether the user accepted the payment. If {@code true} is returned,
     * then this method will deduct the ammos and/or power ups from the player's inventory.
     * @param choice An integer describing what the invoice is for.
     * @throws UndoException if the user requested an undo
     */
    public boolean pay(Integer choice, PaymentInvoice price) throws UndoException {
        PaymentReceipt receipt = context.choosePayment(price, choice);
        if (receipt != null) {
            reportGameChange(new PaymentGameChange(context.getActor(), receipt));
            return true;
        }

        return false;
    }

}
