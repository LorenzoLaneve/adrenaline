package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.MetaPlayer;
import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.PlayerSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.WhileListFilterPlayerSelector;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.items.PowerUpCard;

import java.util.List;

public class SelectAdditionalDamageVictimInteraction extends InteractionBase {

    private PowerUpCard selectedPup;

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

        if(null == selectedPlayer) { return; }

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
