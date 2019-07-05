package it.polimi.deib.newdem.adrenaline.controller.actions.atoms.iteractions;

import it.polimi.deib.newdem.adrenaline.controller.effects.UndoException;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearTileSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.changes.MovementGameChange;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;

/**
 * Interaction encapsulating the selection of the target tile within a MOVE atom.
 */
public class MovmentInteraction extends InteractionBase {

    private int minDist;
    private int maxDist;
    private GameChange movmentGC;

    /**
     * Builds a new {@code ReloadPaymentInteraction } bound to the given {@code InteractionContext}
     * @param context this interaction's environment
     * @param minDist minimum movement distance
     * @param maxDist maximum movement distance
     */
    public MovmentInteraction(InteractionContext context, int minDist, int maxDist) {
        super(context);
        this.minDist = minDist;
        this.maxDist = maxDist;
        movmentGC = null;
    }

    @Override
    public void execute() throws UndoException {
        TileSelector selector = new NearTileSelector(context.getActor(), minDist, maxDist);


        Tile target = context.getDataSource().requestTile(selector, false);

        if(null == target) { throw new UndoException(); }

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
