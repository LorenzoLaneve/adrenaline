package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearTileSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.changes.MovementGameChange;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

public class MovmentInteraction extends InteractionBase {

    private int minDist;
    private int maxDist;
    private GameChange movmentGC;

    public MovmentInteraction(InteractionContext context, int minDist, int maxDist) {
        super(context);
        this.minDist = minDist;
        this.maxDist = maxDist;
        movmentGC = null;
    }

    @Override
    public void execute() throws UndoException {
        TileSelector selector = new NearTileSelector(context.getActor(), minDist, maxDist);

        Tile target = context.getDataSource().requestTile(selector, true);
        /*
        Tile target = null;
        do {
            try {
                target = parent.getDataSource().requestTile(selector, true);
            } catch (UndoException e) {
                // do not exit, do not propagate.
                // force user selection
            }
        }while (null == target);
        */

        movmentGC = new MovementGameChange(context.getActor(), target);
        movmentGC.update(context.getGame());
    }

    @Override
    public void revert() {
        movmentGC.revert(context.getGame());
    }

    @Override
    public boolean requiresInput() {
        return true;
    }
}
