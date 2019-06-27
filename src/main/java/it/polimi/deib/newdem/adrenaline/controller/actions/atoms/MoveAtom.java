package it.polimi.deib.newdem.adrenaline.controller.actions.atoms;

import it.polimi.deib.newdem.adrenaline.controller.actions.Action;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.NearTileSelector;
import it.polimi.deib.newdem.adrenaline.controller.effects.selection.TileSelector;
import it.polimi.deib.newdem.adrenaline.model.game.GameChange;
import it.polimi.deib.newdem.adrenaline.model.game.changes.MovementGameChange;
import it.polimi.deib.newdem.adrenaline.model.game.player.Player;
import it.polimi.deib.newdem.adrenaline.model.map.Tile;
import it.polimi.deib.newdem.adrenaline.view.inet.ConnectionException;

public class MoveAtom extends AtomBase {

    private int minDist;
    private int maxDist;

    public MoveAtom(AtomsContainer parent,
                    int minDist,
                    int maxDist) {
        super(parent);
        this.minDist = minDist;
        this.maxDist = maxDist;
    }

    @Override
    public void execute() throws ConnectionException {
        TileSelector selector = new NearTileSelector(parent.getActor(), minDist, maxDist);
        Tile target = parent.getDataSource().actionDidRequestTile(selector);
        GameChange gc = new MovementGameChange(parent.getActor(), target);
        gc.update(parent.getGame());
    }
}
