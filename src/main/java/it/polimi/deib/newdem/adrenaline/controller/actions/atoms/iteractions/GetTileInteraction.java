package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class GetTileInteraction extends InteractionBase {

    public GetTileInteraction(InteractionContext context) {
        super(context);
    }

    @Override
    public void execute() throws UndoException {
        Tile tile = context.getActor().getTile();

        // is spawn?
        if(tile.hasSpawnPoint()) {
            context.pushInteraction(new SelectNewWeaponInteraction(context));
        }
        // or drop?
        else {
            context.pushInteraction(new GrabDropInteraction(context));
        }
    }

    @Override
    public void revert() {
        // nothing to revert, game state hasn't been changed
    }

    @Override
    public boolean requiresInput() {
        return false;
    }
}
