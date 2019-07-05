package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.MetaPlayer;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.WhileListFilterPlayerSelector;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;

import java.util.List;

/**
 * Interaction encapsulating the selection of the victim to receive an extra damage after a SHOOT atom
 */
public class SelectAdditionalDamageVictimInteraction extends InteractionBase {

    private PowerUpCard selectedPup;

    /**
     * Builds a new {@code SelectAdditionalDamageVictimInteraction } bound to the given {@code InteractionContext}
     * @param context this interaction's environment
     * @param selectedPup powerUp to pass on to this {@code Interaction}'s children
     */
    public SelectAdditionalDamageVictimInteraction(InteractionContext context, PowerUpCard selectedPup) {
        super(context);
        this.selectedPup = selectedPup;
    }

    @Override
    public void execute() throws UndoException {
        List<Player> damagedPlayers = context.getDamagedPlayers();

        if(damagedPlayers.isEmpty()) { return; }

        PlayerSelector selector = new WhileListFilterPlayerSelector(damagedPlayers);
        Player selectedPlayer;
        try{
            selectedPlayer = context.getDataSource().requestPlayer(MetaPlayer.VICTIM, selector, false);
        }
        catch (UndoException e) {
            throw new UndoException();
        }

        if(null == selectedPlayer) { throw new UndoException(); }

        context.setVictim(selectedPlayer);

        context.pushInteraction(new ResolveAdditionalDamageInteraction(context, selectedPup));
    }

    @Override
    public void revert() {
        // do nothing
    }

    @Override
    public boolean requiresInput() {
        return true;
    }
}
